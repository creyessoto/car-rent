package vista;

import gestor.GestorArriendoCuotas;
import gestor.GestorCliente;
import gestor.GestorVehiculos;
import modelo.ArriendoCuota;
import modelo.Cliente;
import modelo.Vehiculo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private GestorArriendoCuotas gestorArriendoCuotas = new GestorArriendoCuotas();
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

        guardarArriendoYMostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validarDatos()) {
                    return;
                }
                Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
                Vehiculo vehiculo = (Vehiculo) cmbAutomovil.getSelectedItem();
                int cantDias = Integer.parseInt(txtDias.getText());
                int cantCuotas = Integer.parseInt(txtCantCuotas.getText());
                int precioXDia = Integer.parseInt(txtPrecioXDia.getText());
                int numArriendo = 0;
                String fechaArriendo = txtFechaArriendo.getText();

                ArriendoCuota arriendoCuota = new ArriendoCuota(numArriendo,fechaArriendo,cantDias,cliente,vehiculo,cantCuotas);
                if(arriendoCuota.evaluarArriendo()){
                    gestorArriendoCuotas.agregarArriendoCuota(arriendoCuota);
                    arriendoCuota.ingresarArriendoConCuota(precioXDia);
                    trimValues();
                    actualizarCmbArriendos();
                    JOptionPane.showMessageDialog(null, "Arriendo generado correctamente", "Arriendo Creado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al generar arriendo!, verifique que el usuario este vigente y el automovil en estado D", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
    
    private boolean validarDatos() {
        if (cmbCliente.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (cmbAutomovil.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtFechaArriendo.getText().isEmpty() || !validarFecha(txtFechaArriendo.getText())) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de arriendo valida", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtDias.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad de dias mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                int cantidadDias = Integer.parseInt(txtDias.getText());
                if (cantidadDias <= 0) {
                    JOptionPane.showMessageDialog(this, "Cantidad de dias debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cantidad de dias debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (txtCantCuotas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cantidad de cuotas debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            try {
                int cantidadCuotas = Integer.parseInt(txtCantCuotas.getText());
                if (cantidadCuotas <= 0) {
                    JOptionPane.showMessageDialog(this, "Cantidad de cuotas debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cantidad de cuotas debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (txtPrecioXDia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Precio por dia debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            try {
                int precioDia = Integer.parseInt(txtPrecioXDia.getText());
                if (precioDia <= 0) {
                    JOptionPane.showMessageDialog(this, "Precio por dia debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Precio por dia debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void trimValues(){

        cmbCliente.setSelectedIndex(0);
        cmbAutomovil.setSelectedIndex(0);
        //arriendosCmbModel.setSelectedIndex(0);
        txtFechaArriendo.setText("");
        txtDias.setText("");
        txtMontoAPagar.setText("");
        txtPrecioXDia.setText("");
        txtCantCuotas.setText("");
    }

    private void actualizarCmbArriendos() {

    }

    private boolean validarFecha(String fecha) {
        String[] fechaArray = fecha.split("/");
        if (fechaArray.length != 3) {
            fechaArray = fecha.split("-");
            if (fechaArray.length != 3) {
                return false;
            }
        }
        int dia = Integer.parseInt(fechaArray[0]);
        int mes = Integer.parseInt(fechaArray[1]);
        int anio = Integer.parseInt(fechaArray[2]);
        if (dia < 1 || dia > 31) {
            return false;
        }
        if (mes < 1 || mes > 12) {
            return false;
        }
        if (anio < 1900 || anio > 2100) {
            return false;
        }
        return true;
    }




}
