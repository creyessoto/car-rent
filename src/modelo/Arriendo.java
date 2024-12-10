package modelo;

public class Arriendo {
    private int numArriendo;
    private String fecArr;
    private int diasArriendo;

    public Arriendo(int numArriendo, String fecArr, int diasArriendo) {
        this.numArriendo = numArriendo;
        this.fecArr = fecArr;
        this.diasArriendo = diasArriendo;
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

    @Override
    public String toString() {
        return "Arriendo{" +
                "numArriendo=" + numArriendo +
                ", fecArr='" + fecArr + '\'' +
                ", diasArriendo=" + diasArriendo +
                '}';
    }
}
