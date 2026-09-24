package model.casa;

import model.jogador.Jogador;

public abstract class Casa {

    private int numero;

    public Casa (int numero) {
        this.numero = numero;
    }

    public abstract void aplicarEfeito(Jogador j);

    public int getNumero() {
        return numero;
    }

}