import co.edu.unilibre.datos.Moto;
import co.edu.unilibre.datos.Parqueadero;

import co.edu.unilibre.datos.RegistroPago;
import co.edu.unilibre.datos.TipoPago;
import co.edu.unilibre.gestion.GestorParqueadero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GestorParqueaderoTest {
    Parqueadero parqueadero;
    GestorParqueadero gestorParqueadero;
    Moto moto;
    @BeforeEach
    public void setUp(){
        parqueadero = new Parqueadero();
        gestorParqueadero = new GestorParqueadero();

    }
    // Crear moto
    @Test
    public void crearMotoOk(){
        moto = gestorParqueadero.crearMoto("1234567891","QWE-45C","Yamaha");
        assertNotNull(moto);
    }
    @Test
    public void crearMotoCedulaTexto(){
        assertNull(gestorParqueadero.crearMoto("ABCDEFG","QWE-45C","Yamaha"));
    }
    @Test
    public void crearMotoCedulaTextoNumeros(){
        assertNull(gestorParqueadero.crearMoto("1ABC3456DEFG","QWE-45C","Yamaha"));
    }
    @Test
    public void crearMotoCedulaNula(){
        assertNull(gestorParqueadero.crearMoto(null,"QWE-45C","Yamaha"));
    }
    @Test
    public void crearMotoPlacaVaciaNula(){
        assertNull(gestorParqueadero.crearMoto("1234567891",null,"Yamaha"));
    }
    @Test
    public void crearMotoMarcaVaciaNula(){
        assertNull(gestorParqueadero.crearMoto("1234567891","QWE-45C",null));
    }
    @Test
    public void crearMotoCedulaVacia(){
        assertNull(gestorParqueadero.crearMoto(" ","QWE-45C","Yamaha"));
    }
    @Test
    public void crearMotoPlacaVacia(){
        assertNull(gestorParqueadero.crearMoto("1234567891"," ","Yamaha"));
    }
    @Test
    public void crearMotoMarcaVacia(){
        assertNull(gestorParqueadero.crearMoto("1234567891","QWE-45C", " "));
    }

    //CrearParqueadero

    @Test
    public void crearParqueaderoOk(){
        assertNotNull(gestorParqueadero.crearParqueadero());
        assertNotNull(gestorParqueadero.obtenerParqueadero());
    }

    // asignar moto
    @Test
    public void ocuparEspacioOk(){
        Moto moto = new Moto("1234567891","QWE-45C","Yamaha");
        gestorParqueadero.crearParqueadero();
        assertTrue(gestorParqueadero.ocuparEspacio(moto));
        Map<String,Moto> motos = gestorParqueadero.obtenerParqueadero().obtenerMotos();
        assertEquals(moto,motos.get(moto.obtenerPlaca()));
    }
    @Test
    public void ocuparEspacioMotoNula(){
        gestorParqueadero.crearParqueadero();
        assertFalse(gestorParqueadero.ocuparEspacio(null));
    }
    @Test
    public void ocuparEspacioParqueaderoLleno(){
        gestorParqueadero.crearParqueadero();
        boolean respuesta = true;
        for (int i = 0; i<gestorParqueadero.obtenerParqueadero().obtenerNumeroPlazas()+1;i++){
            Moto moto1 = new Moto("123456789","HHH-5"+i,"Yamaha");
            respuesta = gestorParqueadero.ocuparEspacio(moto1);

        }
        assertFalse(respuesta);
    }

    @Test
    public void ocuparEspacioConMotoYaRegistrada(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        boolean respuesta = gestorParqueadero.ocuparEspacio(moto1);
        respuesta = gestorParqueadero.ocuparEspacio(moto1);
        assertFalse(respuesta);
    }

    //generar reporte
    @Test
    public void generarReporte(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        Moto moto2 = gestorParqueadero.crearMoto("1987654321","BBB-222", "Honda");
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        RegistroPago registroPago2 = gestorParqueadero.generarRegistroPago(moto2);
        registroPago1.modificarValorPagado(5000.0);
        registroPago2.modificarValorPagado(3500.0);
        gestorParqueadero.registrarPago(registroPago1);
        gestorParqueadero.registrarPago(registroPago2);
        double[] reporte = gestorParqueadero.generarReporte();
        assertEquals(2,reporte[0]);
        assertEquals(8500,reporte[1]);

    }

    // dar salida moto
    @Test
    public void darSalidaMotoOk(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        Moto moto2 = gestorParqueadero.crearMoto("1987654321","BBB-222", "Honda");
        gestorParqueadero.ocuparEspacio(moto1);
        gestorParqueadero.ocuparEspacio(moto2);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        RegistroPago registroPago2 = gestorParqueadero.generarRegistroPago(moto2);
        gestorParqueadero.registrarPago(registroPago1);
        gestorParqueadero.registrarPago(registroPago2);
        gestorParqueadero.ocuparEspacio(moto1);
        gestorParqueadero.ocuparEspacio(moto2);
        assertTrue(gestorParqueadero.darSalidaMoto("AAA-111"));
    }
    @Test
    public void darSalidaMotoInexistente(){
        gestorParqueadero.crearParqueadero();
        assertFalse(gestorParqueadero.darSalidaMoto("AAA-111"));
    }
    @Test
    public void darSalidaMotoNula(){
        gestorParqueadero.crearParqueadero();
        assertFalse(gestorParqueadero.darSalidaMoto(null));
    }
    // generar registro
    @Test
    public void generarRegistroPagoOk(){
        gestorParqueadero.crearParqueadero();
        Moto moto = new Moto("1234567891","QWE-45C","Yamaha");
        RegistroPago registroPago = new RegistroPago();
        registroPago.modificarMoto(moto);
        LocalDateTime tiempo =LocalDateTime.now().minusMinutes(60);
        registroPago.modificarFechaHoraIngreso(tiempo);
        RegistroPago registroPagoPrueba = gestorParqueadero.generarRegistroPago(moto);
        assertNotNull(registroPagoPrueba);
        registroPagoPrueba.modificarFechaHoraIngreso(tiempo);
        assertEquals(registroPago.obtenerMoto().obtenerPlaca(),registroPagoPrueba.obtenerMoto().obtenerPlaca());
    }
    @Test
    public void generarRegistroPagoMotoNula(){
        gestorParqueadero.crearParqueadero();
        Moto moto = new Moto("1234567891","QWE-45C","Yamaha");
        RegistroPago registroPago = new RegistroPago();
        registroPago.modificarMoto(moto);
        LocalDateTime tiempo =LocalDateTime.now().minusMinutes(60);
        registroPago.modificarFechaHoraIngreso(tiempo);
        RegistroPago registroPagoPrueba = gestorParqueadero.generarRegistroPago(null);
        assertNull(registroPagoPrueba);
    }

    @Test
    public void calcularValorAPagarOk(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        registroPago1.modificarFechaHoraIngreso(LocalDateTime.now().minusMinutes(60));
        gestorParqueadero.darSalidaMoto(moto1.obtenerPlaca());
        double valorAPagar = gestorParqueadero.calcularValorAPagar(moto1.obtenerPlaca());
        double valorCalculado = 60 * gestorParqueadero.obtenerParqueadero().obtenerValorMinuto();
        assertEquals(valorCalculado,valorAPagar);
        assertEquals(valorCalculado,registroPago1.obtenerValorAPagar());
    }
    @Test
    public void registrarPagoOk(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        assertTrue(gestorParqueadero.registrarPago(registroPago1));
    }
    @Test
    public void registrarPagoRegistroNulo(){
        gestorParqueadero.crearParqueadero();
        assertFalse(gestorParqueadero.registrarPago(null));
    }

    @Test
    public void encontrarMotoOk(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        assertNotNull(gestorParqueadero.encontrarMoto("AAA-111"));
    }
    @Test
    public void encontrarMotoInexistente(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        assertNull(gestorParqueadero.encontrarMoto("AAA"));
    }
    @Test
    public void encontrarRegistroMotoInexistente(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        assertNotNull(gestorParqueadero.encontrarRegistroMoto("AAA-111"));
    }
    @Test
    public void encontrarRegistroMotoOk(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        assertNull(gestorParqueadero.encontrarRegistroMoto("AAA"));
    }
    @Test
    public void generarPagoOk(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        registroPago1 = gestorParqueadero.generarPago(TipoPago.EFECTIVO,"AAA-111",345000);
        assertNotNull(registroPago1);
    }

    @Test
    public void generarPagoPlacaNoExiste(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        registroPago1 = gestorParqueadero.generarPago(TipoPago.EFECTIVO,"No-Placa",345000);
        assertNull(registroPago1);
    }
    @Test
    public void generarPagoTipoPagoNulo(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        registroPago1 = gestorParqueadero.generarPago(null,"AAA-111",345000);
        assertNull(registroPago1);
    }
    @Test
    public void generarPagoPlacaVacia(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        registroPago1 = gestorParqueadero.generarPago(TipoPago.EFECTIVO," ",345000);
        assertNull(registroPago1);
    }
    @Test
    public void generarPagoPlacaNula(){
        gestorParqueadero.crearParqueadero();
        Moto moto1 = gestorParqueadero.crearMoto("1234567891","AAA-111", "Yamaha");
        gestorParqueadero.ocuparEspacio(moto1);
        RegistroPago registroPago1 = gestorParqueadero.generarRegistroPago(moto1);
        gestorParqueadero.registrarPago(registroPago1);
        registroPago1 = gestorParqueadero.generarPago(TipoPago.EFECTIVO,null,345000);
        assertNull(registroPago1);
    }


}
