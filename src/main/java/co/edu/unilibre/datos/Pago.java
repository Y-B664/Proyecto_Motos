package co.edu.unilibre.datos;

public class Pago {
    private double valorPagado;
    private TipoPago tipoPago;

    public void modificarValorPagado(double valorPagado){
        this.valorPagado = valorPagado;
    }
    public double obtenerValorPagado() {
        return this.valorPagado;
    }
    public void modificarTipoPago(TipoPago tipoPago){
        this.tipoPago = tipoPago;
    }
    public TipoPago obtenerTipoPago() {
        return tipoPago;
    }

}
