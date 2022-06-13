package br.com.cod3r.cm;

import br.com.cod3r.cm.modelo.Tabuleiro;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tab = new Tabuleiro(7, 7, 7);
        tab.abrirCampo(3,3);
        tab.alternarMarcacaoCampo(4,4);
        tab.alternarMarcacaoCampo(4,5);

        System.out.println(tab);
    }
}
