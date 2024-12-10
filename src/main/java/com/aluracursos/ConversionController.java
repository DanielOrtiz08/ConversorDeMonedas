package com.aluracursos;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public final class ConversionController {

    private static final String url = "https://v6.exchangerate-api.com/v6/c9288dd44df33841bdd18883/latest/";

    private static LinkedList<Divisa> divisas = new LinkedList<>();
    private static boolean divisasIsEmpty = true;

    public static LinkedList<Divisa> obtenerDivisas() {
        if (!divisasIsEmpty) return divisas;

        URI endpoint = URI.create(url+"usd");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            JsonObject conversionRatesObject = jsonObject.getAsJsonObject("conversion_rates");

            for (Map.Entry<String, JsonElement> entry : conversionRatesObject.entrySet()) {
                String currency = entry.getKey();
                double rate = entry.getValue().getAsDouble();

                divisas.add(new Divisa(currency, rate));
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("No se pudo convertir");
        } catch (IOException e) {
            throw new RuntimeException("Exepcion de tipo E/S");
        } catch (InterruptedException e) {
            throw new RuntimeException("Exepcion de tipo Interrupted");
        }
        divisasIsEmpty = false;
        return divisas;
    }

    public static String convert(String divisaEntrada, String divisaSalida, int valor) {
        if(divisasIsEmpty) {
            obtenerDivisas();
            divisasIsEmpty = false;
        }

        ForkJoinPool pool = new ForkJoinPool();

        Divisa entrada = pool.invoke(new DivaSearchTask(divisas, divisaEntrada, 0, divisas.size()));
        Divisa salida = pool.invoke(new DivaSearchTask(divisas, divisaSalida, 0, divisas.size()));

        if (entrada == null || salida == null) {
            throw new IllegalArgumentException("Una o ambas divisas no existen");
        }

        double tasaConversion = salida.getTasa() / entrada.getTasa() * valor;
        return String.format("%d %s equivale a %.4f %s", valor, divisaEntrada, tasaConversion, divisaSalida);
    }

}
