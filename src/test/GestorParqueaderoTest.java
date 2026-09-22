import co.edu.unilibre.datos.Moto;
import co.edu.unilibre.datos.Parqueadero;

import co.edu.unilibre.datos.RegistroPago;
import co.edu.unilibre.gestion.GestorParqueadero;
import co.edu.unilibre.gestion.placa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorParqueaderoTest {
    Parqueadero parqueadero;
    GestorParqueadero gestorParqueadero;
    @BeforeEach
    public void setUp(){
        parqueadero = new Parqueadero();
        gestorParqueadero = new GestorParqueadero();
    }
    @Test
    public void crearMotoOk(){
        assertNotNull(gestorParqueadero.crearMoto(0,null,null));
    }
    @Test
    public void crearParqeuaderoOk(){
        assertNotNull(gestorParqueadero.crearParqueadero());
    }
    @Test
    public void ocuparEspacioOk(){
        assertTrue(gestorParqueadero.ocuparEspacio(new Moto()));
    }
    @Test
    public void generarReporte(){
        gestorParqueadero.generarReporte();
    }
    @Test
    public void darSalidaMotoOk(){
        assertTrue(gestorParqueadero.darSalidaMoto("HHH-980"));
    }
    @Test
    public void generarRegistroPagoOk(){
        RegistroPago registroPago = new RegistroPago();
        Moto moto = new Moto();
        assertEquals(registroPago,gestorParqueadero.generarRegistroPago(moto));
    }
    @Test
    public void calcularValorAPagarOk(){

        assertEquals(0.0,gestorParqueadero.calcularValorAPagar("HHH-123"));
        ;
    }
    @Test
    public void registrarPagoOk(){

        assertTrue(gestorParqueadero.registrarPago(new RegistroPago()));
    }

}
