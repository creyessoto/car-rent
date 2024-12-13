package modelo;

public abstract class Arriendo {
    private int numArriendo;
    private String fecArr;
    private int diasArriendo;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Arriendo(int numArriendo, String fecArr, int diasArriendo) {
        this.numArriendo = numArriendo;
        this.fecArr = fecArr;
        this.diasArriendo = diasArriendo;
    }

    public Arriendo(int numArriendo, String fecArr, int diasArriendo, Cliente cliente, Vehiculo vehiculo) {
        this.numArriendo = numArriendo;
        this.fecArr = fecArr;
        this.diasArriendo = diasArriendo;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    public int getNumArriendo() {
        return numArriendo;
    }

    public void setNumArriendo(int numArriendo) {
        this.numArriendo = numArriendo;
    }

    public String getFecArr() {
        return fecArr;
    }

    public void setFecArr(String fecArr) {
        this.fecArr = fecArr;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int obtenerMontoAPagar(int precioDia) {
        return precioDia * this.diasArriendo;
    }

    public boolean evaluarArriendo() {
        return this.cliente.isVigente() && this.vehiculo.getCondicion() == 'D';
    }

    @Override
    public String toString() {
        return "Arriendo{" +
                "numArriendo=" + numArriendo +
                ", fecArr='" + fecArr + '\'' +
                ", diasArriendo=" + diasArriendo +
                ", cliente=" + cliente +
                ", vehiculo=" + vehiculo +
                '}';
    }
}
