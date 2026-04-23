package practica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    private Producto producto;

    private CuentaBancaria cuenta;
    @BeforeEach
    void setUp() {
       producto= new Producto("Dina",50.00,100);
    }


    @Test
    void setNombre() {
        producto.setNombre("Helado");
        assertEquals("Helado", producto.getNombre());
        producto.setNombre("");
        assertEquals("", producto.getNombre());
        producto.setNombre(null);
        assertEquals(null, producto.getNombre());
    }

    @Test
    void setPrecio() {
        producto.setPrecio(500);
        assertEquals(500, producto.getPrecio());
        producto.setPrecio(-500);
        assertEquals(500, producto.getPrecio());
    }

    @Test
    void setUnidades() {
        producto.setUnidades(2);
        assertEquals(2, producto.getUnidades());
        producto.setUnidades(-1);
        assertEquals(2, producto.getUnidades());
    }
}