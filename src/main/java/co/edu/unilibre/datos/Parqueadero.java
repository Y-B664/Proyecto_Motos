package co.edu.unilibre.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parqueadero {

    private Map<String,Moto> motos = new HashMap<String,Moto>();
    private ArrayList<RegistroPago> registroPagos= new ArrayList<RegistroPago>();
    private double valorMinuto = 40;
    private int numeroPlazas = 23;

    public void modificarMotos(Map<String,Moto> motos){
        this.motos = motos;
    }
    public Map<String,Moto> obtenerPlaca(){
        return this.motos;
    }
    public void modificarRegistroPagos(ArrayList<RegistroPago> registroPagos){
        this.registroPagos = registroPagos;
    }
    public ArrayList<RegistroPago> obtenerRegistroPagos(){
        return this.registroPagos;
    }
    public void modificarValorMinuto(double valorMinuto){
        this.valorMinuto = valorMinuto;
    }
    public double obtenerValorMinuto(){
        return this.valorMinuto;
    }

    public Map<String, Moto> obtenerMotos() {
        return this.motos;
    }
    public int obtenerNumeroPlazas(){
        return this.numeroPlazas;
    }
    public void modificarNumeroPlazas(int numeroPlazas){
        this.numeroPlazas = numeroPlazas;
    }
}
