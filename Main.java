
import java.util.List;
import model.Tabuleiro;
import model.casa.Casa;

public class Main {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final static Tabuleiro t = new Tabuleiro();

    public static void main(String[] args) {

        t.preenchertabuleiro();
        List<Casa> casas = t.getCasas();
        int colunas = 10;

        for (int inicio = 0; inicio < casas.size(); inicio += colunas) {
            for (int c = 0; c < colunas; c++) {
                System.out.print("+----");
            }
            System.out.println("+");

            for (int j = inicio; j < inicio + colunas && j < casas.size(); j++) {
                System.out.printf("| %2d ", t.getCasa(j).getNumero());
            }
            System.out.println("|");
        }

        for (int c = 0; c < colunas; c++) {
            System.out.print("+----");
        }
        System.out.println("+");

    }
}
