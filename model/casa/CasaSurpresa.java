package model.casa;

import model.Baralho;
import model.carta.Carta;
import model.jogador.Jogador;

public class CasaSurpresa extends Casa {
    private final Baralho baralho = new Baralho();
    public CasaSurpresa (int numero) {
        super(numero);
    }

    @Override
    public void aplicarEfeito (Jogador jogador) {
        Carta carta = baralho.sortear();
        Jogador novoJogador = carta.aplicar(jogador);

    }
}