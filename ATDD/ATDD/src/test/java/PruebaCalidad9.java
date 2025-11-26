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

//Entrar desde el Home al algoritmo Tress, insertar un numero y comprobar que ese el el minimo

public class PruebaCalidad9{
    
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
        
        // 2. Presionar el botón que lleva a la pagina de Tress
        WebElement botonNavegacion = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div/div/div/nav/div/button[6]/div[1]/span")
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
        
        // 3. Buscar el campo de insercion 
        WebElement campo = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div/div[2]/main/div[2]/div/div/div[1]/div/div/div/div[1]/div/div/input")
        );
        Assert.assertTrue(campo.isDisplayed(), "El campo debe estar visible");
        System.out.println("El campo fue encontrado y visible");
        campo.sendKeys("94");
        //4. Buscar el boton de insertar
        WebElement botonInsertar = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div/div[2]/main/div[2]/div/div/div[1]/div/div/div/div[1]/div/div/button")
        );
        Assert.assertTrue(botonInsertar.isDisplayed(), "El boton debe estar visible");
        System.out.println("El boton fue encontrado y visible");
        botonInsertar.click(); 
       
        //5. Buscar el nodo insertado y su valor minimo
        WebElement minimo = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div/div[2]/main/div[2]/div/div/div[3]/div/div[1]/div[2]/div/div[1]")
        );
        Assert.assertTrue(minimo.isDisplayed(), "El nodo insertado debe estar visible");
        System.out.println("El nodo insertado fue encontrado y visible");
        
        // Buscar el elemento strong dentro del div
        WebElement strongElement = minimo.findElement(By.tagName("strong"));
        String valorStrong = strongElement.getText();

        System.out.println("Se muestra el valor correcto de: " + valorStrong);
        Assert.assertEquals(valorStrong, "94", "El minimo es 94");
        

         try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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