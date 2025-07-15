# ContactManager - Pruebas Automatizadas con TestNG y contactng.xml

## ¿Qué es ContactManager?
Un sistema para gestionar contactos, permitiendo agregar, desactivar y filtrar contactos por estado (activo/inactivo).

## Estructura del proyecto
```
manager/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── cl/
│   │           └── kibernumacademy/
│   │               └── testng/
│   │                   ├── Contact.java
│   │                   └── ContactManager.java
│   └── test/
│       └── java/
│           └── cl/
│               └── kibernumacademy/
│                   └── testng/
│                       └── ContactManagerTest.java
├── testng.xml
```

## ¿Qué es testng.xml?
Archivo de configuración para TestNG, Define parámetros, clases de prueba y ejecución en paralelo.

## Ejemplo de testng.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="ContactManagerSuite" parallel="tests" thread-count="2">
    <test name="ContactManagerTest">
        <parameter name="nombre" value="Juan Perez"/>
        <parameter name="correo" value="juan.perez@email.com"/>
        <classes>
            <class name="cl.kibernumacademy.contact.ContactManagerTest"/>
        </classes>
    </test>
    <test name="ContactManagerTest2">
        <parameter name="nombre" value="Ana Lopez"/>
        <parameter name="correo" value="ana.lopez@email.com"/>
        <classes>
            <class name="cl.kibernumacademy.contact.ContactManagerTest"/>
        </classes>
    </test>
</suite>
```

## ¿Cómo ejecutar las pruebas?
1. Configura Maven Surefire en tu `pom.xml` para usar `contactng.xml`:
   ```xml
   <suiteXmlFiles>
     <suiteXmlFile>testng.xml</suiteXmlFile>
   </suiteXmlFiles>
   ```
2. Ejecuta:
   ```
   mvn clean test
   ```

# Documentación: Uso de SoftAssert en TestNG

## ¿Qué es SoftAssert?
`SoftAssert` es una clase de TestNG que permite realizar múltiples validaciones (aserciones) en un mismo método de prueba sin detener la ejecución ante el primer fallo. A diferencia de `Assert`, que interrumpe el test si una condición falla, `SoftAssert` acumula los resultados y reporta todos los errores al final del método.

## ¿Por qué usar SoftAssert?
- Permite validar varios aspectos de una prueba en una sola ejecución.
- Facilita la depuración mostrando todos los fallos juntos.
- Es útil cuando quieres comprobar el estado de varios objetos o condiciones relacionadas.

## Flujo de uso de SoftAssert (paso a paso)

1. **Crear una instancia de SoftAssert:**
   ```java
   SoftAssert sa = new SoftAssert();
   ```
2. **Realizar múltiples aserciones:**
   ```java
   sa.assertTrue(condicion1);
   sa.assertEquals(valor1, valor2);
   sa.assertFalse(condicion2);
   // Puedes agregar tantas como necesites
   ```
3. **Finalizar con sa.assertAll():**
   ```java
   sa.assertAll();
   ```
   Este método revisa todas las aserciones realizadas. Si alguna falló, lanza una excepción y muestra todos los errores acumulados.

## Ejemplo comentado
```java
@Test
public void ejemploSoftAssert() {
    // Paso 1: Crear la instancia
    SoftAssert sa = new SoftAssert();

    // Paso 2: Realizar varias validaciones
    sa.assertTrue(2 > 1, "2 debe ser mayor que 1"); // Pasa
    sa.assertEquals("hola", "hola", "Las cadenas deben ser iguales"); // Pasa
    sa.assertFalse(false, "Debe ser falso"); // Pasa
    sa.assertTrue(5 < 3, "5 no es menor que 3"); // Falla
    sa.assertEquals(10, 20, "10 no es igual a 20"); // Falla

    // Paso 3: Ejecutar todas las validaciones
    sa.assertAll(); // Aquí se reportan los errores de las aserciones fallidas
}
```

## Explicación del flujo
- Las aserciones se ejecutan una tras otra, sin detener el test si alguna falla.
- Al final, `assertAll()` revisa todas y lanza una excepción si alguna no se cumplió, mostrando un resumen de los errores.
- Esto permite ver todos los problemas de la prueba en una sola ejecución, facilitando la corrección y el análisis.

## Recomendaciones
- Siempre llama a `assertAll()` al final del método de prueba, de lo contrario los errores no se reportarán.
- Úsalo cuando necesites validar múltiples condiciones en un solo test.
- Es ideal para pruebas de filtrado, validación de listas, o cuando quieres comprobar varios resultados relacionados.

---
**SoftAssert es una herramienta poderosa para mejorar la calidad y el análisis de tus pruebas automatizadas en TestNG.**