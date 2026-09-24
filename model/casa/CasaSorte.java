package model.casa;

import model.jogador.Jogador;

public class CasaSorte extends Casa {

    public CasaSorte (int numero) {
        super(numero);
    }

    @Override
    public void aplicarEfeito (Jogador jogador) {
        j.setCasaAtual(j.getCasaAtual() + 3);
    }
}