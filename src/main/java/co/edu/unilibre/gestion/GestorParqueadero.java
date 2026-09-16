package co.edu.unilibre.gestion;

import co.edu.unilibre.datos.Moto;
import co.edu.unilibre.datos.Parqueadero;
import co.edu.unilibre.datos.RegistroPago;
import co.edu.unilibre.datos.TipoPago;

import java.util.ArrayList;

public class GestorParqueadero {
    private Parqueadero parqueadero;

    public Parqueadero obtenerParqueadero(){

        return this.parqueadero;
    }
    public Moto crearMoto(int cedulaPropietario,String placa, String marca){
        return null;
    }
    public Parqueadero crearParqueadero() {
        return null;
    }

    public boolean ocuparEspacio(Moto moto) {
        return false;
    }

    public void generarReporte() {
    }
    private double calcularIngresosDia(RegistroPago registroPago){
        return 0.0;
    }

    public boolean darSalidaMoto(String placa, TipoPago tipoPago) {
        return false;
    }

    public RegistroPago generarRegistroPago(Moto moto) {
        return null;
    }

    public double calcularValorAPagar(RegistroPago registroPago) {
        return 0.0;
    }

    public boolean registrarPago(RegistroPago registroPago) {
        return false;
    }
}
