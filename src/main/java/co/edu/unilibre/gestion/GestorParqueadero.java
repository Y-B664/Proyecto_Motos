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
    public void modificarParqueadero(Parqueadero parqueadero){
        this.parqueadero = parqueadero;
    }
    public Moto crearMoto(String cedulaPropietario,String placa, String marca){
        if(cedulaPropietario == null || placa == null || marca == null){
            return null;
        } else if (!cedulaPropietario.trim().matches("[0-9]+")) {
            return null;
        } else if (placa.isBlank() || marca.isBlank()) {
            return null;
        }
        Moto moto = new Moto(cedulaPropietario.trim(),placa.trim(),marca.trim());
        return moto ;
    }
    public Parqueadero crearParqueadero() {
        Parqueadero parqueadero= new Parqueadero();
        this.parqueadero = parqueadero;
        return parqueadero;
    }

    public boolean ocuparEspacio(Moto moto) {
        if (moto==null){
            return false;
        }
        Map<String,Moto> motos = this.parqueadero.obtenerMotos();
        System.out.println(motos.size());
        if (encontrarMoto(moto.obtenerPlaca()) != null){
            return false;
        }
        if(motos.size() == 23){
            return false;
        }
        motos.put(moto.obtenerPlaca(),moto);
        return true;
    }

    public double[] generarReporte() {
        double[] reporte = {calcularTotalMotosIngresadas(),calcularIngresosDia()};
        return reporte;
    }
    private int calcularTotalMotosIngresadas(){
        int totalMotos =parqueadero.obtenerRegistroPagos().size();
        return totalMotos;
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
        if(moto == null){
            return false;
        }
        RegistroPago registroPago = encontrarRegistroMoto(placa);
        registroPago.modificarFechaHoraSalida(LocalDateTime.now());
        return true;
    }

    public RegistroPago generarRegistroPago(Moto moto) {
        if (moto == null){
            return null;
        }
        RegistroPago registroPago = new RegistroPago();
        registroPago.modificarMoto(moto);
        registroPago.modificarFechaHoraIngreso(LocalDateTime.now());
        return registroPago;
    }
    public boolean registrarPago(RegistroPago registroPago) {
        if(registroPago==null){
            return false;
        }
        List<RegistroPago> registros = parqueadero.obtenerRegistroPagos();
        registros.add(registroPago);
        return true;
    }
    public RegistroPago generarPago(TipoPago tipoPago, String placa, double valor){
        if(tipoPago == null || placa ==null || placa.isBlank()){
            return null;
        }
        RegistroPago registroPago = encontrarRegistroMoto(placa);
        if(registroPago == null){
            return null;
        }
        registroPago.modificarTipoPago(tipoPago);
        registroPago.modificarValorPagado(valor);
        Map<String,Moto> motos = parqueadero.obtenerMotos();
        motos.remove(placa);
        return registroPago;
    }

    public double calcularValorAPagar(String placa) {
        RegistroPago registroPago =encontrarRegistroMoto(placa);
        double duracionMinutos = Duration.between(registroPago.obtenerFechaHoraIngreso(),registroPago.obtenerFechaHoraSalida()).toMinutes();
        double valorAPagar = duracionMinutos * parqueadero.obtenerValorMinuto();
        registroPago.modificarValorAPagar(valorAPagar);
        return valorAPagar;
    }


    public Moto encontrarMoto(String placa){
        System.out.println(placa);
        Map<String,Moto> motos = parqueadero.obtenerMotos();
        return motos.get(placa);
    }
    public RegistroPago encontrarRegistroMoto(String placa){
        for (RegistroPago registroPago : parqueadero.obtenerRegistroPagos()){
            if (registroPago.obtenerMoto().obtenerPlaca().equals(placa)){
                return registroPago;
            }
        }
        return null;
    }

}
