/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_victoriamurillo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;



public class Muelle extends JFrame {
    private final ArrayList<Barco> barcos;

    public Muelle() {
        barcos = new ArrayList<>();
        Guivisual();
    }

    private void Guivisual() {
        setTitle("Victoria's Ship Company");
        setSize(500, 400); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Victoria's Ship Company", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(30, 144, 255));  
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 15, 15)); 
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregarBarco = crearBoton("Agregar Barco");
        btnAgregarBarco.addActionListener(e -> agregarBarco());

        JButton btnAgregarElemento = crearBoton("Agregar Elemento a Barco");
        btnAgregarElemento.addActionListener(e -> agregarElemento());

        JButton btnVaciarBarco = crearBoton("Vaciar y Cobrar Barco");
        btnVaciarBarco.addActionListener(e -> vaciarBarco());

        JButton btnBarcosDesde = crearBoton("Listar Barcos Desde Año");
        btnBarcosDesde.addActionListener(e -> listarBarcosDesde());

        panelBotones.add(btnAgregarBarco);
        panelBotones.add(btnAgregarElemento);
        panelBotones.add(btnVaciarBarco);
        panelBotones.add(btnBarcosDesde);

        add(panelBotones, BorderLayout.CENTER);

        JButton btnSalir = crearBoton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));

        JPanel panelSalir = new JPanel();
        panelSalir.add(btnSalir);
        add(panelSalir, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(250, 40)); 
        boton.setBackground(new Color(30, 144, 255));  
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(25, 120, 210), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return boton;
    }

    private void agregarBarco() {
        String tipo = JOptionPane.showInputDialog(this, "Tipo de barco (PESQUERO/PASAJERO):");
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");

        if (buscarBarco(nombre).isPresent()) {
            JOptionPane.showMessageDialog(this, "El nombre del barco ya existe.");
            return;
        }

        if ("PESQUERO".equalsIgnoreCase(tipo)) {
            TipoPesquero tipoPesquero = (TipoPesquero) JOptionPane.showInputDialog(this,
                    "Seleccione el tipo de pesquero:",
                    "Tipo de Pesquero",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TipoPesquero.values(),
                    TipoPesquero.PEZ);
            barcos.add(new BarcoPesquero(nombre, tipoPesquero));
        } else if ("PASAJERO".equalsIgnoreCase(tipo)) {
            int maxPasajeros = Integer.parseInt(JOptionPane.showInputDialog(this, "Capacidad de pasajeros:"));
            double precioBoleto = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio del boleto:"));
            barcos.add(new BarcoPasajero(nombre, maxPasajeros, precioBoleto));
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de barco no válido.");
        }
    }

    private void agregarElemento() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");
        Optional<Barco> barco = buscarBarco(nombre);

        barco.ifPresentOrElse(
                Barco::addElemento,
                () -> JOptionPane.showMessageDialog(this, "Barco no encontrado.")
        );
    }

    private void vaciarBarco() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");
        Optional<Barco> barco = buscarBarco(nombre);

        barco.ifPresentOrElse(b -> {
            double totalCobrado = b.vaciarCobrar();
            JOptionPane.showMessageDialog(this, "Total cobrado: $" + totalCobrado);
            if (b instanceof BarcoPasajero) {
                ((BarcoPasajero) b).listarPasajeros();
            }
        }, () -> JOptionPane.showMessageDialog(this, "Barco no encontrado."));
    }

    private void listarBarcosDesde() {
        int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Año:"));
        StringBuilder resultado = new StringBuilder("Barcos desde " + year + ":\n");
        listarBarcosRecursivo(0, year, resultado);

        if (resultado.length() == ("Barcos desde " + year + ":\n").length()) {
            resultado.append("No se encontraron barcos.");
        }

        JOptionPane.showMessageDialog(this, resultado.toString());
    }

    private void listarBarcosRecursivo(int indice, int year, StringBuilder resultado) {
        if (indice < barcos.size()) {
            Barco barco = barcos.get(indice);
            if (barco.getFechaCirculacion().getYear() >= year) {
                resultado.append(barco.getNombre()).append(" - ").append(barco.getFechaCirculacion()).append("\n");
            }
            listarBarcosRecursivo(indice + 1, year, resultado);
        }
    }

    private Optional<Barco> buscarBarco(String nombre) {
        return barcos.stream().filter(b -> b.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Muelle ex = new Muelle();
            ex.setVisible(true);
        });
    }
}