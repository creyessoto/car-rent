package gestor;

import modelo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class GestorVehiculos {
    private ArrayList<Vehiculo> listaVehiculos;

    public GestorVehiculos() {
        this.listaVehiculos = new ArrayList<>();

        //Automoviles predefinidos
        listaVehiculos.add(new Vehiculo("ABC123",'D'));
        listaVehiculos.add(new Vehiculo("XYZ789",'D'));
        listaVehiculos.add(new Vehiculo("DEF456",'A'));
        listaVehiculos.add(new Vehiculo("GHI321",'A'));
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        listaVehiculos.add(vehiculo);
    }

    public ArrayList<Vehiculo> obtenerListaVehiculos() {
        System.out.println(listaVehiculos);
        return listaVehiculos;
    }

    public boolean validarVehiculo(String patente) {
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getPatente().equals(patente)) {
                return true;
            }
        }
        return false;
    }
}
