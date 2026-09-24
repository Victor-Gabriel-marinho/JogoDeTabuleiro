package model.casa;

import controller.Jogo;
import model.jogador.Jogador;

public class CasaMagica extends Casa {

    public CasaMagica (int numero) {
        super(numero);
    }

    @Override
    public String getSimbolo() {
        return "*";
    }

    @Override
    public String aplicarEfeito (Jogador jogador, Jogo jogo) {
        Jogador maisAtras =  jogo.jogadorMaisAtras();
        jogo.trocarPosição(jogador, maisAtras);
        return jogador.getNome() + " trocou de posição com " + maisAtras.getNome();
    }
}