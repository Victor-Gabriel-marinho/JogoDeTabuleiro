package model.casa;

import controller.Jogo;
import model.jogador.Jogador;

public class CasaPerdeAVez extends Casa {

    public CasaPerdeAVez (int numero) {
        super(numero);
    }

    @Override
    public String getSimbolo() {
        return "X";
    }

    @Override
    public String aplicarEfeito (Jogador jogador, Jogo jogo) {
        jogo.adicionarJogadorPerdeAVez(jogador);
        return "Jogador: " + jogador.getNome() + " não joga proxima rodada a vez";
    }
}