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

//Entrar desde el Home al algoritmo Dijkstra, buscar el boton circulo, insertar un nodos y eliminarlo  

public class PruebaCalidad5 {
    
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
        
        // 2. Presionar el botón que lleva a la pagina de Dijkstra
        WebElement botonNavegacion = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div/div/div/nav/div/button[8]/div[1]/span")
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

        elementoSVG.click();
        
        // Verificaciones del elemento SPAN
        Assert.assertTrue(elementoSVG.isDisplayed(), "El elemento SPAN debe estar visible");
        System.out.println("El boton circulo fue encontrado y visible");

        // Esperar un momento para observar el resultado del clic
       
        
        //4. Buscar la pizarra
        WebElement pizarra = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/section")
        ); 
        Assert.assertTrue(pizarra.isDisplayed(), "La pizarra debe estar visible");
        System.out.println("La pizarra fue encontrada y visible");

        pizarra.click();

        //5. Encontrar el boton de Limpiar
        WebElement limpiarNodos = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/aside/div[2]/button/span")
        ); 
        Assert.assertTrue(limpiarNodos.isDisplayed(), "El boton de limpiar debe estar visible");
        limpiarNodos.click();
        try {
                WebElement botonOK = driver.findElement(
                    By.xpath("//div[contains(@class, 'dialog')]//button[1]")
                );
                botonOK.click();
                System.out.println("Botón OK clickeado (primer botón del diálogo)");
            } catch (Exception e2) {
                System.out.println("No se pudo encontrar el botón OK con ninguna estrategia");
                
                // Estrategia 3: Listar todos los botones visibles para debug
                System.out.println("=== BOTONES DISPONIBLES EN EL DIÁLOGO ===");
                java.util.List<WebElement> botonesDialogo = driver.findElements(
                    By.xpath("//div[contains(@class, 'dialog')]//button")
                );
                for (WebElement boton : botonesDialogo) {
                    try {
                        System.out.println("Botón: '" + boton.getText() + "'");
                    } catch (Exception ex) {
                        // Ignorar elementos que ya no están en el DOM
                    }
                }
                
                // Si no encontramos el botón, intentar con JavaScript para cerrar el diálogo
                try {
                    ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("window.confirm = function(){return true;};");
                    System.out.println("Diálogo manejado via JavaScript");
                } catch (Exception e3) {
                    System.out.println("No se pudo manejar el diálogo");
                }
            }
        limpiarNodos.click();
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