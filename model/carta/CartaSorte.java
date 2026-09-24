package model.carta;

import model.jogador.Jogador;
import model.jogador.JogadorSortudo;

public class CartaSorte extends Carta {
    @Override
    public Jogador aplicar (Jogador jogador) {
        return new JogadorSortudo(jogador.getNome(), jogador.getCor(), jogador.getCasaAtual(), jogador.getTotalJogadas());
    }
}
