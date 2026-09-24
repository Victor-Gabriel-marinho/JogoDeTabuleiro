package model.casa;

import model.jogador.Jogador;

public class CasaNormal extends Casa {

    public CasaNormal (int numero) {
        super(numero);
    }

    @Override
    public String aplicarEfeito (Jogador jogador) {
        return "Jogador:" + jogador.getNome() + " esta na casa "+ jogador.getCasaAtual();
    }
    
}