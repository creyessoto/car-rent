package vista;

import gestor.GestorArriendoCuotas;
import gestor.GestorCliente;
import gestor.GestorVehiculos;
import modelo.ArriendoCuota;
import modelo.Cliente;
import modelo.CuotaArriendo;
import modelo.Vehiculo;
import vista.utils.TablaCuotasModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class Ventana extends JFrame{
    private JPanel mainPanel;
    private JComboBox cmbCliente;
    private JComboBox cmbAutomovil;
    private JButton ingresarNuevoClienteButton;
    private JTextField txtFechaArriendo;
    private JTextField txtDias;
    private JTextField txtPrecioXDia;
    private JTextField txtCantCuotas;
    private JButton guardarArriendoYMostrarButton;
    private JButton PAGARCUOTAButton;
    private JComboBox cmbArriendos;
    private JButton habilitarGeneracionDeArriendosButton;
    private JTable tblCuotas;
    private JLabel lblMontoPagado;
    private JLabel lblMontoAPagar;
    private TablaCuotasModel tablaCuotasModel;
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
        setResizable(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        cmbCliente.setModel(clientesCmbModel);
        cmbAutomovil.setModel(vehiculosCmbModel);
        cmbArriendos.setModel(arriendosCmbModel);
        cargarAutomoviles();
        defaultCliente();
        primeraCargaComboBoxArriendo();
        tablaCuotasModel = new TablaCuotasModel(new ArrayList<CuotaArriendo>());
        tblCuotas.setModel(tablaCuotasModel);
        toggleComponents(true);
        PAGARCUOTAButton.setEnabled(false);
        habilitarGeneracionDeArriendosButton.setEnabled(false);


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
                String fechaArriendo = txtFechaArriendo.getText();
                int cantDias = Integer.parseInt(txtDias.getText());
                int cantCuotas = Integer.parseInt(txtCantCuotas.getText());
                int precioXDia = Integer.parseInt(txtPrecioXDia.getText());
                int numArriendo = obtenerUltimoNumeroArriendo() + 1;
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
        habilitarGeneracionDeArriendosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleComponents(true);
                PAGARCUOTAButton.setEnabled(false);
                habilitarGeneracionDeArriendosButton.setEnabled(false);
                cmbArriendos.setSelectedIndex(0);
                limpiarTabla();
                lblMontoAPagar.setText("");
                lblMontoPagado.setText("");
            }
        });
        PAGARCUOTAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagarCuotas();
            }
        });
        cmbArriendos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED && cmbArriendos.getSelectedIndex() != 0){
                    ArriendoCuota arriendoCuota = (ArriendoCuota) cmbArriendos.getSelectedItem();
                    if(arriendoCuota != null) {
                        actualizarTablaCuotas(arriendoCuota.getCuotaArriendos());
                        int montoAPagar = obtenerMontoAPagar(arriendoCuota.getCuotaArriendos());
                        int montoPagado = obtenerMontoPagado(arriendoCuota.getCuotaArriendos());
                        lblMontoAPagar.setText(String.valueOf(montoAPagar));
                        lblMontoPagado.setText(String.valueOf(montoPagado));
                        toggleComponents(false);
                        PAGARCUOTAButton.setEnabled(true);
                        habilitarGeneracionDeArriendosButton.setEnabled(true);
                    }
                }
                if(cmbArriendos.getSelectedIndex() == 0){
                    toggleComponents(true);
                    PAGARCUOTAButton.setEnabled(false);
                    habilitarGeneracionDeArriendosButton.setEnabled(false);
                    limpiarTabla();
                    lblMontoAPagar.setText("");
                    lblMontoPagado.setText("");
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
        vehiculosCmbModel.removeAllElements();
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
    private void pagarCuotas () {
        int[] selectedRows = tblCuotas.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una cuota para pagar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArriendoCuota arriendoCuota = (ArriendoCuota) cmbArriendos.getSelectedItem();
        for (int selectedRow : selectedRows) {
            int numeroCuota = (int) tblCuotas.getValueAt(selectedRow, 0);
            if (!arriendoCuota.pagarCuota(numeroCuota)) {
                JOptionPane.showMessageDialog(this, "No es posible pagar la cuota " + numeroCuota, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        actualizarTablaCuotas(arriendoCuota.getCuotaArriendos());
        int montoPagado = obtenerMontoPagado(arriendoCuota.getCuotaArriendos());
        System.out.println(montoPagado);
        int montoAPagar = obtenerMontoAPagar(arriendoCuota.getCuotaArriendos());
        lblMontoPagado.setText(String.valueOf(montoPagado));
        lblMontoAPagar.setText(String.valueOf(montoAPagar));
        JOptionPane.showMessageDialog(this, "Cuotas pagadas correctamente", "Cuotas pagadas", JOptionPane.INFORMATION_MESSAGE);

    }

    private void trimValues(){

        cmbCliente.setSelectedIndex(0);
        cmbAutomovil.setSelectedIndex(0);
        cmbArriendos.setSelectedIndex(0);
        txtFechaArriendo.setText("");
        txtDias.setText("");
        lblMontoAPagar.setText("");
        txtPrecioXDia.setText("");
        txtCantCuotas.setText("");
    }

    private void toggleComponents(boolean toggle){
        cmbCliente.setEnabled(toggle);
        cmbAutomovil.setEnabled(toggle);
        txtFechaArriendo.setEnabled(toggle);
        txtDias.setEnabled(toggle);
        lblMontoAPagar.setEnabled(toggle);
        txtPrecioXDia.setEnabled(toggle);
        txtCantCuotas.setEnabled(toggle);
        guardarArriendoYMostrarButton.setEnabled(toggle);
        PAGARCUOTAButton.setEnabled(toggle);
        ingresarNuevoClienteButton.setEnabled(toggle);
    }
    public void actualizarTablaCuotas(ArrayList<CuotaArriendo> cuotas) {
        tablaCuotasModel.actualizarTabla(cuotas);
        tblCuotas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }


    private void actualizarCmbArriendos() {
        ItemListener[] itemListeners = cmbArriendos.getItemListeners();
        for (ItemListener itemListener : itemListeners) {
            cmbArriendos.removeItemListener(itemListener);
        }
        arriendosCmbModel.removeAllElements();
        primeraCargaComboBoxArriendo();
        List<ArriendoCuota> listaArriendos = gestorArriendoCuotas.obtenerListaArriendoCuotas();
        for (ArriendoCuota arriendoCuota : listaArriendos) {
            arriendosCmbModel.addElement(arriendoCuota);
        }
        for (ItemListener itemListener : itemListeners) {
            cmbArriendos.addItemListener(itemListener);
        }

    }

    private void primeraCargaComboBoxArriendo() {
        arriendosCmbModel.addElement("Seleccione un arriendo");
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
    private void limpiarTabla(){tablaCuotasModel.limpiarTabla();    }

    private int obtenerMontoPagado (ArrayList<CuotaArriendo> cuotas) {
        int montoPagado = 0;
        for (CuotaArriendo cuota : cuotas) {
            if (cuota.isPagada()) {
                montoPagado += cuota.getValorCuota();
            }
        }
        return montoPagado;
    }

    private int obtenerMontoAPagar (ArrayList<CuotaArriendo> cuotas) {
        int montoAPagar = 0;
        for (CuotaArriendo cuota : cuotas) {
            if (!cuota.isPagada()) {
                montoAPagar += cuota.getValorCuota();
            }
        }
        return montoAPagar;
    }

    private int obtenerUltimoNumeroArriendo() {
        int numeroArriendo = 0;
        List<ArriendoCuota> listaArriendos = gestorArriendoCuotas.obtenerListaArriendoCuotas();
        for (ArriendoCuota arriendoCuota : listaArriendos) {
            if (arriendoCuota.getNumArriendo() > numeroArriendo) {
                numeroArriendo = arriendoCuota.getNumArriendo();
            }
        }
        return numeroArriendo;
    }





}
