package controller;

import model.Tabuleiro;
import model.casa.Casa;
import model.jogador.Jogador;
import model.jogador.JogadorNormal;
import view.Cores;
import view.TabuleiroView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private final TabuleiroView tabuleiroView = new TabuleiroView();
    private final Tabuleiro tabuleiro = new Tabuleiro();
    private boolean modoAutomatico = true;
    private List<Jogador> jogadores = new ArrayList<>();
    private List<Jogador> jogadoresPerdeAVez = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public void iniciarJogo() {
        tabuleiro.preenchertabuleiro();
        criarJogadores();
        mostrarEstadoInicial();

        int numeroRodada = 1;
        while (!algumJogadorVenceu()) {
            jogarRodada(numeroRodada);
            numeroRodada++;
            // QUEBRANDO AQ PARA NÃO FICAR INFINITO
            break;
        }

    }

    private void jogarRodada(int numeroRodada) {
        System.out.println("=== RODADA " + numeroRodada + " ===");
        for (Jogador j : jogadores) {
            if (algumJogadorVenceu()) break;
            jogarTurno(j);

        }
    }

    private void jogarTurno(Jogador j) {
        if (jogadoresPerdeAVez.contains(j)) {
            System.out.println(j.getNome() + " perdeu a vez!");
            jogadoresPerdeAVez.remove(j);
            return;
        }

        // FUNÇÃO DE GIRAR DADOS E ANDAR AQUI

        // APLICANDO EFEITO DA CASA
        Casa casaAtual = tabuleiro.getCasa(j.getCasaAtual());
        String mensagem = casaAtual.aplicarEfeito(j, this);

        tabuleiroView.mostrarTabuleiro(tabuleiro, jogadores);
        System.out.println(mensagem);
        System.out.println("Pressione ENTER para continuar...");
        scan.nextLine();
    }

    // Criação de jogadores (fiz manualmente só pra testar)
    private void criarJogadores() {

        Jogador jogador1 = new JogadorNormal("Victor", Cores.AMARELO, 25, 0);
        Jogador jogador2 = new JogadorNormal("Pedro", Cores.VERMELHO, 20, 0);
        Jogador jogador3 = new JogadorNormal("PH", Cores.AZUL, 5, 0);
        jogadores.add(jogador1);
        jogadores.add(jogador2);
        jogadores.add(jogador3);

    }

    // Mostra jogadores e tabuleiro antes de iniciar o jogo
    private void mostrarEstadoInicial() {
        //Mostra o tabuleiro inicial
        System.out.println("===INCIANDO A PARTIDA===");
        int i = 0;
        for (Jogador jogador : jogadores) {

            System.out.println("JOGADOR" + i + ": " + jogador.getNome());
            i++;
        }

        System.out.println("TABULEIRO INICIAL:");

        tabuleiroView.mostrarTabuleiro(tabuleiro, jogadores);

        System.out.println("PRESSIONE ENTER PARA INICIAR: ");
        scan.nextLine();
    }

    // Daqui para baixo usei na aplicação dos efeitos das casas
    public Jogador jogadorMaisAtras() {
        Jogador maisAtras = jogadores.getFirst();
        for (Jogador j : jogadores) {

            if (j.getCasaAtual() < maisAtras.getCasaAtual()) {
                maisAtras = j;
            }

        }
        return maisAtras;
    }

    public void trocarPosição(Jogador jogador1, Jogador jogador2) {

        int temp = jogador2.getCasaAtual();
        jogador2.setCasaAtual(jogador1.getCasaAtual());
        jogador1.setCasaAtual(temp);

    }

    public void adicionarJogadorPerdeAVez (Jogador jogador) {
        jogadoresPerdeAVez.add(jogador);
    }

    public void susbtituirJogador (Jogador jogadorAntigo, Jogador jogadorAtual) {
        int i = jogadores.indexOf(jogadorAntigo);
        jogadores.set(i, jogadorAtual);
    }

    public void escolherJogador () {

    }

    private boolean algumJogadorVenceu() {
        return jogadores.stream().anyMatch(j -> j.getCasaAtual() >= 40);
    }
}

