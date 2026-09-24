package model.casa;

import model.jogador.Jogador;

public class CasaMagica extends Casa {

    public CasaMagica (int numero) {
        super(numero);
    }

    @Override
    public String aplicarEfeito (Jogador jogador) {
        return "";
    }
}