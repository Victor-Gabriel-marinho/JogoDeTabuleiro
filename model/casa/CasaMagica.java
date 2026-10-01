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
        jogo.trocarPosicao(jogador, maisAtras);
        return jogador.getNome() + " esta na casa "+ jogador.getCasaAtual() + " e trocou de posição com " + maisAtras.getNome();
    }
}