package co.edu.unilibre.datos;

public class Moto {
    private int cedulaPropietario;
    private String placa;
    private String marca;
    private byte[]  fotoPlaca;

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
    public void modificarFotoPlaca(byte[] fotoPlaca){
        this.fotoPlaca = fotoPlaca;
    }
    public byte[] obtenerFotoPlaca(){
        return this.fotoPlaca;
    }
}
