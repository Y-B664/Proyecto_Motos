package co.edu.unilibre.datos;

import java.util.ArrayList;

public class Parqueadero {

    private ArrayList<Moto> motos = new ArrayList<Moto>();
    private ArrayList<RegistroPago> registroPagos= new ArrayList<RegistroPago>();
    private double valorMinuto = 40;

    public void modificarMotos(ArrayList<Moto> motos){

        this.motos = motos;
    }
    public ArrayList<Moto> obtenerPlaca(){

        return this.motos;
    }
    public void modificarRegistroPagos(ArrayList<RegistroPago> motos){
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

}
