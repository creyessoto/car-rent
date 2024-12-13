package vista;

import gestor.GestorCliente;
import modelo.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Ventana extends JFrame{
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
    private GestorCliente gestorCliente = new GestorCliente();
    private DefaultComboBoxModel clientesCmbModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel vehiculosCmbModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel arriendosCmbModel = new DefaultComboBoxModel();


    public Ventana() {
        setContentPane(mainPanel);
        setTitle("Car Rent");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        cmbCliente.setModel(clientesCmbModel);
        defaultCliente();

        ingresarNuevoClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarUsuario ventanaAgregarUsuario = new VentanaAgregarUsuario(gestorCliente);
                ventanaAgregarUsuario.pack();
                ventanaAgregarUsuario.setLocationRelativeTo(null);
                ventanaAgregarUsuario.setVisible(true);
                cargarClientes();
            }
        });

    }



    private void cargarClientes() {
        clientesCmbModel.removeAllElements();
        defaultCliente();
        ArrayList<Cliente> listaClientes = gestorCliente.obtenerListaClientes();
        for (Cliente cliente : listaClientes) {
            clientesCmbModel.addElement(cliente);
        }
    }

    private void defaultCliente() {
        clientesCmbModel.addElement("Seleccione CLIENTE");

    }

}
