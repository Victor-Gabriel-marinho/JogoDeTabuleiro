package model.casa;

import model.jogador.Jogador;

public class CasaPerdeAVez extends Casa {

    public CasaPerdeAVez (int numero) {
        super(numero);
    }

    @Override
    public String aplicarEfeito (Jogador jogador) {
        return "Jogador: " + jogador.getNome() + "Perdeu a vez";
    }
}