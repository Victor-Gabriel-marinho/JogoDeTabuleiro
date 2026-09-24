package model;

import java.util.ArrayList;
import java.util.List;
import model.casa.Casa;
import model.casa.CasaMagica;
import model.casa.CasaNormal;
import model.casa.CasaPerdeAVez;
import model.casa.CasaSorte;
import model.casa.CasaSurpresa;
import model.casa.CasaVoltarInicio;

public class Tabuleiro {
    private List<Casa> casas;

    public Tabuleiro () {
       
        this.casas = new ArrayList<> ();

    }

    public void preenchertabuleiro () {
         for (int i = 0; i <=39; i++) {

            switch (i) {
                case 10, 25, 18 -> this.casas.add(new CasaPerdeAVez(i));
                case 13 -> this.casas.add(new CasaSurpresa(i));
                case 5, 15, 30 -> this.casas.add(new CasaSorte(i));
                case 17, 27 -> this.casas.add(new CasaVoltarInicio(i));
                case 20, 35 -> this.casas.add(new CasaMagica(i));
                default -> this.casas.add(new CasaNormal(i));
            }
        }
    }

    public List<Casa> getCasas() {
        return casas;
    }

    public Casa getCasa(int numero) {
        return casas.get(numero);
    }

    
}
