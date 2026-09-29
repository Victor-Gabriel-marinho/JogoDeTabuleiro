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
        System.out.println("=== CRIAÇÃO DE JOGADORES ===");
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
                System.out.println("Qual a sorte do jogador? Clique:\n1 - Sortudo\n2 - Normal\n3 - Azarado");
                sorte = scan.nextInt();
                scan.nextLine();
                if(sorte < 1 || sorte > 3){
                    System.out.println("DIGITE UM VALOR DE 1 A 3");
                }
            }catch(InputMismatchException e){
                System.out.println("DIGITE UM VALOR DE 1 A 3");
                scan.nextLine();
            }
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
                    case "amarelo" -> cor = Cores.AMARELO;
                    case "azul" -> cor = Cores.AZUL;
                    case "vermelho" -> cor = Cores.VERMELHO;
                    case "verde" -> cor = Cores.VERDE;
                    case "branco" -> cor = Cores.BRANCO;
                    case "ciano" -> cor = Cores.CIANO;
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
        }

        for (Jogador j : jogadores) {
            if (j.getCasaAtual() >= 40) {
                mostrarVencedor(j);
                break;
            }
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

            switch (sorteJogador) {
                case 1 -> jogador = new JogadorSortudo(nome, cor, 0, 0);
                case 2 -> jogador = new JogadorNormal(nome, cor, 0, 0);
                case 3 -> jogador = new JogadorAzarado(nome, cor, 0, 0);
                default -> jogador = new JogadorNormal(nome, cor, 0 ,0 );
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
            System.out.println(j.getNome() + " PERDEU A VEZ!");
            jogadoresPerdeAVez.remove(j);
            return;
        }

        // FUNÇÃO DE GIRAR DADOS E ANDAR AQUI
        j.sorteioDados();
        j.getSomaDados();


        System.out.println("A SOMA DOS DADOS FOI: " + j.getSomaDados());

        j.andarCasas();
        j.incrementarJogadas();

        if (j.getCasaAtual() + j.getSomaDados() >= 40) {
            return;
        }

        // APLICANDO EFEITO DA CASA
        Casa casaAtual = tabuleiro.getCasa(j.getCasaAtual());
        String mensagem = casaAtual.aplicarEfeito(j, this);

        tabuleiroView.mostrarTabuleiro(tabuleiro, jogadores);
        System.out.println(mensagem);
        System.out.println("Pressione ENTER para continuar...");
        scan.nextLine();
    }

    private boolean algumJogadorVenceu() {
        return jogadores.stream().anyMatch(j -> j.getCasaAtual() >= 40);
    }

    private void mostrarVencedor(Jogador jogadorVencedor) {
        System.out.println("PARABÉNS "+ jogadorVencedor.getNome() + " VOCÊ VENCEU com "+ jogadorVencedor.getTotalJogadas() +"!!!");

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

    // FUNÇÕES PARA APLICAR EFEITO DAS CASAS:

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

    public Jogador escolherJogador (Jogador jogadorQueEscolhe) {

        System.out.println("===ESCOLHA UM JOGADOR PARA VOLTAR AO INÍCIO===");

        int i = 1;

        for ( Jogador j : jogadores ) {

            if (j.hashCode() == jogadorQueEscolhe.hashCode()) continue;

            System.out.println(i + " " + j.getNome());
            i++;

        }
        int idEscolhido = scan.nextInt();

        return jogadores.get(idEscolhido - 1);
    }

}

