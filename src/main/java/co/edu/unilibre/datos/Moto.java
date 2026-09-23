package co.edu.unilibre.datos;

public class Moto {
    private String cedulaPropietario;
    private String placa;
    private String marca;

    public Moto() {

    }
    public Moto(String cedulaPropietario, String placa, String marca) {
        this.cedulaPropietario = cedulaPropietario;
        this.placa = placa;
        this.marca = marca;
    }

    public void modificarCedulaPropietario(String cedulaPropietario){
        this.cedulaPropietario = cedulaPropietario;
    }
    public String obtenerCedulaPropietario(){
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
