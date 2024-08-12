package examen_victoriamurillo;

public final class BarcoPesquero extends Barco {
    private int pecesCapturados;
    private final TipoPesquero tipoPesquero;

    public BarcoPesquero(String nombre, TipoPesquero tipoPesquero) {
        super(nombre);
        this.pecesCapturados = 0;
        this.tipoPesquero = tipoPesquero;
    }

    @Override
    public void addElemento() {
        pecesCapturados++;
    }

    @Override
    public double vaciarCobrar() {
        double total = pecesCapturados * tipoPesquero.getPrice();
        pecesCapturados = 0;
        return total;
    }

    @Override
    public double precioElemento() {
        return tipoPesquero.getPrice();
    }

    @Override
    public String toString() {
        return super.toString() + " (Pesquero: " + tipoPesquero + ", Peces capturados: " + pecesCapturados + ")";
    }
}
