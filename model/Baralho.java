package model;

import model.carta.Carta;
import model.carta.CartaNormal;
import model.carta.CartaSorte;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baralho {
    private List<Carta> cartas;
    private Random random = new Random();

    public Baralho() {
        cartas = new ArrayList<>();
        cartas.add(new CartaNormal());
        cartas.add(new CartaSorte());
        cartas.add(new CartaSorte());

    }

    public Carta sortear() {
        return cartas.get(random.nextInt(cartas.size()));
    }

}
