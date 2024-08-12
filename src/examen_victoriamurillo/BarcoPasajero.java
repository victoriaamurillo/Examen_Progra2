package examen_victoriamurillo;
import javax.swing.JOptionPane;

public final class BarcoPasajero extends Barco {
    private final String[] pasajeros;
    private final double precioBoleto;
    private int contadorPasajeros;

    public BarcoPasajero(String nombre, int maxPasajeros, double precioBoleto) {
        super(nombre);
        this.pasajeros = new String[maxPasajeros];
        this.precioBoleto = precioBoleto;
        this.contadorPasajeros = 0;
    }

    @Override
    public void addElemento() {
        if (contadorPasajeros < pasajeros.length) {
            String nombrePasajero = JOptionPane.showInputDialog(null, "Ingrese el nombre del pasajero:");
            if (nombrePasajero != null && !nombrePasajero.isEmpty()) {
                pasajeros[contadorPasajeros] = nombrePasajero;
                contadorPasajeros++;
            } else {
                JOptionPane.showMessageDialog(null, "Nombre del pasajero no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay espacio para más pasajeros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public double vaciarCobrar() {
        double total = contadorPasajeros * precioBoleto;
        contadorPasajeros = 0;
        return total;
    }

    @Override
    public double precioElemento() {
        return precioBoleto;
    }

    @Override
    public String toString() {
        return super.toString() + " (Pasajero, Cantidad de Pasajeros que compraron boleto: " + contadorPasajeros + ")";
    }

    public void listarPasajeros() {
        if (contadorPasajeros > 0) {
            StringBuilder pasajerosList = new StringBuilder("Pasajeros:\n");
            listarPasajerosRecursivo(0, pasajerosList);
            JOptionPane.showMessageDialog(null, pasajerosList.toString(), "Lista de Pasajeros", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay pasajeros para listar.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void listarPasajerosRecursivo(int indice, StringBuilder pasajerosList) {
        if (indice < contadorPasajeros) {
            pasajerosList.append(pasajeros[indice]).append("\n");
            listarPasajerosRecursivo(indice + 1, pasajerosList);
        }
    }
}