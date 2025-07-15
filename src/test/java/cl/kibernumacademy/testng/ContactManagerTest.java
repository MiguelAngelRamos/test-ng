package cl.kibernumacademy.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import cl.kibernumacademy.testng.contact.Contact;

import org.testng.annotations.Parameters;
import java.util.List;

public class ContactManagerTest {
    private ContactManager manager;

    @BeforeClass
    public void setUp() {
        manager = new ContactManager();
    }

    @AfterClass
    public void tearDown() {
        manager = null;
    }

    @BeforeMethod
    public void limpiarContactos() {
        manager.getContactos().clear();
    }

    @Test
    @Parameters({"nombre", "correo"})
    public void testAgregarContacto(String nombre, String correo) {
        Contact contacto = new Contact(nombre, correo);
        manager.agregarContacto(contacto);
        Assert.assertTrue(manager.getContactos().contains(contacto), "El contacto debe estar en la lista");
    }

    @Test
    @Parameters({"nombre"})
    public void testDesactivarContacto(String nombre) {
        Contact contacto = new Contact(nombre, "correo@ejemplo.com");
        manager.agregarContacto(contacto);
        boolean desactivado = manager.desactivarContacto(nombre);
        Assert.assertTrue(desactivado, "El contacto debe desactivarse");
        Assert.assertEquals(contacto.getEstado(), Contact.Estado.INACTIVO);
    }

    @Test
    public void testDesactivarContactoInexistente() {
        boolean desactivado = manager.desactivarContacto("no existe");
        Assert.assertFalse(desactivado, "No debe desactivar contacto inexistente");
    }

    @Test
    public void testFiltrarPorEstado() {
        // NOTA: Este test no utiliza los parámetros definidos en testng.xml porque necesita crear contactos específicos
        // con valores fijos para controlar el escenario y validar el filtrado por estado.
        Contact c1 = new Contact("c1", "c1@mail.com"); // Creamos el contacto c1 con estado ACTIVO por defecto
        Contact c2 = new Contact("c2", "c2@mail.com"); // Creamos el contacto c2 con estado ACTIVO por defecto
        manager.agregarContacto(c1); // Agregamos c1 al ContactManager
        manager.agregarContacto(c2); // Agregamos c2 al ContactManager
        manager.desactivarContacto("c1"); // Desactivamos el contacto c1 (cambia su estado a INACTIVO)
        List<Contact> activos = manager.filtrarPorEstado(Contact.Estado.ACTIVO); // Obtenemos la lista de contactos activos
        List<Contact> inactivos = manager.filtrarPorEstado(Contact.Estado.INACTIVO); // Obtenemos la lista de contactos inactivos
        SoftAssert sa = new SoftAssert(); // Usamos SoftAssert para validar múltiples condiciones sin detener el test en el primer fallo
        sa.assertTrue(activos.contains(c2)); // Verificamos que c2 está en la lista de activos
        sa.assertTrue(inactivos.contains(c1)); // Verificamos que c1 está en la lista de inactivos
        sa.assertAll(); // Ejecuta todas las validaciones y reporta los errores encontrados
    }
}

    // EQUIVALENCIAS ENTRE TESTNG Y JUNIT 5:
    // @BeforeClass (TestNG)  <-->  @BeforeAll (JUnit 5)
    // @AfterClass  (TestNG)  <-->  @AfterAll  (JUnit 5)
    // @BeforeMethod (TestNG) <-->  @BeforeEach (JUnit 5)
    // @AfterMethod  (TestNG) <-->  @AfterEach  (JUnit 5)
    // @Test         (TestNG) <-->  @Test      (JUnit 5)
    // -----------------------------------------------------------------------------