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

//Entrar desde el Home al algoritmo de Asignacion, buscar el boton de circular, insertar el nodo, cambiarle de nombre y comprobar aparece en la matriz

public class PruebaCalidad13 {
    
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
        
        // 2. Presionar el botón que lleva a la pagina de asignación 
        WebElement botonNavegacion = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div/div/div/nav/div/button[3]")
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
        
        // 3. Buscar y verificar el boton de circulo en la nueva página
        WebElement elementoSVG = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/aside/div[2]/div[1]/div/button[1]/span")
        );

        elementoSVG.click();
        
        // Verificaciones del elemento SPAN
        Assert.assertTrue(elementoSVG.isDisplayed(), "El elemento SPAN debe estar visible");
        System.out.println("El boton circulo fue encontrado y visible");
       
        
        //4. Buscar la pizarra
        WebElement pizarra = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/section")
        ); 
        Assert.assertTrue(pizarra.isDisplayed(), "La pizarra debe estar visible");
        System.out.println("La pizarra fue encontrada y visible");

        pizarra.click();

        //5. Nombre del nodo
        WebElement nombreNodo = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/input")
        );
        Assert.assertTrue(nombreNodo.isDisplayed(), "El campo del nombre del nodo debe estar visible");
        System.out.println("El campo del nombre del nodo fue encontrado y visible");
        nombreNodo.clear();
        nombreNodo.sendKeys("Nombre");

        //6. Buscar el boton de la matriz
        WebElement botonMatriz = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/aside/div[2]/div[3]/button[3]/span") 
        );
        Assert.assertTrue(botonMatriz.isDisplayed(), "El boton de la matriz debe estar visible");
        System.out.println("El boton de la matriz fue encontrado y visible");
        botonMatriz.click();

        //7. Verificar que el nodo aparece en la matriz
        WebElement nodoMatriz = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/div/div/main/div[1]/table/thead/tr/th[2]/span")    
        );
        Assert.assertTrue(nodoMatriz.isDisplayed(), "El nodo debe estar visible en la matriz");
        System.out.println("El nodo fue encontrado y visible en la matriz");
        System.out.println("El nodo en la matriz es correcto: " + nodoMatriz.getText());
        Assert.assertEquals(nodoMatriz.getText(), "Nombre", "El nodo debe tener el nombre 'Nombre'");


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