package com.aluracursos;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        PanelConversorDeDivisas estructuraDelPanel = new PanelConversorDeDivisas();
        estructuraDelPanel.estructurarPanel();

        System.out.println(estructuraDelPanel);

        MenuPanel.showPanel(estructuraDelPanel);
    }

    //*******************************************************************************************************************************************************


}