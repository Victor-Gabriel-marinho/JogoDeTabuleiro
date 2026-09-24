package model.carta;

import model.jogador.Jogador;
import model.jogador.JogadorAzarado;

public class CarteAzar extends Carta{
    @Override
    public Jogador aplicar (Jogador jogador) {
        return new JogadorAzarado(jogador.getNome(), jogador.getCor(), jogador.getCasaAtual(), jogador.getTotalJogadas());
    }
}
