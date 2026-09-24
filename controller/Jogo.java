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
        String mensagem = casaAtual.aplicarEfeito(j);

        tabuleiroView.mostrarTabuleiro(tabuleiro, jogadores);
        System.out.println(mensagem);
        System.out.println("Pressione ENTER para continuar...");
        scan.nextLine();
    }


    private void pausar(long milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Criação de jogadores (fiz manualmente só pra testar)
    private void criarJogadores() {

        Jogador jogador1 = new JogadorNormal("Victor", Cores.AMARELO, 5, 0);
        Jogador jogador2 = new JogadorNormal("Pedro", Cores.VERMELHO, 10, 0);
        Jogador jogador3 = new JogadorNormal("PH", Cores.AZUL, 23, 0);
        jogadores.add(jogador1);
        jogadores.add(jogador2);
        jogadores.add(jogador3);

    }

    private void mostrarEstadoInicial () {
        //Mostra o tabuleiro inicial
        System.out.println("===INCIANDO A PARTIDA===");
        int i = 0;
        for (Jogador jogador : jogadores) {

            System.out.println("JOGADOR"+i+": " + jogador.getNome());
            i++;
        }

        System.out.println("TABULEIRO INICIAL:");

        tabuleiroView.mostrarTabuleiro(tabuleiro, jogadores);

        System.out.println("PRESSIONE ENTER PARA INICIAR: ");
        scan.nextLine();
    }

    private boolean algumJogadorVenceu() {
        return jogadores.stream().anyMatch(j -> j.getCasaAtual() >= 40);
    }
}

