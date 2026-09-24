package view;

import model.Tabuleiro;
import model.casa.Casa;
import model.jogador.Jogador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabuleiroView {

    public void mostrarTabuleiro(Tabuleiro tabuleiro, List<Jogador> jogadores) {

        List<Casa> casas = tabuleiro.getCasas();
        int colunas = 10;

        for (int inicio = 0; inicio < casas.size(); inicio += colunas) {
            for (int c = 0; c < colunas; c++) {
                System.out.print("+----");
            }
            System.out.println("+");

            Map<Integer, List<Jogador>> jogadoresPorPosicao = new HashMap<>();
            for (Jogador j1 : jogadores) {
                jogadoresPorPosicao
                        .computeIfAbsent(j1.getCasaAtual(), k -> new ArrayList<>())
                        .add(j1);
            }

            for (int j = inicio; j < inicio + colunas && j < casas.size(); j++) {
                List<Jogador> aqui = jogadoresPorPosicao.getOrDefault(j, List.of());

                if (aqui.isEmpty()) {
                    System.out.printf("| %2s ", tabuleiro.getCasa(j).getSimbolo());
                } else {
                    StringBuilder simbolos = new StringBuilder();
                    for (Jogador jogador : aqui) {
                        simbolos.append(jogador.getCor())
                                .append(tabuleiro.getCasa(j).getSimbolo())
                                .append(Cores.RESET);
                    }
                    System.out.printf("| %2s ", simbolos.toString());
                }
            }
            System.out.println("|");
        }

        for (int c = 0; c < colunas; c++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }

}
