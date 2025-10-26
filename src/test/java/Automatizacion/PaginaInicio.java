package Automatizacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverNavegador;

import java.sql.Driver;
import java.time.Duration;
import java.util.Set;

public class PaginaInicio extends DriverNavegador {

    @Test
    public void presionBotonBiblioteca() {
        abrirPagina();

        //Realizo un xpath para encontrar el boton de biblioteca
        final var botonBiblioteca = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/library']"));
        //Presiono el boton de biblioteca
        botonBiblioteca.click();

        //Manejo la redireccion y cierro ventanas emergentes si es necesario
        manejarRedireccion("/library");

        //Verifico que la url actual contenga /library
        final var urlfinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlfinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlfinal.contains("/library"), "La reedireccion a la biblioteca fallo o fue interrumpida");
    }

    @Test
    public void presionarBotonGroups() {
        abrirPagina();

        //Realizo un xpath para encontrar el boton de Grupos
        final var botonGroups = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/groups']"));
        //Presiono el boton de Grupos
        botonGroups.click();
        //Manejo la redireccion y cierro ventanas emergentes si es necesario
        manejarRedireccion("/groups");

        //Verifico que la url actual contenga /groups
        final var urlFinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlFinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/groups"), "La redireccion a la pagina de grupos fallo");
    }

    @Test
    public void presionarBotonListas() {
        abrirPagina();

        //Realizo un xpath para encontrar el boton de Grupos
        final var botonListas = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/lists']"));

        //Presiono el boton de Grupos
        botonListas.click();

        //Manejo la redireccion y cierro ventanas emergentes si es necesario
        manejarRedireccion("/lists");

        //Verifico que la url actual contenga /lists
        final var urlFinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlFinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/lists"), "La redireccion a la pagina de grupos fallo");
    }

    @Test
    public void presionarBotonForo() {

    }

    private void abrirPagina() { // Abro la pagina principal
        driver.get("https://zonatmo.com/");

    }

    private void manejarRedireccion(String urlEsperada) { // Codigo para detectar anuncios emergentes y cerrarlos
        final var pestañaOriginal = driver.getWindowHandle();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Se espera 20 segundos

            // Se espera que se abra alguna pestaña nueva o que la URL se cambie
            wait.until(d -> d.getWindowHandles().size() >= 1);

            Set<String> pestañas = driver.getWindowHandles();

            // Se realiza condicion de que si se abre una pestaña nueva, verificamos su contenido
            if (pestañas.size() > 1) {
                for (String pestaña : pestañas) {
                    driver.switchTo().window(pestaña);
                    final var urlActual = driver.getCurrentUrl();

                    System.out.println("Pestaña detectada: " + urlActual);

                    // Se cierra las pestañas que no coincidan con la URL esperada
                    if (!urlActual.contains(urlEsperada)) {
                        System.out.println("Cerrando pestaña no deseada: " + urlActual);
                        driver.close();
                    }
                }
                // Se vuelve a la pestaña original
                driver.switchTo().window(pestañaOriginal);
            }

            // Se espera a que la URL actual contenga el fragmento esperado
            wait.until(d -> {
                final var actual = d.getCurrentUrl();
                System.out.println("Esperando... URL actual: " + actual);
                return actual.contains(urlEsperada);
            });

            System.out.println("✅ Redirección detectada correctamente: " + driver.getCurrentUrl());

            // Logs adicionales para fallas en la espera
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("Timeout: No se detectó la URL esperada dentro del tiempo límite.");
            System.err.println("URL final detectada: " + driver.getCurrentUrl());
            throw e; // re-lanzamos para que el test muestre el fallo real
        } catch (Exception e) {
            System.err.println("Error inesperado en manejarRedireccion(): " + e.getMessage());
            e.printStackTrace();
        }
    }

}
