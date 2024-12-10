package com.aluracursos;

import javax.swing.*;

public final class MenuPanel {

    public static void showPanel(PanelConversorDeDivisas infoPanel) {

        while (true) {

            int resultado = JOptionPane.showConfirmDialog(null, infoPanel.getPanelPrincipal(), "Convertidor de Divisas", JOptionPane.OK_CANCEL_OPTION);

            //*********************************************************************************************************************************************
            if (resultado == JOptionPane.OK_OPTION) {
                String divisaEntrada = infoPanel.getListaDivisasEntrada().getSelectedValue();
                String divisaSalida = infoPanel.getListaDivisasSalida().getSelectedValue();
                String valorTexto = infoPanel.getValorAConvertir().getText();

                if (divisaEntrada == null || divisaSalida == null || valorTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar ambas divisas y un valor a convertir.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int valor = Integer.parseInt(valorTexto);
                        String conversion = ConversionController.convert(divisaEntrada, divisaSalida, valor);
                        System.out.println("salio 1");
                        JOptionPane.showMessageDialog(null, conversion, "Resultado de la Conversión", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Salio 2");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El valor a convertir debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error durante la conversión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Programa Finalizado", "Finalizando", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

}
