package controlador;

import modelo.ArriendoCuota;
import modelo.Cliente;
import modelo.Vehiculo;

public interface InterfaceArriendoCuotas extends InterfaceClientes {
    public void seleccionarCliente(Cliente cliente);
    public void seleccionarAutomovil(Vehiculo vehiculo);
    public void guardarArriendoYMostrarCuotas(ArriendoCuota arriendoCuota, int precioDia);
    public void pagarPrimerCuota();
}
