package controlador;

import modelo.CuotaArriendo;

public interface InterfacePagarCuotaArriendo {
    public void seleccionarCuotaAPagar(CuotaArriendo cuota);
    public boolean ejecutarPagoCuota(CuotaArriendo cuota);
}
