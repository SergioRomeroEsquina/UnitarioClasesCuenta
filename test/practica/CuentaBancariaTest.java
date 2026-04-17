package practica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaTest {
    private CuentaBancaria cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaBancaria("Ana García", 1000);
        System.out.println("Instanciando a Ana García con 100 euros de saldo... iniciando test ");
    }
    @AfterEach
    void tearDown() {
        System.out.println("Test finalizado ... Liberando cuentas");
        cuenta = null;
    }
    @Test
    void testConstructor() {
        assertEquals("Ana García", cuenta.getTitular());
        assertEquals(1000, cuenta.getSaldo());
        assertFalse(cuenta.isBloqueada());

        CuentaBancaria cuentaNegativa = new CuentaBancaria("Lola Mento", -300);
        assertEquals(0, cuentaNegativa.getSaldo());
    }

    @Test
    void setTitular() {
        cuenta.setTitular("Andrés Jiménez");
        assertEquals("Andrés Jiménez", cuenta.getTitular());

        cuenta.setTitular(null);
        assertEquals("Andrés Jiménez", cuenta.getTitular());

        cuenta.setTitular("");
        assertEquals("Andrés Jiménez", cuenta.getTitular());
    }

    @Test
    void setSaldo() {
        cuenta.setSaldo(100);
        assertEquals(100, cuenta.getSaldo());

        cuenta.setSaldo(-100);
        assertEquals(100, cuenta.getSaldo());
    }

    @Test
    void isBloqueada() {
    }

    @Test
    void setBloqueada() {
    }

    @Test
    void getTotalCuentasCreadas() {
    }

    @Test
    void reiniciarContador() {
    }

    @Test
    void ingresar() {
        double saldo = cuenta.getSaldo();
        assertTrue(cuenta.ingresar(200));
        assertEquals(saldo + 200, cuenta.getSaldo());

        assertFalse(cuenta.ingresar(-300));
        assertEquals(saldo + 200, cuenta.getSaldo());

        cuenta.setBloqueada(true);
        assertFalse(cuenta.ingresar(500));
        assertEquals(saldo + 200, cuenta.getSaldo());

    }

    @Test
    void retirar() {
        assertTrue(cuenta.retirar(100));
        assertEquals(-100, cuenta.getSaldo());
        assertTrue(cuenta.retirar(-50));
        assertEquals(-100, cuenta.getSaldo());
        assertFalse(cuenta.retirar(2000));
        assertEquals(-100, cuenta.getSaldo());
        cuenta.setBloqueada(true);
        assertFalse(cuenta.retirar(100));
        assertEquals(-100, cuenta.getSaldo());
    }

    @Test
    void aplicarComisionMensual() {
        assertEquals(998, cuenta.aplicarComisionMensual());
        assertEquals(998, cuenta.getSaldo());

        assertEquals(993, cuenta.aplicarComisionMensual());
        assertEquals(998, cuenta.getSaldo());

        cuenta.setSaldo(4);
        assertEquals(0, cuenta.aplicarComisionMensual());
        assertEquals(0, cuenta.getSaldo());

        cuenta.setSaldo(8000);
        assertEquals(8000, cuenta.aplicarComisionMensual());
        assertEquals(8000, cuenta.getSaldo());

        cuenta.setSaldo(3000);
        cuenta.setBloqueada(true);
        assertEquals(3000, cuenta.aplicarComisionMensual());
        assertEquals(3000, cuenta.getSaldo());
    }

    @Test
    void movimientosValidos() {
    }

    @Test
    void bloquearSiSaldoCero() {
        assertFalse(cuenta.bloquearSiSaldoCero());
        cuenta.setSaldo(0);
        assertTrue(cuenta.bloquearSiSaldoCero());
    }
}