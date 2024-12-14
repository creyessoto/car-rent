package modelo;

import java.util.ArrayList;

public class ArriendoCuota extends Arriendo {
    private int cantCuotas;
    private ArrayList<CuotaArriendo> cuotaArriendos;

    public ArriendoCuota(int numArriendo, String fecArr, int diasArriendo, Cliente cliente, Vehiculo vehiculo, int cantCuotas ) {
        super(numArriendo, fecArr, diasArriendo, cliente, vehiculo);
        this.cantCuotas = cantCuotas;
        this.cuotaArriendos = new ArrayList<>();
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

    private ArrayList<CuotaArriendo> generarCuotas(int precioDia) {
        this.cuotaArriendos.clear();
        int montoTotal = this.obtenerMontoAPagar(precioDia);
        int valorCuota = Math.round(montoTotal / this.cantCuotas);

        for (int i = 0; i < this.cantCuotas; i++) {
            CuotaArriendo cuota = new CuotaArriendo(i + 1, valorCuota, false);
            this.cuotaArriendos.add(cuota);
        }
        return this.cuotaArriendos;
    }

    public boolean ingresarArriendoConCuota(int precioDia) {
        if (this.evaluarArriendo()) {
            this.getVehiculo().setCondicion('A');
            this.generarCuotas(precioDia);
            return true;
        }
        return false;
    }

    public boolean pagarCuota(int numeroCuota) {
        int montoPagado = 0;
        for (CuotaArriendo cuotas : cuotaArriendos) {
            if (cuotas.getNumCuota() == numeroCuota && !cuotas.isPagada()) {
                montoPagado += cuotas.getValorCuota();
                return cuotas.pagarCuota();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "N° Arriendo " + this.getNumArriendo() + " Cliente " + this.getCliente().getNombre() + " Vehiculo " + this.getVehiculo().getPatente();
    }
}
