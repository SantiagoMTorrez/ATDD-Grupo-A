import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;  // ✅ Import correcto de TestNG
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistroDZoneTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{
        WebDriverManager.chromedriver().setup();  // ✅ Solo WebDriverManager
        driver = new ChromeDriver();
    }
    
    @Test
    public void verificarMensajeErrorAlRegistrar(){
        
        // 1. Ingresar a la página de DZone
        String dzoneUrl = "https://dzone.com";
        driver.get(dzoneUrl);
        
        // 2. Hacer click en el link Join
        WebElement joinLink = driver.findElement(By.xpath("//*[@id=\"unauthenticated-block\"]/div[2]/a[2]"));
        joinLink.click();
        
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        
        // 3. Presionar el botón Join
        WebElement joinButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[3]/button"));
        joinButton.click();

        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        
        // Verificación - Usando Assert de TestNG
        WebElement iconAlert = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[1]/div[2]/form/div[1]/span[2]/i"));
        
        // ✅ FORMA CORRECTA con TestNG:
        Assert.assertTrue(iconAlert.isDisplayed(), "El icono de alerta debería mostrarse");
        
        System.out.println("Se muestra el icono? "+iconAlert.isDisplayed());
        
        // Validar el mensaje del correo
        WebElement emailErrorMessage = driver.findElement(
                By.xpath("//div[@data-validate=\"Please enter a valid email address\"]"));
        
        String attribute = emailErrorMessage.getAttribute("data-validate");
        System.out.println("Valor del attribute::: "+attribute);
        
        Assert.assertEquals(attribute, "Please enter a valid email address", 
                          "El mensaje de error de email debería coincidir");
    }
    
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
}