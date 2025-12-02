package Automatizacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utilities.DriverNavegador;

public class PaginaLogin extends DriverNavegador {

    @Test
    public void abrirLogin() {

        final var botonLogin = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/login']"));

        botonLogin.click();

        sleep(2000);

        //Verifico que la url actual contenga /library
        final var urlfinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlfinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlfinal.contains("/login"), "La reedireccion a la biblioteca fallo o fue interrumpida");
    }

    @Test
    public void ingresarDatosErroneosLogin() {
        //Realizo el login con datos erroneos
        login();

        //Completo los campos del formulario de login
        final var campoEmail = driver.findElement(By.id("email"));
        final var campoPassword = driver.findElement(By.id("password"));
        final var botonIngresar = driver.findElement(By.xpath("//button[@type='submit']"));

        //Ingreso datos erroneos
        campoEmail.sendKeys("francojorquero@gmail.com");
        campoPassword.sendKeys("123456");
        botonIngresar.click();

        sleep(2000);

        //Busco el mensaje de error en la pagina
        final var mensajeError = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]//li"));

        //Verifico que el mensaje de error sea el esperado
        final var textoError = mensajeError.getText();
        System.out.printf("El mensaje de error es: %s%n", textoError);

        //Realizo la asercion correspondiente
        Assertions.assertEquals("El email o contraseña introducidos no son correctos.", textoError, "El mensaje de error no es el esperado");
    }

    @Test
    public void pruebaIniciarSesion() {
        //Cerrar sesion si ya hay un usuario logueado
        cerrarSesion();

        //Realizo el login con datos correctos
        login();

        //Completo los campos del formulario de login
        final var campoEmail = driver.findElement(By.id("email"));
        final var campoPassword = driver.findElement(By.id("password"));
        final var botonIngresar = driver.findElement(By.xpath("//button[@type='submit']"));

        //Ingreso datos correctos
        campoEmail.sendKeys("francojorquero@gmail.com");
        campoPassword.sendKeys("franco123j");
        botonIngresar.click();

        sleep(2000);

        //Verifico que el usuario se haya logueado correctamente buscando el nombre de usuario en la pagina
        final var usuarioLogueado = driver.findElement(By.xpath("//img[@class='rounded-circle']/parent::*"));

        //Obtengo el nombre de usuario y lo imprimo por consola
        final var nombreUsuario = usuarioLogueado.getText().trim();
        System.out.printf("El usuario logueado es: %s%n", nombreUsuario);

        //Realizo la asercion correspondiente
        Assertions.assertEquals("MONSTERHUNTERSINC", nombreUsuario, "El usuario logueado no es el esperado");

    }

    private void login() {

        final var botonLogin = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/login']"));

        botonLogin.click();

        sleep(2000);

    }

    private void cerrarSesion() {
        final var menuUsuario = driver.findElement(By.id("navbarDropdownMenuLink"));
        menuUsuario.click();

        sleep(2000);

        final var botonCerrarSesion = driver.findElement(By.cssSelector("a[href='https://zonatmo.com/logout']"));

        botonCerrarSesion.click();

        sleep(2000);
    }
}
