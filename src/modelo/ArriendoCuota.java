package modelo;

import java.util.ArrayList;

public class ArriendoCuota extends Arriendo {
    private int cantCuotas;
    private ArrayList<CuotaArriendo> cuotaArriendos;

    public ArriendoCuota(int numArriendo, String fecArr, int diasArriendo, int cantCuotas, ArrayList<CuotaArriendo> cuotaArriendos) {
        super(numArriendo, fecArr, diasArriendo);
        this.cantCuotas = cantCuotas;
        this.cuotaArriendos = cuotaArriendos;
    }

    public int getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(int cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public ArrayList<CuotaArriendo> getCuotaArriendos() {
        return cuotaArriendos;
    }

    public void setCuotaArriendos(ArrayList<CuotaArriendo> cuotaArriendos) {
        this.cuotaArriendos = cuotaArriendos;
    }

    @Override
    public String toString() {
        return "ArriendoCuota{" +
                "cantCuotas=" + cantCuotas +
                ", cuotaArriendos=" + cuotaArriendos +
                '}';
    }
}
