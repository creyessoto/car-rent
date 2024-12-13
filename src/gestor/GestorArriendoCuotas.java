package gestor;

import modelo.ArriendoCuota;

import java.util.ArrayList;

public class GestorArriendoCuotas {
    private ArrayList<ArriendoCuota> listaArriendoCuotas;

    public GestorArriendoCuotas() {
        this.listaArriendoCuotas = new ArrayList<>();
    }

    public void agregarArriendoCuota(ArriendoCuota arriendoCuota){
        listaArriendoCuotas.add(arriendoCuota);
    }

    public ArrayList<ArriendoCuota> obtenerListaArriendoCuotas(){
        return listaArriendoCuotas;
    }
}
