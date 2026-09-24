package model.jogador;
import java.util.ArrayList;
import java.util.Random;

public abstract class Jogador {
    String nome;
    protected Random aleatorio = new Random();
    private String cor;
    protected int dado1;
    protected int dado2;
    protected int somaDados;
    private int casaAtual;
    private int totalJogadas;

    public Jogador(String nome, String cor, int casaAtual, int totalJogadas){
        this.nome = nome;
        this.cor = cor;
        this.casaAtual = casaAtual;
        this.totalJogadas =totalJogadas;

    }
    public String getCor() {
        return cor;
    }

    public int getDado1() {
        return dado1;
    }

    public int getDado2() {
        return dado2;
    }

    public int getSomaDados() {
        return somaDados;
    }

    public int getCasaAtual() {
        return casaAtual;
    }

    public int getTotalJogadas() {
        return totalJogadas;
    }

    public void setCasaAtual(int casaAtual) {
        this.casaAtual = casaAtual;
    }


    public abstract void sorteioDados();

    public void andarCasas(){
        this.casaAtual += this.somaDados;
    }

}

