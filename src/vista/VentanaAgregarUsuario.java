package vista;

import controlador.ControladorCliente;
import modelo.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarUsuario {
    private JPanel mainPanel;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JButton AGREGARButton;
    private JCheckBox chkVigente;

    ControladorCliente ctrlCliente = new ControladorCliente();

    public VentanaAgregarUsuario() {
        AGREGARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();
                cliente.setCedula(txtCedula.getText().trim());
                cliente.setNombre(txtNombre.getText().trim());
                if(chkVigente.isSelected()){
                    cliente.setVigente(true);
                }else {
                    cliente.setVigente(false);
                }
                if(ctrlCliente.agregarCliente(cliente)){
                    javax.swing.JOptionPane.showMessageDialog(null,"Cliente agregado");
                }

            }
        });
    }

    public void mostrar(){
        JFrame frame = new JFrame("Agregar usuario");
        frame.setContentPane(new VentanaAgregarUsuario().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


}
