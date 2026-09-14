package co.edu.unilibre.datos;

public class Moto {
    private int cedulaPropietario;
    private String placa;
    private String marca;

    public void modificarCedulaPropietario(int cedulaPropietario){
        this.cedulaPropietario = cedulaPropietario;
    }
    public int obtenerCedulaPropietario(){
        return this.cedulaPropietario;
    }
    public void modificarPlaca(String placa){
        this.placa = placa;
    }
    public String obtenerPlaca(){
        return this.placa;
    }
    public void modificarMarca(String marca){
        this.marca = marca;
    }
    public String obtenerMarca(){
        return this.marca;
    }
}
