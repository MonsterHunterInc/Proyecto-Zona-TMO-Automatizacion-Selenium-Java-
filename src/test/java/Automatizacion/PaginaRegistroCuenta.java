package Automatizacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utilities.DriverNavegador;

public class PaginaRegistroCuenta extends DriverNavegador {

    @Test
    public void registrarCuentaNueva() {
        //Prueba de registro de cuenta nueva (Bloqueado por el ReCaptcha de cloudflare)

        //Uso de JavascriptExecutor para interactuar con elementos que pueden no ser accesibles directamente
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("ddddddd");
        usernameRegistro.sendKeys("ddddddd");
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        js.executeScript("arguments[0].click();", checkboxTerminos);
        sleep(2000);
        botonRegistrarse.click();

        sleep(2000);
    }

    @Test
    public void ingresarEmailIncorrecto() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        //Ingreso un email sin el formato correcto
        emailRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para quitar el foco del campo email y activar la validacion
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", emailRegistro);
        usernameRegistro.sendKeys("ddddddd");
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        js.executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        //Obtengo el mensaje de validacion del campo email usando JavascriptExecutor
        final var mensajeEmailInvalido = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", emailRegistro);
        System.out.printf("El mensaje de email invalido es: %s%n", mensajeEmailInvalido);

        sleep(2000);

