package model.jogador;
import java.util.ArrayList;
import java.util.Random;

public class Jogador {

    private Random aleatorio = new Random();
    private String cor;
    private int dado1;
    private int dado2;
    private int somaDados;
    private int casaAtual;
    private int totalJogadas;

    public Jogador(String cor){
        this.cor = cor;
        this.casaAtual = 0;
        this.totalJogadas =0;

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


    public void sorteioDados(){
        this.dado1 = aleatorio.nextInt(6) + 1;
        this.dado2 = aleatorio.nextInt(6) + 1;
        this.somaDados = this.dado1 + this.dado2;
    }

    public void andarCasas(){
        this.casaAtual += this.somaDados;
    }

}

