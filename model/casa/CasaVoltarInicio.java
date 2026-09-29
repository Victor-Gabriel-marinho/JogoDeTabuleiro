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

        Jogador jogadorEscolhido = jogo.escolherJogador(jogador);
        jogadorEscolhido.setCasaAtual(0);

        return "O jogador " + jogador.getNome() + " esta na casa "+ jogador.getCasaAtual() + " e voltou o jogador " + jogadorEscolhido.getNome() + " início";
    }
}