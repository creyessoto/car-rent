package vista;

import gestor.GestorCliente;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarUsuario extends JDialog {
    private JPanel mainPanel;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JButton AGREGARButton;
    private JCheckBox chkVigente;
    private GestorCliente gestorCliente;


    public VentanaAgregarUsuario(GestorCliente gestorCliente) {
        setContentPane(mainPanel);
        setModal(true);
        setResizable(false);
        getRootPane().setDefaultButton(AGREGARButton);

        this.gestorCliente = gestorCliente;

        AGREGARButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
    }

    private void agregar() {
        if(!validarEntradaDatos()) {
            return;
        }
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        boolean vigente = chkVigente.isSelected();
        try {
            if (gestorCliente.validarCliente(cedula)) {
                JOptionPane.showMessageDialog(this, "Cliente ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente nuevoCliente = new Cliente(cedula, nombre, vigente);
            gestorCliente.agregarCliente(nuevoCliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente!", "Cliente Agregado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private boolean validarEntradaDatos () {
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cedula", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
