package controller.MododeJogo;

import model.jogador.Jogador;

public class ModoNormal implements FonteDeMovimento {
    @Override
    public int obterQuantidadeDeCasas(Jogador jogador){
        jogador.sorteioDados();
        return jogador.getSomaDados();
    }
}
