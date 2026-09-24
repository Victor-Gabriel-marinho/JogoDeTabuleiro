package model.casa;

import model.jogador.Jogador;
import model.jogador.JogadorAzarado;

public class CasaSorte extends Casa {

    public CasaSorte (int numero) {
        super(numero);
    }

    @Override
    public String aplicarEfeito (Jogador jogador) {

        if (jogador.getClass() == JogadorAzarado.class) {
           return "Jogador azarado não anvaça 3 casas";
        }
        jogador.setCasaAtual(jogador.getCasaAtual() + 3);
        return "Jogador: " + jogador.getNome() + "Avançou 3 casas";
    }
}