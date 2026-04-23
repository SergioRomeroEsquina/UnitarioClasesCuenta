package practica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Pedido pedido;
    @BeforeEach
    void setUp() {
        pedido = new Pedido(69,"Jhonny Silverhand", new Producto[]{new Producto("Dina",50.00,100)});
        System.out.println("Instanciando a Ana García con 100 euros de saldo... iniciando test ");
    }
    @AfterEach
    void tearDown() {
        System.out.println("Test finalizado ... Liberando cuentas");
        pedido = null;
    }

    @Test
    void testConstructor() {
        assertEquals("Jhonny Silverhand", pedido.getCliente());
        assertEquals(69, pedido.getNumeroPedido());
        assertEquals("Dina", pedido.getProductos()[0].getNombre());  // Corregido: accede al nombre del primer producto
        assertEquals(false, pedido.isUrgente());
    }

    @Test
    void setNumeroPedido() {
        pedido.setNumeroPedido(69);
        assertEquals(69,pedido.getNumeroPedido());
        pedido.setNumeroPedido(-20);
        assertEquals(69,pedido.getNumeroPedido());
    }

    @Test
    void setCliente() {
        pedido.setCliente("Jhonny Silverhand");
        assertEquals("Jhonny Silverhand", pedido.getCliente());
        pedido.setCliente("");
        assertEquals("Jhonny Silverhand", pedido.getCliente());
        pedido.setCliente(null);
        assertEquals("Jhonny Silverhand", pedido.getCliente());
    }

    @Test
    void setProductos() {
        pedido.setProductos(new Producto[]{});
        assertEquals(0,pedido.getProductos().length);
    }

    @Test
    void setUrgente() {
        pedido.setUrgente(true);
        assertTrue(pedido.isUrgente());
    }

    @Test
    void reiniciarContador() {
        pedido.reiniciarContador();
        assertEquals(0, pedido.getTotalPedidosCreados());
    }

    @Test
    void calcularTotal() {
        assertEquals(5000, pedido.calcularTotal());
        pedido.setUrgente(true);
        assertEquals(5010.0, pedido.calcularTotal());
    }

    @Test
    void contarProductosValidos() {
        assertEquals(1,pedido.contarProductosValidos());
        pedido.setProductos(new Producto[]{});
        assertEquals(0,pedido.contarProductosValidos());
    }

    @Test
    void aplicarDescuentoSiCorresponde() {
        boolean aplicado = pedido.aplicarDescuentoSiCorresponde();
        assertTrue(aplicado);
        assertEquals(45.0, pedido.getProductos()[0].getPrecio());

        Pedido pedido2 = new Pedido(70, "Test", new Producto[]{new Producto("Test", 10, 10)});  // Total 100
        assertFalse(pedido2.aplicarDescuentoSiCorresponde());
    }

    @Test
    void generarResumen() {
        assertEquals("Pedido 69 de Jhonny Silverhand - NORMAL - 1 productos", pedido.generarResumen());
        pedido.setUrgente(true);
        assertEquals("Pedido 69 de Jhonny Silverhand - URGENTE - 1 productos", pedido.generarResumen());
    }

    @Test
    void contieneProducto() {
        assertTrue(pedido.contieneProducto("Dina"));
        assertTrue(pedido.contieneProducto("dina"));
        assertFalse(pedido.contieneProducto("Otro"));
        assertFalse(pedido.contieneProducto(null));
        pedido.setProductos(null);
        assertFalse(pedido.contieneProducto("Dina"));
    }

    @Test
    void eliminarProductosSinStock() {
        assertEquals(0, pedido.eliminarProductosSinStock());
        Pedido pedido2 = new Pedido(71, "Test", new Producto[]{new Producto("SinStock", 10, 0)});
        assertEquals(1, pedido2.eliminarProductosSinStock());
        assertNull(pedido2.getProductos()[0]);
    }
}