        //Realizo las aserciones correspondientes
        Assertions.assertTrue(mensajeEmailInvalido.contains("Incluye un signo \"@\""), "El mensaje no contiene el aviso del signo @");
        Assertions.assertTrue(mensajeEmailInvalido.contains("no incluye el signo \"@\""), "El mensaje no contiene la segunda parte del error");

    }

    @Test
    public void ingresarCampoVacioEmail() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        //Dejo el campo email vacio
        emailRegistro.sendKeys("");
        //Uso de JavascriptExecutor para quitar el foco del campo email y activar la validacion
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", emailRegistro);
        usernameRegistro.sendKeys("ddddddd");
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        //Obtengo el mensaje de validacion del campo email usando JavascriptExecutor
        final var mensajeCampoVacio = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", emailRegistro);
        System.out.printf("El mensaje de campo vacio es: %s%n", mensajeCampoVacio);

        sleep(2000);

        //Realizo las aserciones correspondientes
        Assertions.assertTrue(mensajeCampoVacio.contains("Completa este campo"), "El mensaje no contiene el aviso de campo vacio");
    }

    @Test
    public void ingresarCampoVacioNick() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("francojorquero@gmail.com");
        usernameRegistro.sendKeys("");
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", usernameRegistro);
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        //Obtengo el mensaje de validacion del campo username usando JavascriptExecutor
        final var mensajeCampoVacio = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", usernameRegistro);
        System.out.printf("El mensaje de campo vacio es: %s%n", mensajeCampoVacio);
        sleep(2000);

        //Realizo las aserciones correspondientes
        Assertions.assertTrue(mensajeCampoVacio.contains("Completa este campo"), "El mensaje no contiene el aviso de campo vacio");
    }

    @Test
    public void ingresarCampoVacioContrasena() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("francojorquero@gmail.com");
        usernameRegistro.sendKeys("ddddddd");
        passwordRegistro.sendKeys("");
        //Uso de JavascriptExecutor para quitar el foco del campo password y activar la validacion
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", usernameRegistro);
        repetirPasswordRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        //Obtengo el mensaje de validacion del campo username usando JavascriptExecutor
        final var mensajeCampoVacio = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", passwordRegistro);
        System.out.printf("El mensaje de campo vacio es: %s%n", mensajeCampoVacio);

        sleep(2000);

        //Realizo las aserciones correspondientes
        Assertions.assertTrue(mensajeCampoVacio.contains("Completa este campo"), "El mensaje no contiene el aviso de campo vacio");
    }

    @Test
    public void ingresarCampoVacioRepetirContrasena() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("francojorquero@gmail.com");
        usernameRegistro.sendKeys("ddddddd");
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("");
        //Uso de JavascriptExecutor para quitar el foco del campo password y activar la validacion
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", usernameRegistro);
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        //Obtengo el mensaje de validacion del campo username usando JavascriptExecutor
        final var mensajeCampoVacio = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", repetirPasswordRegistro);
        System.out.printf("El mensaje de campo vacio es: %s%n", mensajeCampoVacio);

        sleep(2000);

        //Realizo las aserciones correspondientes
        Assertions.assertTrue(mensajeCampoVacio.contains("Completa este campo"), "El mensaje no contiene el aviso de campo vacio");
    }

    @Test
    public void noPresionaronTérminos() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("francojorquero@gmail.com");
        usernameRegistro.sendKeys("ddddddd");
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para quitar el foco del campo password y activar la validacion
        ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", checkboxTerminos);
        botonRegistrarse.click();

        //Obtengo el mensaje de validacion del campo username usando JavascriptExecutor
        final var mensajeCampoVacio = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", checkboxTerminos);
        System.out.printf("El mensaje de campo vacio es: %s%n", mensajeCampoVacio);

        sleep(2000);

        //Realizo las aserciones correspondientes
        Assertions.assertTrue(mensajeCampoVacio.contains("Controla esta casilla si deseas continuar"), "El mensaje no contiene el aviso del checkbox de terminos");
    }

    @Test
    public void usernameOcupado() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("d@gmail.com");
        usernameRegistro.sendKeys("dddddd");
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("ddddddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        sleep(2000);

        //Obtengo el mensaje de error del nick
        final var textoErrorNick = driver.findElement(By.cssSelector(".alert-danger ul li")).getText();
        System.out.printf("El mensaje de error del nick es: %s%n", textoErrorNick);

        //Realizo la asercion correspondiente
        Assertions.assertEquals("El elemento username ya está en uso.", textoErrorNick, "El mensaje de error no es el esperado");
    }

    @Test
    public void contrasenaNoCompleta() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("d@gmail.com");
        usernameRegistro.sendKeys("sdsasdfffgs");
        passwordRegistro.sendKeys("dddd");
        repetirPasswordRegistro.sendKeys("dddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        sleep(2000);

        //Obtengo el mensaje de error del nick
        final var textoErrorNick = driver.findElement(By.cssSelector(".alert-danger ul li")).getText();
        System.out.printf("El mensaje de error del password es: %s%n", textoErrorNick);

        //Realizo la asercion correspondiente
        Assertions.assertEquals("El campo password debe contener al menos 6 caracteres.", textoErrorNick, "El mensaje de error no es el esperado");
    }

    @Test
    public void contrasenasNoCoinciden() {
        //Navego a la pagina de registro
        paginaRegistro();

        //Completo los campos del formulario de registro
        final var emailRegistro = driver.findElement(By.name("email"));
        final var usernameRegistro = driver.findElement(By.name("username"));
        final var passwordRegistro = driver.findElement(By.name("password"));
        final var repetirPasswordRegistro = driver.findElement(By.name("password_confirmation"));
        final var checkboxTerminos = driver.findElement(By.id("gdprCheck"));
        final var botonRegistrarse = driver.findElement(By.cssSelector("button.btn-primary"));

        emailRegistro.sendKeys("d@gmail.com");
        usernameRegistro.sendKeys("sdsasdfffgs");
        passwordRegistro.sendKeys("ddddddd");
        repetirPasswordRegistro.sendKeys("dddddddddddddd");
        //Uso de JavascriptExecutor para hacer click en el checkbox de terminos y condiciones
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkboxTerminos);
        botonRegistrarse.click();

        sleep(2000);

        //Obtengo el mensaje de error del nick
        final var textoErrorNick = driver.findElement(By.cssSelector(".alert-danger ul li")).getText();
        System.out.printf("El mensaje de error del password es: %s%n", textoErrorNick);

        //Realizo la asercion correspondiente
        Assertions.assertEquals("El campo confirmación de password no coincide.", textoErrorNick, "El mensaje de error no es el esperado");
    }

    private void paginaRegistro() {

        final var botonRegistro = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/register']"));
        botonRegistro.click();

        sleep(2000);
    }
}
