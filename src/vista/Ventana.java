package vista;

import gestor.GestorCliente;
import gestor.GestorVehiculos;
import modelo.Cliente;
import modelo.Vehiculo;

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
    private GestorVehiculos gestorVehiculos = new GestorVehiculos();
    private DefaultComboBoxModel clientesCmbModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel vehiculosCmbModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel arriendosCmbModel = new DefaultComboBoxModel();


    public Ventana() {
        setContentPane(mainPanel);
        setTitle("Arriendo de vehiculos Car-REnt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        cmbCliente.setModel(clientesCmbModel);
        cmbAutomovil.setModel(vehiculosCmbModel);
        cargarAutomoviles();
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

    private void cargarAutomoviles() {
        clientesCmbModel.removeAllElements();
        defaultAutomoviles();
        ArrayList<Vehiculo> listaVehiculos = gestorVehiculos.obtenerListaVehiculos();
        for (Vehiculo vehiculo : listaVehiculos) {
            vehiculosCmbModel.addElement(vehiculo);
        }
    }

    private void defaultCliente() {
        clientesCmbModel.addElement("Seleccione CLIENTE");
    }
    private void defaultAutomoviles() {
        vehiculosCmbModel.addElement("Seleccione AUTOMOVIL");
    }

}
