package model;

import java.util.ArrayList;
import java.util.List;
import model.casa.Casa;
import model.casa.CasaNormal;

public class Tabuleiro {
    private List<Casa> casas;

    public Tabuleiro () {
       
        this.casas = new ArrayList<> ();

    }

    public void preenchertabuleiro () {
         for (int i = 0; i <=3; i++) {
            this.casas.add(new CasaNormal(i));
        }
    }

    public List<Casa> getCasas() {
        return casas;
    }

    
}
