package model.casa;

public class CasaNormal extends Casa {

    private final int numero;

    public CasaNormal (int numero) {
        this.numero = numero;
    }

    @Override
    public void aplicarEfeito () {}

    public int getNumero() {
        return numero;
    }

    
}