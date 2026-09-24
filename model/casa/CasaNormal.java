package model.casa;

import controller.Jogo;
import model.jogador.Jogador;

public class CasaNormal extends Casa {

    public CasaNormal (int numero) {
        super(numero);
    }

    @Override
    public String getSimbolo() {
        return String.valueOf(this.getNumero());
    }

    @Override
    public String aplicarEfeito (Jogador jogador, Jogo jogo) {
        return "Jogador:" + jogador.getNome() + " esta na casa "+ jogador.getCasaAtual();
    }
    
}