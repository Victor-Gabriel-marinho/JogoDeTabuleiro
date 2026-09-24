package model.jogador;

public class JogadorNormal extends Jogador{
    public JogadorNormal(String cor, int casaAtual, int totalJogadas){
        super(cor, casaAtual, totalJogadas);
    }

    @Override
    public void sorteioDados() {
        this.dado1 = aleatorio.nextInt(6) + 1;
        this.dado2 = aleatorio.nextInt(6) + 1;
        this.somaDados = this.dado1 + this.dado2;

    }
}
