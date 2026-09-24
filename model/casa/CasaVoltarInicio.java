package model.casa;

import model.jogador.Jogador;

public class CasaVoltarInicio extends Casa {

    public CasaVoltarInicio (int numero) {
        super(numero);
    }

    @Override
    public String aplicarEfeito (Jogador jogador) {
        jogador.setCasaAtual(0);
        return "O jogador " + jogador.getNome() + " voltou ao início";
    }
}