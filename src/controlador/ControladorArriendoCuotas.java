package controlador;

import modelo.ArriendoCuota;
import modelo.Cliente;
import modelo.CuotaArriendo;
import modelo.Vehiculo;

import javax.swing.*;
import java.util.ArrayList;

public class ControladorArriendoCuotas implements InterfaceArriendoCuotas {

    private ArrayList<Cliente> clientes;

    public ControladorArriendoCuotas(ArrayList<Cliente> clientes) {
        clientes = new ArrayList<>();
    }

    @Override
    public void seleccionarCliente(Cliente cliente) {

    }

    @Override
    public void seleccionarAutomovil(Vehiculo vehiculo) {

    }

    @Override
    public void guardarArriendoYMostrarCuotas(ArriendoCuota arriendoCuota, int precioDia) {

    }

    @Override
    public void pagarPrimerCuota() {

    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        return clientes.add(cliente);
    }
}
