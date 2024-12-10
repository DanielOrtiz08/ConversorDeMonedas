package com.aluracursos;

import lombok.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.LinkedList;

@Getter
@Setter
@NoArgsConstructor
public class PanelConversorDeDivisas {

    private JList<String> listaDivisasSalida;
    private JList<String> listaDivisasEntrada;
    private JTextField valorAConvertir;
    private JPanel panelPrincipal;

    public void estructurarPanel() {
        JPanel panelValor = new JPanel(new FlowLayout());
        panelValor.add(new JLabel("Valor a convertir:"));
        setValorAConvertir(new JTextField(10));
        panelValor.add(getValorAConvertir());

        DefaultListModel<String> modeloListaEntrada = new DefaultListModel<>();
        DefaultListModel<String> modeloListaSalida = new DefaultListModel<>();

        LinkedList<Divisa> divisas = ConversionController.obtenerDivisas();
        divisas.stream().map(Divisa::getNombre).forEach(nombre -> {
            modeloListaEntrada.addElement(nombre);
            modeloListaSalida.addElement(nombre);
        });

        setListaDivisasEntrada(new JList<>(modeloListaEntrada));
        setListaDivisasSalida(new JList<>(modeloListaSalida));

        JTextField barraDeBusquedaEntrada = new JTextField(10);
        JTextField barraDeBusquedaSalida = new JTextField(10);
        configurarBarraDeBusqueda(divisas, barraDeBusquedaEntrada, modeloListaEntrada, getListaDivisasEntrada());
        configurarBarraDeBusqueda(divisas, barraDeBusquedaSalida, modeloListaSalida, getListaDivisasSalida());

        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new BoxLayout(panelEntrada, BoxLayout.Y_AXIS));
        panelEntrada.add(new JLabel("Divisa de entrada:"));
        panelEntrada.add(barraDeBusquedaEntrada);
        panelEntrada.add(new JScrollPane(getListaDivisasEntrada()));

        JPanel panelSalida = new JPanel();
        panelSalida.setLayout(new BoxLayout(panelSalida, BoxLayout.Y_AXIS));
        panelSalida.add(new JLabel("Divisa de salida:"));
        panelSalida.add(barraDeBusquedaSalida);
        panelSalida.add(new JScrollPane(getListaDivisasSalida()));

        JPanel panelDivisas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelDivisas.add(panelEntrada);
        panelDivisas.add(panelSalida);

        setPanelPrincipal(new JPanel());
        getPanelPrincipal().setLayout(new BoxLayout(getPanelPrincipal(), BoxLayout.Y_AXIS));
        getPanelPrincipal().add(panelValor);
        getPanelPrincipal().add(panelDivisas);
    }

    private void configurarBarraDeBusqueda(LinkedList<Divisa> divisas, JTextField barraDeBusqueda, DefaultListModel<String> modeloLista, JList<String> listaDivisas) {
        final boolean[] ignorarEvento = {false};

        barraDeBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (ignorarEvento[0]) return;
                listaDivisas.clearSelection();
                actualizarListaDeDivisas(barraDeBusqueda.getText(), modeloLista, divisas);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (ignorarEvento[0]) return;
                listaDivisas.clearSelection();
                actualizarListaDeDivisas(barraDeBusqueda.getText(), modeloLista, divisas);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (ignorarEvento[0]) return;
                listaDivisas.clearSelection();
                actualizarListaDeDivisas(barraDeBusqueda.getText(), modeloLista, divisas);
            }
        });

        listaDivisas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && !listaDivisas.isSelectionEmpty()) {
                    ignorarEvento[0] = true;
                    barraDeBusqueda.setText(listaDivisas.getSelectedValue());
                    ignorarEvento[0] = false;
                }
            }
        });
    }

    private void actualizarListaDeDivisas(String buscarDivisa, DefaultListModel<String> modeloLista, LinkedList<Divisa> divisas) {
        modeloLista.clear();
        for (Divisa divisa : divisas) {
            if (divisa.getNombre().toLowerCase().contains(buscarDivisa.toLowerCase())) {
                modeloLista.addElement(divisa.getNombre());
            }
        }
    }
}