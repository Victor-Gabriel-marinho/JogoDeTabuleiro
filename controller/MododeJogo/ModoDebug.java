package controller.MododeJogo;

import model.jogador.Jogador;

import java.util.Scanner;

public class ModoDebug implements  FonteDeMovimento{

    private final Scanner scan;

    public ModoDebug (Scanner scan){
        this.scan = scan;
    }

    @Override
    public int obterQuantidadeDeCasas(Jogador jogador){
        System.out.println(jogador.getNome() + " está na casa " + jogador.getCasaAtual());
        System.out.print("Para qual casa deseja andar? ");

        int destino = scan.nextInt();

        scan.nextLine();

        return destino - jogador.getCasaAtual();
    }
}
