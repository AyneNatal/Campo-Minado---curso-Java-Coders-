package br.com.cod3r.cm.modelo;

import br.com.cod3r.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private final int linha;
    private final int coluna;

    private boolean minado = false;
    private boolean aberto = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    //Methods:

    boolean adicionarVizinho (Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean vizinhoDiagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !vizinhoDiagonal){
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && vizinhoDiagonal){
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void toggleMarcacaoCampo (){
        if (!aberto){
            marcado = !marcado;
        }
    }

    public boolean isMarcado(){
        return marcado;
    }

    boolean abrirCampo(){
        if (!aberto && !marcado){
            aberto = true;

            if (minado){
                throw new ExplosaoException();
            }
            if (vizinhancaSegura()){
                vizinhos.forEach(Campo::abrirCampo);
            }
            return true;
        } else {
            return false;
        }
    }

    void minarCampo (){
        minado = true;
    }

    boolean vizinhancaSegura (){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    boolean objetivoAlcancado (){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return  desvendado || protegido;
    }

    long minasNaVizinhanca (){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar (){
        aberto = false;
        minado = false;
        marcado = false;
    }

    @Override
    public String toString() {
        if (marcado){
            return "x";
        } else if (aberto && minado){
            return "*";
        } else if (aberto && minasNaVizinhanca() > 0){
            return Long.toString(minasNaVizinhanca());
        } else if (aberto){
            return " ";
        } else {
            return "?";
        }
    }

    //Getters ans Setters:

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public boolean isMinado() {
        return minado;
    }

    public void setMinado(boolean minado) {
        this.minado = minado;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
}
