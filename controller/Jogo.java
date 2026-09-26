package controller;

import model.Tabuleiro;
import model.casa.Casa;
import model.jogador.Jogador;
import model.jogador.JogadorAzarado;
import model.jogador.JogadorNormal;
import model.jogador.JogadorSortudo;
import view.Cores;
import view.TabuleiroView;

import java.lang.annotation.AnnotationTypeMismatchException;
import java.util.*;

public class Jogo {
    private final TabuleiroView tabuleiroView = new TabuleiroView();
    private final Tabuleiro tabuleiro = new Tabuleiro();
    private boolean modoAutomatico = true;
    private List<Jogador> jogadores = new ArrayList<>();
    private List<Jogador> jogadoresPerdeAVez = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private List<String> cores = new ArrayList<>();


    public int verificarEntradaJogadores(){

        int total = 0;
        while(total < 2 || total > 6) {
            System.out.println("Quantos jogadores vão participar? Min: 2, Max: 6 ");
            try {
                total = scan.nextInt();
                scan.nextLine();
                if(total < 2 || total > 6){
                    System.out.println("Valor precisa ser de 2 a 6");
                }

            } catch (InputMismatchException e) {
                System.out.println("Valor precisa ser inteiro!");
                scan.nextLine();
            }
        }
        return total;
    }
    public int definirSorteJogador(){
        int sorte = 0;
        while(sorte < 1 || sorte > 3){
            try {
                System.out.println("Qual a sorte do jogador? Clique:\n 1 - Sortudo\n2-Normal\n3-Azarado");
                sorte = scan.nextInt();
                scan.nextLine();
                if(sorte < 1 || sorte > 3){
                    System.out.println("Digite entre 1 a 3");
                }
            }catch(InputMismatchException e){
                System.out.println("Digite um valor entre 1 e 3");}
        }
        return sorte;
    }

    public String escolherCor(){
        String cor = "";
        System.out.println("Opcoes disponiveis de cor: ");
        for(String opcoes : cores){
            System.out.println(opcoes);
        }
        System.out.println("Digite a cor para o jogador: ");
        while (!cores.contains(cor)){
            cor = scan.nextLine().trim().toLowerCase();
            if(!cores.contains(cor)){
                System.out.println("Opcao cor nao disponivel! ");
            }
            else{
                cores.remove(cor);
                switch (cor) {
                    case "amarelo":
                        cor = Cores.AMARELO;
                        break;
                    case "azul":
                        cor = Cores.AZUL;
                        break;
                    case "vermelho":
                        cor = Cores.VERMELHO;
                        break;
                    case "verde":
                        cor = Cores.VERDE;
                        break;
                    case "branco":
                        cor = Cores.BRANCO;
                        break;
                    case "ciano":
                        cor = Cores.CIANO;
                        break;
                }
                break;
            }

        }
        return cor;
    }




    public void iniciarJogo() {
        cores.addAll(Arrays.asList("amarelo", "azul", "vermelho", "verde", "branco", "ciano"));
        tabuleiro.preenchertabuleiro();
        criarJogadores();
        mostrarEstadoInicial();

        int numeroRodada = 1;
        while (!algumJogadorVenceu()) {
            jogarRodada(numeroRodada);
            numeroRodada++;
            // QUEBRANDO AQ PARA NÃO FICAR INFINITO
        }

    }
    public void criarJogadores(){
        int totalJogadores = verificarEntradaJogadores();
        for(int i = 0; i < totalJogadores; i++){
            int j = i+1;
            System.out.println("-----Jogador " + j + "-----");
            System.out.println("Qual o nome do jogador "+ j + "? ");
            String nome = scan.nextLine();
            System.out.println("Qual a cor de " + nome + "? ");
            String cor = escolherCor();
            int sorteJogador = definirSorteJogador();
            Jogador jogador;
            if(sorteJogador == 1){
                jogador = new JogadorSortudo(nome, cor, 0, 0);
            }
            else if(sorteJogador == 2){
                jogador = new JogadorNormal(nome, cor, 0, 0);
            }
            else{
                jogador = new JogadorAzarado(nome, cor, 0, 0);
            }
            jogadores.add(jogador);
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
        j.sorteioDados();
        j.getSomaDados();
        j.andarCasas();

        // APLICANDO EFEITO DA CASA
        Casa casaAtual = tabuleiro.getCasa(j.getCasaAtual());
        String mensagem = casaAtual.aplicarEfeito(j, this);

        tabuleiroView.mostrarTabuleiro(tabuleiro, jogadores);
        System.out.println(mensagem);
        System.out.println("Pressione ENTER para continuar...");
        scan.nextLine();
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

