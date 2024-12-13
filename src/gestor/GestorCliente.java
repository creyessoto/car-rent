package gestor;

import modelo.Cliente;

import java.util.ArrayList;

public class GestorCliente implements InterfaceClientes {

    private ArrayList<Cliente> clientes;

    public GestorCliente() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente("12345678-9", "Juan Pérez", true));
        clientes.add(new Cliente("98765432-1", "Ana Gómez", false));
        clientes.add(new Cliente("45678912-3", "Carlos Ruiz", true));

    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        System.out.println(cliente);
        return clientes.add(cliente);

    }
}
