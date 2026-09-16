package co.edu.unilibre.datos;

import java.time.LocalDateTime;

public class RegistroPago {
    private double valorPagado;
    private double valorAPagar;
    private TipoPago tipoPago;
    private LocalDateTime fechaHoraPago;
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;
    private Moto moto;

    public void modificarValorPagado(double valorPagado){
        this.valorPagado = valorPagado;
    }
    public double obtenerValorPagado() {
        return this.valorPagado;
    }
    public void modificarValorAPagar(double valorAPagar){
        this.valorAPagar = valorAPagar;
    }
    public double obtenerValorAPagar() {
        return this.valorAPagar;
    }
    public void modificarTipoPago(TipoPago tipoPago){
        this.tipoPago = tipoPago;
    }
    public TipoPago obtenerTipoPago() {
        return tipoPago;
    }
    public void modificarFechaHoraPago(LocalDateTime fechaHoraPago){
        this.fechaHoraPago = fechaHoraPago;
    }
    public LocalDateTime obtenerFechaHoraPago() {
        return this.fechaHoraPago;
    }
    public void modificarFechaHoraIngreso(LocalDateTime fechaHoraIngreso){
        this.fechaHoraIngreso = fechaHoraIngreso;
    }
    public LocalDateTime obtenerFechaHoraIngreso() {
        return this.fechaHoraIngreso;
    }
    public void modificarFechaHoraSalida(LocalDateTime fechaHoraSalida){
        this.fechaHoraSalida = fechaHoraSalida;
    }
    public LocalDateTime obtenerFechaHoraSalida() {
        return this.fechaHoraSalida;
    }
    public void modificarMoto(Moto moto){
        this.moto = moto;
    }
    public Moto obtenerMoto() {
        return this.moto;
    }

}
