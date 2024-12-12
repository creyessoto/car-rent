package controlador;

import modelo.Cliente;

import java.util.ArrayList;

public class ControladorCliente implements InterfaceClientes {

    private ArrayList<Cliente> clientes;

    public ControladorCliente() {
        clientes = new ArrayList<>();
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        System.out.println(cliente);
        return clientes.add(cliente);

    }
}
