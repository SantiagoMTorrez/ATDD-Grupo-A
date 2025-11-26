import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

//Entrar desde el Home al algoritmo Johnson y buscar el boton circulo

public class PruebaCalidad1 {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception {
        // Configuración automática del ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    @Test
    public void verificarNavegacionYElementoSVG() {
        
        /********** Preparación de la prueba **********/
        
        // 1. Ingresar a la página de Grafos
        String url = "https://grafos-umber.vercel.app/";
        driver.get(url);
        
        System.out.println("Página cargada: " + driver.getTitle());
        
        /********** Lógica de la prueba **********/
        
        // 2. Presionar el botón que lleva a la pagina de Johnson
        WebElement botonNavegacion = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div/div/div/nav/div/button[2]/div[1]/span")
        );
        
        // Verificar que el botón está visible y habilitado antes de hacer click
        Assert.assertTrue(botonNavegacion.isDisplayed(), "El botón de navegación debe estar visible");
        Assert.assertTrue(botonNavegacion.isEnabled(), "El botón de navegación debe estar habilitado");
        
        botonNavegacion.click();
        System.out.println("Botón de navegación clickeado");
        
        // Esperar a que cargue la nueva página
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        /********** Verificación de la situación esperada **********/
        
        // 3. Buscar y verificar el elemento SPAN en la nueva página
        WebElement elementoSVG = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/aside/div[2]/div[1]/div/button[1]/span")
        );
        
        // Verificaciones del elemento SPAN
        Assert.assertTrue(elementoSVG.isDisplayed(), "El elemento SPAN debe estar visible");
        System.out.println("El boton circulo fue encontrado y visible");
        
        // Verificar adicionalmente algunos atributos del SPAN
        String tagName = elementoSVG.getTagName();
        Assert.assertEquals(tagName.toLowerCase(), "span", "El elemento debe ser un SPAN");
        System.out.println("Tag name del elemento: " + tagName);
        
        // Verificar que estamos en una URL diferente (confirmar navegación)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, url, "Debe haberse navegado a una página diferente");
        System.out.println("URL actual: " + currentUrl);
    }
    
    @AfterTest
    public void closeDriver() throws Exception {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
            System.out.println("Navegador cerrado");
        }
    }
}