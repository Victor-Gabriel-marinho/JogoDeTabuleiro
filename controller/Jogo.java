package controller;

import controller.MododeJogo.FonteDeMovimento;
import controller.MododeJogo.ModoDebug;
import controller.MododeJogo.ModoNormal;
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
    private FonteDeMovimento fonteDeMovimento;
    private List<Jogador> jogadores = new ArrayList<>();
    private List<Jogador> jogadoresPerdeAVez = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private List<String> cores = new ArrayList<>();
    private List<Integer> sortes = new ArrayList<>();


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


    public int definirSorteJogador(List<Integer> sortes) {
        int sorte = 0;
        boolean todasIguais = sortes != null && !sortes.isEmpty();

        if (todasIguais) {
            for (int i = 1; i < sortes.size(); i++) {
                if (!sortes.get(i).equals(sortes.get(0))) {
                    todasIguais = false;
                    break;
                }
            }
        }

        while (sorte < 1 || sorte > 3) {
            try {
                System.out.println("""
                    Qual a sorte do jogador? Digite:
                    1 - Sortudo
                    2 - Normal
                    3 - Azarado
                    """);

                sorte = scan.nextInt();
                scan.nextLine();

                if (sorte < 1 || sorte > 3) {
                    System.out.println("Digite um valor entre 1 e 3.");
                    continue;
                }

                if (todasIguais && sorte == sortes.get(0)) {
                    System.out.println("Todos os jogadores têm a mesma sorte! Escolha uma sorte diferente!");
                    sorte = 0;
                }

            } catch (InputMismatchException e) {
                System.out.println("Digite um valor entre 1 e 3.");
                scan.nextLine();
            }
        }

        return sorte;
    }



    public String escolherCor() {
        System.out.println("Opcoes disponiveis de cor:");

        for (int i = 0; i < cores.size(); i++) {
            System.out.println((i + 1) + " - " + cores.get(i));
        }

        int opcao = 0;

        while (opcao < 1 || opcao > cores.size()) {
            try {
                System.out.println("Digite o numero da cor para o jogador: ");
                opcao = scan.nextInt();
                scan.nextLine();

                if (opcao < 1 || opcao > cores.size()) {
                    System.out.println("Opcao de cor nao disponivel!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Digite um numero valido!");
                scan.nextLine();
            }
        }

        String corEscolhida = cores.remove(opcao - 1);

        return switch (corEscolhida) {
            case "Amarelo" -> Cores.AMARELO;
            case "Azul" -> Cores.AZUL;
            case "Vermelho" -> Cores.VERMELHO;
            case "Verde" -> Cores.VERDE;
            case "Branco" -> Cores.BRANCO;
            case "Ciano" -> Cores.CIANO;
            default -> throw new IllegalArgumentException("Cor invalida");
        };
    }

    public void iniciarJogo() {
        cores.addAll(Arrays.asList("Amarelo", "Azul", "Vermelho", "Verde", "Branco", "Ciano"));
        tabuleiro.preenchertabuleiro();
        int modo = 0;
        while (modo < 1 || modo > 2) {
            try {
                System.out.println("=== JOGO DE TABULEIRO ===");
                System.out.println("Escolha o modo de jogo");
                System.out.println("1 - Casual");
                System.out.println("2 - Debug");

                modo = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                scan.nextLine();
            }

        }
        this.fonteDeMovimento = (modo==2) ? new ModoDebug(scan) : new ModoNormal();

        criarJogadores();
        mostrarEstadoInicial();

        int numeroRodada = 1;
        while (!algumJogadorVenceu()) {
            jogarRodada(numeroRodada);
            numeroRodada++;
        }

        for (Jogador j : jogadores) {
            if (j.getCasaAtual() >= 40) {
                mostrarResumoFinal(j);
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

            int sorteJogador;
            if(i == totalJogadores -1){
                sorteJogador = definirSorteJogador(sortes);
            }
            else{
                sorteJogador = definirSorteJogador(null);
            }
            sortes.add(sorteJogador);
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

        System.out.println("");
        if (jogadoresPerdeAVez.contains(j)) {
            System.out.println(j.getNome() + " PERDEU A VEZ!");
            jogadoresPerdeAVez.remove(j);
            return;
        }

        int quantidade = fonteDeMovimento.obterQuantidadeDeCasas(j);

        System.out.println("=== TURNO DE "+ j.getNome() +" ===");
        System.out.println("=== "+j.getNome() + " ESTÁ ANDANDO " + quantidade + " CASAS ===");
        j.andarCasas(quantidade);
        j.incrementarJogadas();

        if (j.getCasaAtual() >= 40) {
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
        System.out.println("PARABÉNS "+ jogadorVencedor.getNome() + " VOCÊ VENCEU COM "+ jogadorVencedor.getTotalJogadas() +" JOGADAS!!!");

    }

    private void mostrarResumoFinal(Jogador vencedor) {
        mostrarVencedor(vencedor);
        System.out.println("\n=== RESUMO FINAL ===");
        for (Jogador j : jogadores) {
            System.out.println(j.getNome() + " - Casa " + j.getCasaAtual()
                    + " - " + j.getTotalJogadas() + " jogadas");
        }
    }

    // Mostra jogadores e tabuleiro antes de iniciar o jogo
    private void mostrarEstadoInicial() {
        //Mostra o tabuleiro inicial
        System.out.println("===INCIANDO A PARTIDA===");
        int i = 1;
        for (Jogador jogador : jogadores) {

            System.out.println("JOGADOR " + i + ": " + jogador.getNome());
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

        System.out.println("");
        System.out.println("===ESCOLHA UM JOGADOR PARA VOLTAR AO INÍCIO===");

        int idEscolhido = -1;
        while (idEscolhido < 0 || idEscolhido >= jogadores.size() || jogadores.get(idEscolhido) == jogadorQueEscolhe) {
            try {

                System.out.print("Digite o número do jogador: ");
                idEscolhido = scan.nextInt();
                scan.nextLine();

            } catch (InputMismatchException e) {

                System.out.println("Digite um número válido!");
                scan.nextLine();
                idEscolhido = -1;

            }
        }
        return jogadores.get(idEscolhido);
    }

}

