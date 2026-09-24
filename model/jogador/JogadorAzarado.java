package model.jogador;

public class JogadorAzarado extends Jogador{
    public JogadorAzarado(String nome, String cor, int casaAtual, int totalJogadas){
        super(nome, cor, casaAtual, totalJogadas);
    }

    @Override
    public void sorteioDados() {
        while (somaDados > 6) {
            this.dado1 = aleatorio.nextInt(6) + 1;
            this.dado2 = aleatorio.nextInt(6) + 1;
            this.somaDados = this.dado1 + this.dado2;
        }
    }
}

