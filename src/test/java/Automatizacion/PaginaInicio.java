package Automatizacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import utilities.DriverNavegador;

import java.util.List;
import java.util.Set;

public class PaginaInicio extends DriverNavegador {

    @Test
    public void presionBotonBiblioteca() {
        //Realizo un xpath para encontrar el boton de biblioteca
        final var botonBiblioteca = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/library']"));
        //Presiono el boton de biblioteca
        botonBiblioteca.click();

        sleep(2000);

        //Verifico que la url actual contenga /library
        final var urlfinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlfinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlfinal.contains("/library"), "La reedireccion a la biblioteca fallo o fue interrumpida");
    }

    @Test
    public void presionarBotonGroups() {
        //Realizo un xpath para encontrar el boton de Grupos
        final var botonGroups = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/groups']"));
        //Presiono el boton de Grupos
        botonGroups.click();

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        //Verifico que la url actual contenga /groups
        final var urlFinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlFinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/groups"), "La redireccion a la pagina de grupos fallo");
    }

    @Test
    public void presionarBotonListas() {
        //Realizo un xpath para encontrar el boton de Grupos
        final var botonListas = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/lists']"));

        //Presiono el boton de Grupos
        botonListas.click();

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        //Verifico que la url actual contenga /lists
        final var urlFinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlFinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/lists"), "La redireccion a la pagina de grupos fallo");
    }

    @Test
    public void presionarBotonForo() {
        //Guardo el ID de la pestaña principal
        final var ventanaPrincipal = driver.getWindowHandle();

        //Realizo un xpath para encontrar el boton de Foro)
        final var botonForo = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/forum']"));
        botonForo.click();

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        // Espero que se abra una nueva pestaña
        Set<String> pestañas = driver.getWindowHandles();
        Assertions.assertTrue(pestañas.size() > 1, "No se abrió una nueva pestaña al presionar el botón de Foro");

        // Cambio el control a la nueva pestaña
        for (var pestaña : pestañas) {
            if (!pestaña.equals(ventanaPrincipal)) {
                driver.switchTo().window(pestaña);
                break;
            }
        }

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        //Se valida que la URL y el título sean los esperados
        final var urlFinal = driver.getCurrentUrl();
        final var tituloPagina = driver.getTitle();

        // Imprimo en consola los valores obtenidos
        Assertions.assertEquals("https://tmocommunity.com/", urlFinal, "La URL de la pestaña del foro es incorrecta");
        Assertions.assertEquals("TMOCommunity", tituloPagina, "El título de la pestaña del foro es incorrecto");

    }

    @Test
    public void filtroBusqueda() {
        final var barraBusqueda = driver.findElement(By.name("title"));

        //Ingreso el término de búsqueda y presiono Enter
        barraBusqueda.sendKeys("Shokugeki no Soma");
        barraBusqueda.sendKeys(Keys.ENTER);

        sleep(2000);

        //Verifico que la url actual contenga /library
        final var urlfinal = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlfinal);

        //Obtengo el valor del campo de búsqueda para verificar que coincida con lo ingresado
        final var resultado = driver.findElement(By.id("title_item"));
        resultado.getAttribute("value");

        System.out.printf("El valor del campo de búsqueda es: %s%n", resultado.getAttribute("value"));

        sleep(2000);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlfinal.contains("https://zonatmo.com/library?title=Shokugeki+no+Soma&_pg=1"), "La reedireccion a la biblioteca con lo buscado fallo o fue interrumpida");
        Assertions.assertEquals("Shokugeki no Soma", resultado.getAttribute("value"),"El término de búsqueda no coincide con el valor ingresado");


    }

    @Test
    public void botonCambioDeColor() {
        //Encuentro el boton de cambio de color
        final var botonCambioDeColor = driver.findElement(By.id("toggleLightDarkIcon"));

        //Presiono el boton de cambio de color
        botonCambioDeColor.click();

        sleep(2000);

        //Verifico que el icono haya cambiado
        final var pestañaCambioColor = driver.findElement(By.id("toggleLightDarkBtn"));

        //Presiono el boton de cambio de color nuevamente para regresar al estado inicial
        pestañaCambioColor.click();

        sleep(2000);

        //Verifico el color actual de la pagina
        final var verificarColor = driver.findElement(By.cssSelector("i.fas.fa-fw.fa-toggle-on, i.fas.fa-fw.fa-toggle-off"));
        //Obtengo la clase del icono para determinar el color
        final var claseFinal = verificarColor.getAttribute("class");

        //Realizo la asercion correspondiente para verificar que el color de la pagina haya cambiado
        Assertions.assertTrue(claseFinal.contains("fa-toggle-on") || claseFinal.contains("fa-toggle-off"), "No se pudo determinar el color de la pagina");

    }

    @Test
    public void presionarBotonIdiomaEspañol() {
        //Encuentro el boton de cambio de idioma
        final var botonIdioma = driver.findElement(By.id("navbarDropdownLanguage"));

        //Presiono el boton de cambio de idioma
        botonIdioma.click();

        sleep(2000);

        //Selecciono el idioma English
        final var botonEnglish = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/lang/es']"));
        //Presiono el boton de English
        botonEnglish.click();

        sleep(2000);

        //Verifico que el idioma haya cambiado
        final var verificarIdioma = driver.findElement(By.cssSelector("span.flag-icon.flag-icon-es"));
        final var claseFinal = verificarIdioma.getAttribute("class");

        //Realizo la asercion correspondiente para verificar que el idioma haya cambiado a English
        Assertions.assertTrue(claseFinal.contains("flag-icon-es"), "El cambio de idioma a English falló");

    }

    @Test
    public void presionarBotonIdiomaEnglish() {
        //Encuentro el boton de cambio de idioma
        final var botonIdioma = driver.findElement(By.id("navbarDropdownLanguage"));

        //Presiono el boton de cambio de idioma
        botonIdioma.click();

        sleep(2000);

        //Selecciono el idioma English
        final var botonEnglish = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/lang/en']"));
        //Presiono el boton de English
        botonEnglish.click();

        sleep(2000);

        //Verifico que el idioma haya cambiado
        final var verificarIdioma = driver.findElement(By.cssSelector("span.flag-icon.flag-icon-gb"));
        final var claseFinal = verificarIdioma.getAttribute("class");

        //Realizo la asercion correspondiente para verificar que el idioma haya cambiado a English
        Assertions.assertTrue(claseFinal.contains("flag-icon-gb"), "El cambio de idioma a English falló");

    }

    @Test
    public void presionarBotonIdiomaCatalan() {
        //Encuentro el boton de cambio de idioma
        final var botonIdioma = driver.findElement(By.id("navbarDropdownLanguage"));

        //Presiono el boton de cambio de idioma
        botonIdioma.click();

        sleep(2000);

        //Selecciono el idioma English
        final var botonEnglish = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/lang/ct']"));
        //Presiono el boton de English
        botonEnglish.click();

        sleep(2000);

        //Verifico que el idioma haya cambiado
        final var verificarIdioma = driver.findElement(By.cssSelector("span.flag-icon.flag-icon-ct"));
        final var claseFinal = verificarIdioma.getAttribute("class");

        //Realizo la asercion correspondiente para verificar que el idioma haya cambiado a English
        Assertions.assertTrue(claseFinal.contains("flag-icon-ct"), "El cambio de idioma a English falló");

    }

    @Test
    public void presionarBotonIdiomaPortugues() {
        //Encuentro el boton de cambio de idioma
        final var botonIdioma = driver.findElement(By.id("navbarDropdownLanguage"));

        //Presiono el boton de cambio de idioma
        botonIdioma.click();

        sleep(2000);

        //Selecciono el idioma English
        final var botonEnglish = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/lang/pt']"));
        //Presiono el boton de English
        botonEnglish.click();

        sleep(2000);

        //Verifico que el idioma haya cambiado
        final var verificarIdioma = driver.findElement(By.cssSelector("span.flag-icon.flag-icon-pt"));
        final var claseFinal = verificarIdioma.getAttribute("class");

        //Realizo la asercion correspondiente para verificar que el idioma haya cambiado a English
        Assertions.assertTrue(claseFinal.contains("flag-icon-pt"), "El cambio de idioma a English falló");

    }

    @Test
    public void presionarBotonLogin() {

        //Realizo un xpath para encontrar el boton de Registrar
        final var botonAcceder = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/login']"));

        //Presiono el boton de Registrar
        botonAcceder.click();

        sleep(2000);

        //Verifico que la url actual contenga /login
        final var urlFinal = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlFinal);

        //Obtengo el título de la página para verificar que sea "Login"
        final var obtenerTitulo = driver.findElement(By.className("text-center"));
        System.out.printf("El título de la página es: %s", obtenerTitulo.getText());

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/login"), "La redireccion a la pagina de registro fallo");
        Assertions.assertEquals("Login", obtenerTitulo.getText(), "El título de la página no coincide con el esperado");

    }

    @Test
    public void presionarBotonRegistrar() {
        //Realizo un xpath para encontrar el boton de Registrar
        final var botonAcceder = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/register']"));

        //Presiono el boton de Registrar
        botonAcceder.click();

        sleep(2000);

        //Verifico que la url actual contenga /register
        final var urlFinal = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlFinal);

        //Obtengo el título de la página para verificar que sea "Registro"
        final var obtenerTitulo = driver.findElement(By.className("text-center"));
        System.out.printf("El título de la página es: %s", obtenerTitulo.getText());

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/register"), "La redireccion a la pagina de registro fallo");
        Assertions.assertEquals("Registro", obtenerTitulo.getText(), "El título de la página no coincide con el esperado");

    }

    @Test
    public void seccionSeinen() {

        //Encuentro el boton de cambio de seccion
        final var botonSeinen = driver.findElement(By.id("pills-populars-boys-tab"));

        //Presiono el boton de cambio de seccion
        botonSeinen.click();

        sleep(2000);

        //Verifico que la seccion haya cambiado
        final var estado = botonSeinen.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);

        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Seinen
        Assertions.assertEquals("true", estado, "No se ha cambiado a la sección Seinen");

    }

    @Test
    public void seccionJosei() {
        //Encuentro el boton de cambio de seccion
        final var botonJosei = driver.findElement(By.id("pills-populars-girls-tab"));

        //Presiono el boton de cambio de seccion
        botonJosei.click();

        sleep(2000);

        //Verifico que la seccion haya cambiado
        final var estado = botonJosei.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);

        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Josei
        Assertions.assertEquals("true", estado, "El texto del botón no coincide con el esperado");
    }

    @Test
    public void ingresarPrimeraImagenSeccion() {

        //Seccion Populares

        // Encuentro la primera imagen de la seccion
        final var imagen = driver.findElement(By.cssSelector("div.element:first-of-type"));

        // Obtengo la URL esperada del enlace asociado a la imagen
        final var urlEsperada = imagen.findElement(By.tagName("a")).getAttribute("href");
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        // Hago clic en la imagen para navegar a la página del manga
        imagen.click();

        sleep(2000);

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la imagen.");


    }

    @Test
    public void ingresarPrimeraImagenSeccionSeinen() {

        // Cambio a la seccion Seinen
        final var seccionSeinen = driver.findElement(By.id("pills-populars-boys-tab"));

        //Presiono el boton de cambio de seccion
        seccionSeinen.click();

        sleep(2000);

        // Encuentro la primera imagen de la seccion Seinen
        final var imagenSeinen = driver.findElement(By.cssSelector("#pills-populars-boys div.element:first-of-type"));

        // Obtengo la URL esperada del enlace asociado a la imagen
        final var urlEsperada = imagenSeinen.findElement(By.tagName("a")).getAttribute("href");;
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        // Hago clic en la imagen para navegar a la página del manga
        imagenSeinen.click();

        sleep(2000);

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la imagen.");

    }

    @Test
    public void ingresarPrimeraImagenSeccionJosei() {

        // Cambio a la seccion Josei
        final var seccionJosei = driver.findElement(By.id("pills-populars-girls-tab"));

        //Presiono el boton de cambio de seccion
        seccionJosei.click();

        sleep(2000);

        // Encuentro la primera imagen de la seccion Josei
        final var imagenJosei = driver.findElement(By.cssSelector("#pills-populars-girls div.element:first-of-type"));

        // Obtengo la URL esperada del enlace asociado a la imagen
        final var urlEsperada = imagenJosei.findElement(By.tagName("a")).getAttribute("href");;
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        // Hago clic en la imagen para navegar a la página del manga
        imagenJosei.click();

        sleep(2000);

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la imagen.");

    }

    @Test
    public void presionarBotonVerTodo() {

        final var botonVerTodo = driver.findElement(By.xpath("//a[@href=\'https://zonatmo.com/populars\']"));

        //Presiono el boton de Ver Todo
        botonVerTodo.click();

        sleep(2000);

        //Verifico que la url actual contenga /populars
        final var urlFinal = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlFinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/populars"), "La redireccion a la pagina de populares fallo");
    }

    @Test
    public void presionarBotonListaProgramacion() {

        final var botonListaProgramacion = driver.findElement(By.id("pills-lists-tab"));

        //Presiono el boton de Lista de Programacion
        botonListaProgramacion.click();

        sleep(2000);

        final var estado = botonListaProgramacion.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);

        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Lista de Programacion
        Assertions.assertEquals("true", estado, "No se ha cambiado a la sección Lista de Programacion");
    }

    @Test
    public void volverSeccionCapitulosPendiente() {

        presionarBotonListaProgramacion();

        sleep(2000);

        final var botonCapitulosPendientes = driver.findElement(By.id("pills-pending-tab"));

        //Presiono el boton de Capitulos Pendientes
        botonCapitulosPendientes.click();

        sleep(2000);

        final var estado = botonCapitulosPendientes.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);

        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Capitulos Pendientes
        Assertions.assertEquals("true", estado, "No se ha cambiado a la sección Capitulos Pendientes");
    }

    @Test
    public void presionarBotonTrendingSeinen() {

        final var botonTrendingSeinen = driver.findElement(By.id("pills-trending-boys-tab"));

        //Presiono el boton de Trending
        botonTrendingSeinen.click();

        sleep(2000);
        
        //Verifico que la seccion haya cambiado
        final var estado = botonTrendingSeinen.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);

        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Trending
        Assertions.assertEquals("true", estado, "No se ha cambiado a la sección Trending");
    }

    @Test
    public void presionarBotonTrendingJosei() {
        
        final var botonTrending = driver.findElement(By.id("pills-trending-girls-tab"));
        
        //Presiono el boton de Trending
        botonTrending.click();
        
        sleep(2000);
        
        //Verifico que la seccion haya cambiado
        final var estado = botonTrending.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);
        
        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Trending
        Assertions.assertEquals("true", estado, "No se ha cambiado a la sección Trending");
        
    }

    @Test
    public void presionarbotonTrending() { //Vuelvo a la seccion desde Seinen a Trending para completar la prueba

        presionarBotonTrendingSeinen();

        final var botonTrending = driver.findElement(By.id("pills-trending-tab"));

        //Presiono el boton de Trending
        botonTrending.click();

        sleep(2000);

        //Verifico que la seccion haya cambiado
        final var estado = botonTrending.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);

        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Trending
        Assertions.assertEquals("true", estado, "No se ha cambiado a la sección Trending");
    }

    @Test
    public void presionarBotonTrending2() { //Vuelvo a la seccion desde Josei a Trending para completar la prueba

        presionarBotonTrendingJosei();

        final var botonTrending = driver.findElement(By.id("pills-trending-tab"));

        //Presiono el boton de Trending
        botonTrending.click();

        sleep(2000);

        //Verifico que la seccion haya cambiado
        final var estado = botonTrending.getAttribute("aria-selected");
        System.out.printf("El estado del botón es: %s%n", estado);

        //Realizo la asercion correspondiente para verificar que la seccion haya cambiado a Trending
        Assertions.assertEquals("true", estado, "No se ha cambiado a la sección Trending");
    }

    @Test
    public void presionarImagenTrending() {

        // Encuentro la seccion de Trending
        final var seccionTrending = driver.findElement(By.id("pills-trending"));

        // Encuentro la primera imagen de la seccion Trending
        final var imagenTrending = seccionTrending.findElement(By.cssSelector("div.element:first-of-type"));

        // Obtengo la URL esperada del enlace asociado a la imagen
        final var urlEsperada = imagenTrending.findElement(By.tagName("a")).getAttribute("href");
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        // Hago clic en la imagen para navegar a la página del manga
        imagenTrending.click();

        sleep(2000);

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la imagen.");
    }

    @Test
    public void presionarImagenTrendingSeinen() {

        final var botonTrendingSeinen = driver.findElement(By.id("pills-trending-boys-tab"));

        //Presiono el boton de Trending
        botonTrendingSeinen.click();

        sleep(2000);

        final var imagenSeccionSeinen = driver.findElement(By.cssSelector("#pills-trending-boys div.element:first-of-type"));

        // Obtengo la URL esperada del enlace asociado a la imagen
        final var urlEsperada = imagenSeccionSeinen.findElement(By.tagName("a")).getAttribute("href");;
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        //Presiono el boton de Trending
        imagenSeccionSeinen.click();

        sleep(2000);

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la imagen.");
    }

    @Test
    public void presionarImagenTrendingJosei() {

        final var botonTrendingSeinen = driver.findElement(By.id("pills-trending-girls-tab"));

        //Presiono el boton de Trending
        botonTrendingSeinen.click();

        sleep(2000);

        final var imagenSeccionSeinen = driver.findElement(By.cssSelector("#pills-trending-girls div.element:first-of-type"));

        // Obtengo la URL esperada del enlace asociado a la imagen
        final var urlEsperada = imagenSeccionSeinen.findElement(By.tagName("a")).getAttribute("href");;
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        //Presiono el boton de Trending
        imagenSeccionSeinen.click();

        sleep(2000);

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la imagen.");
    }

    @Test
    public void presionarPrimeraNoticia() {

        // Encuentro la primera noticia en la sección de noticias
        final var primeraNoticia = driver.findElement(By.cssSelector("div.thumbnail.news div.thumbnail-title a"));

        // Obtengo la URL esperada del enlace asociado a la noticia
        final var urlEsperada = primeraNoticia.getAttribute("href");
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        // Guardo el identificador de la ventana actual
        final var ventanaActual = driver.getWindowHandle();

        // Hago clic en la noticia para navegar a la página de la noticia
        primeraNoticia.click();

        sleep(3000);

        // Cambio el control a la nueva ventana abierta
        for (var ventana : driver.getWindowHandles()) {
            if (!ventana.equals(ventanaActual)) {
                driver.switchTo().window(ventana);
                break;
            }

        }

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la noticia.");
    }

    @Test
    public void presionarSegundaNoticia() {

        // Encuentro la segunda noticia en la sección de noticias
        List<WebElement> noticia = driver.findElements(By.cssSelector("div.thumbnail.news div.thumbnail-title a"));

        // Selecciono la segunda noticia (índice 1)
        final var segundaNoticia = noticia.get(1);

        // Obtengo la URL esperada del enlace asociado a la noticia
        final var urlEsperada = segundaNoticia.getAttribute("href");
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        // Guardo el identificador de la ventana actual
        final var ventanaActual = driver.getWindowHandle();

        // Hago clic en la noticia para navegar a la página de la noticia
        segundaNoticia.click();

        sleep(3000);

        // Cambio el control a la nueva ventana abierta
        for (var ventana : driver.getWindowHandles()) {
            if (!ventana.equals(ventanaActual)) {
                driver.switchTo().window(ventana);
                break;
            }

        }

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la noticia.");
    }

    @Test
    public void presionarTerceraNoticia() {

        // Encuentro la tercera noticia en la sección de noticias
        List<WebElement> noticia = driver.findElements(By.cssSelector("div.thumbnail.news div.thumbnail-title a"));

        // Selecciono la segunda noticia (índice 1)
        final var tercerNoticia = noticia.get(2);

        // Obtengo la URL esperada del enlace asociado a la noticia
        final var urlEsperada = tercerNoticia.getAttribute("href");
        System.out.printf("La url esperada es: %s%n", urlEsperada);

        // Guardo el identificador de la ventana actual
        final var ventanaActual = driver.getWindowHandle();

        // Hago clic en la noticia para navegar a la página de la noticia
        tercerNoticia.click();

        sleep(3000);

        // Cambio el control a la nueva ventana abierta
        for (var ventana : driver.getWindowHandles()) {
            if (!ventana.equals(ventanaActual)) {
                driver.switchTo().window(ventana);
                break;
            }

        }

        // Obtengo la URL actual después de hacer clic en la imagen
        String urlActual = driver.getCurrentUrl();
        System.out.printf("La url actual es: %s%n", urlActual);

        // Realizo la aserción para verificar que la URL actual coincida con la URL esperada
        Assertions.assertEquals(urlEsperada, urlActual, "La URL actual no coincide con la URL esperada después de hacer clic en la noticia.");
    }
}
