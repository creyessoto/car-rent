package gestor;

import modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class GestorCliente {

    private ArrayList<Cliente> clientes;

    public GestorCliente() {
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        System.out.println(cliente);
        clientes.add(cliente);
    }

    public ArrayList<Cliente> obtenerListaClientes() {
        return clientes;
    }
    public boolean validarCliente(String cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }
}
