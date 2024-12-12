package controlador;

import modelo.Cliente;

public class ControladorCliente implements InterfaceClientes {

    @Override
    public void agregarCliente(String cedula, String nombre) {
        new Cliente(cedula,nombre,true);
        System.out.println("cliente agregado con exito");
    }
}
