package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel mainPanel;
    private JComboBox cmbCliente;
    private JComboBox cmbAutomovil;
    private JButton ingresarNuevoClienteButton;
    private JTextField txtFechaArriendo;
    private JTextField txtDias;
    private JTextField txtMontoAPagar;
    private JTextField txtPrecioXDia;
    private JTextField txtCantCuotas;
    private JButton guardarArriendoYMostrarButton;
    private JTable table1;
    private JButton PAGARPRIMERACUOTAButton;


    public Ventana() {
        ingresarNuevoClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarUsuario ventanaAgregarUsuario = new VentanaAgregarUsuario();
                ventanaAgregarUsuario.mostrar();
            }
        });
        // Inicializar clientes en el JComboBox
        cargarClientes();
    }

    private void cargarClientes() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car-REnt");
        frame.setContentPane(new Ventana().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }


}
