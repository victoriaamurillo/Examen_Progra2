package examen_victoriamurillo;
public enum TipoPesquero {
    PEZ(100.0),
    CAMARON(150.0),
    LANGOSTA(500.0);

    public final double price;

    TipoPesquero(double precio) {
        this.price = precio;
    }

    public double getPrice() {
        return price;
    }
}
