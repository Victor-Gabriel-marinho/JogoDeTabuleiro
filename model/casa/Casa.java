package model.casa;

import model.jogador.Jogador;

public abstract class Casa {

    private int numero;

    public Casa (int numero) {
        this.numero = numero;
    }

    public abstract String aplicarEfeito(Jogador jogador);

    public int getNumero() {
        return numero;
    }

}