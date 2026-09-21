package co.edu.unilibre.gestion;

import co.edu.unilibre.datos.Moto;
import co.edu.unilibre.datos.Parqueadero;
import co.edu.unilibre.datos.RegistroPago;
import co.edu.unilibre.datos.TipoPago;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class GestorParqueadero {
    private Parqueadero parqueadero;

    public Parqueadero obtenerParqueadero(){
        return this.parqueadero;
    }
    public Moto crearMoto(int cedulaPropietario,String placa, String marca){
        Moto moto = new Moto(cedulaPropietario,placa,marca);
        return moto ;
    }
    public Parqueadero crearParqueadero() {
        Parqueadero parqueadero= new Parqueadero();
        this.parqueadero = parqueadero;
        return parqueadero;
    }

    public boolean ocuparEspacio(Moto moto) {
        Map<String,Moto> motos = this.parqueadero.obtenerMotos();
        motos.put(moto.obtenerPlaca(),moto);
        return true;
    }

    public void generarReporte() {

    }
    private double calcularIngresosDia(){
        double total = 0;
        for (RegistroPago registroPago : parqueadero.obtenerRegistroPagos()){
            total += registroPago.obtenerValorPagado();
        }
        return total;
    }

    public boolean darSalidaMoto(String placa) {
        Moto moto = encontrarMoto(placa);
        RegistroPago registroPago = encontraRegistroMoto(placa);
        registroPago.modificarFechaHoraSalida(LocalDateTime.now());
        if(moto== null){
            return false;
        }
        return true;
    }

    public RegistroPago generarRegistroPago(Moto moto) {
        RegistroPago registroPago = new RegistroPago();
        registroPago.modificarMoto(moto);
        registroPago.modificarFechaHoraIngreso(LocalDateTime.now());
        return null;
    }
    public void generarPago(TipoPago tipoPago, String placa, double valor){
        RegistroPago registroPago = encontraRegistroMoto(placa);
    }

    public double calcularValorAPagar(String placa) {
        RegistroPago registroPago =encontraRegistroMoto(placa);
        double duracionMinutos = Duration.between(registroPago.obtenerFechaHoraIngreso(),registroPago.obtenerFechaHoraSalida()).toMinutes();
        double valorAPagar = duracionMinutos * parqueadero.obtenerValorMinuto();
        registroPago.modificarValorAPagar(valorAPagar);
        return valorAPagar;
    }

    public boolean registrarPago(RegistroPago registroPago) {
        List<RegistroPago> registros = parqueadero.obtenerRegistroPagos();
        registros.add(registroPago);
        return true;
    }
    private Moto encontrarMoto(String placa){
        Map<String,Moto> motos =parqueadero.obtenerMotos();
        return motos.get(placa);
    }
    private RegistroPago encontrarRegistroMoto(String placa){
        for (RegistroPago registroPago : parqueadero.obtenerRegistroPagos()){
            if (registroPago.obtenerMoto().obtenerPlaca()==placa){
                return registroPago;
            }
        }
        return null;
    }
}
