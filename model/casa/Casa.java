package model.casa;

import controller.Jogo;
import model.jogador.Jogador;

public abstract class Casa {

    private int numero;

    public Casa (int numero) {
        this.numero = numero;
    }

    public abstract String aplicarEfeito(Jogador jogador, Jogo jogo);

    public abstract String getSimbolo();

    public int getNumero() {
        return numero;
    }

}