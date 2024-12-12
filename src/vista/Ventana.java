package vista;

import javax.swing.*;

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
