package model.casa;

import controller.Jogo;
import model.jogador.Jogador;

public class CasaVoltarInicio extends Casa {

    public CasaVoltarInicio (int numero) {
        super(numero);
    }

    @Override
    public String getSimbolo() {
        return "<<";
    }

    @Override
    public String aplicarEfeito (Jogador jogador, Jogo jogo) {
        jogador.setCasaAtual(0);
        return "O jogador " + jogador.getNome() + " voltou ao início";
    }
}