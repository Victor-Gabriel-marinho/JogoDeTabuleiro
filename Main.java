
import model.Tabuleiro;
import model.casa.Casa;

public class Main {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final static Tabuleiro t = new Tabuleiro();

    public static void main(String[] args) {

        int i = 0;
        t.preenchertabuleiro();

        for (Casa c : t.getCasas()) {
            System.out.println("+----+----+----+----+----+----+----+----+----+----+----+");
            for (int j = 0; j < 10; j++) {

                System.out.print(" | " + i + " |");

                if (i == 10) {
                    System.out.print(ANSI_RED + "| " + i + " |" + ANSI_RESET);
                }

                i++;
            }
            System.out.println("");
        }

    }
}
