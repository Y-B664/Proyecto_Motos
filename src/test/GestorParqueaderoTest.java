import co.edu.unilibre.datos.Moto;
import co.edu.unilibre.datos.Parqueadero;

import co.edu.unilibre.datos.RegistroPago;
import co.edu.unilibre.datos.TipoPago;
import co.edu.unilibre.gestion.GestorParqueadero;
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
        assertTrue(gestorParqueadero.darSalidaMoto("HHH-980", TipoPago.DAVIPLATA));
    }
    @Test
    public void generarRegistroPagoOk(){
        RegistroPago registroPago = new RegistroPago();
        assertEquals(registroPago,gestorParqueadero.generarRegistroPago(new Moto()));
    }
    @Test
    public void calcularValorAPagarOk(){
        assertEquals(5000.5,gestorParqueadero.calcularValorAPagar(new RegistroPago()));
        ;
    }
    @Test
    public void registrarPagoOk(){
        assertTrue(gestorParqueadero.registrarPago(new RegistroPago()));
    }

}
