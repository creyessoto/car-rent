package controlador;

import modelo.ArriendoCuota;
import modelo.Cliente;
import modelo.Vehiculo;

public interface InterfaceArriendoCuotas extends InterfaceClientes {
    void seleccionarCliente(Cliente cliente);
    void seleccionarAutomovil(Vehiculo vehiculo);
    void guardarArriendoYMostrarCuotas(ArriendoCuota arriendoCuota, int precioDia);
    void pagarPrimerCuota();
}
