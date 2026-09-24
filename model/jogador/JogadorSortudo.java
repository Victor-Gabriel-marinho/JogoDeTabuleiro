package model.jogador;

public class JogadorSortudo extends Jogador{

    public JogadorSortudo(String cor, int casaAtual, int totalJogadas){
       super(cor, casaAtual, totalJogadas);
    }

    @Override
    public void sorteioDados() {
        while (somaDados < 7) {
            this.dado1 = aleatorio.nextInt(6) + 1;
            this.dado2 = aleatorio.nextInt(6) + 1;
            this.somaDados = this.dado1 + this.dado2;
        }
    }
}
    

