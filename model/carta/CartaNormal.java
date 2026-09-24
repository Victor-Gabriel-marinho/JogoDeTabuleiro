package model.carta;

import model.jogador.Jogador;
import model.jogador.JogadorNormal;

public class CartaNormal extends Carta {
    @Override
    public Jogador aplicar (Jogador jogador) {
        return new JogadorNormal(jogador.getNome(), jogador.getCor(), jogador.getCasaAtual(), jogador.getTotalJogadas());
    }
}
