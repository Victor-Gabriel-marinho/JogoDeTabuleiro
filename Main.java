
import java.util.List;

import controller.Jogo;
import model.Tabuleiro;
import model.casa.Casa;

public class Main {

    private final Jogo jogo = new Jogo();

    public void main(String[] args) {
        // Chama o Controller que mestra o jogo
        jogo.iniciarJogo();

    }
}
