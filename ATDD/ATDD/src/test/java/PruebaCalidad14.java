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

//Entrar desde el Home al algoritmo Northwest y comprobar que resuelva el argoritmo correctamente en minimizacion

public class PruebaCalidad14{
    
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
            By.xpath("//*[@id=\"app\"]/div/div/div/nav/div/button[4]/div[1]/span")
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
        
        // 3. Ingresar los elementos de la tabla 
        WebElement tabla11 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[1]/td[1]/input")
        );
        Assert.assertTrue(tabla11.isDisplayed(), "El campo de texto debe estar visible");
        tabla11.clear();
        tabla11.sendKeys("6");

        WebElement tabla12 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[1]/td[2]/input")
        );
        Assert.assertTrue(tabla12.isDisplayed(), "El campo de texto debe estar visible");
        tabla12.clear();
        tabla12.sendKeys("8");

        WebElement tabla13 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[1]/td[3]/input")
        );
        Assert.assertTrue(tabla13.isDisplayed(), "El campo de texto debe estar visible");
        tabla13.clear();
        tabla13.sendKeys("7");

        WebElement tabla21 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[2]/td[1]/input")
        );
        Assert.assertTrue(tabla21.isDisplayed(), "El campo de texto debe estar visible");
        tabla21.clear();
        tabla21.sendKeys("8");

        WebElement tabla22 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[2]/td[2]/input")
        );
        Assert.assertTrue(tabla22.isDisplayed(), "El campo de texto debe estar visible");
        tabla22.clear();
        tabla22.sendKeys("1");

        WebElement tabla23 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[2]/td[3]/input")
        );
        Assert.assertTrue(tabla23.isDisplayed(), "El campo de texto debe estar visible");
        tabla23.clear();
        tabla23.sendKeys("2");

        WebElement tabla31 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[3]/td[1]/input")
        );
        Assert.assertTrue(tabla31.isDisplayed(), "El campo de texto debe estar visible");
        tabla31.clear();
        tabla31.sendKeys("9");

        WebElement tabla32 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[3]/td[2]/input")
        );
        Assert.assertTrue(tabla32.isDisplayed(), "El campo de texto debe estar visible");
        tabla32.clear();
        tabla32.sendKeys("5");

        WebElement tabla33 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[3]/td[3]/input")
        );
        Assert.assertTrue(tabla33.isDisplayed(), "El campo de texto debe estar visible");
        tabla33.clear();
        tabla33.sendKeys("3");
        
        //4. Insertar Disponibilidad y Demanda
        WebElement disponibilidad1 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[1]/td[4]/input")   
        ); 
        Assert.assertTrue(disponibilidad1.isDisplayed(), "El campo de disponibilidad debe estar visible");
        disponibilidad1.clear();
        disponibilidad1.sendKeys("12");

        WebElement disponibilidad2 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[2]/td[4]/input")   
        ); 
        Assert.assertTrue(disponibilidad2.isDisplayed(), "El campo de disponibilidad debe estar visible");
        disponibilidad2.clear();
        disponibilidad2.sendKeys("11");

        WebElement disponibilidad3 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[3]/td[4]/input")   
        ); 
        Assert.assertTrue(disponibilidad3.isDisplayed(), "El campo de disponibilidad debe estar visible");
        disponibilidad3.clear();
        disponibilidad3.sendKeys("7");

        WebElement demanda1 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[4]/td[1]/input")   
        ); 
        Assert.assertTrue(demanda1.isDisplayed(), "El campo de disponibilidad debe estar visible");
        demanda1.clear();
        demanda1.sendKeys("16");

        WebElement demanda2 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[4]/td[2]/input")   
        ); 
        Assert.assertTrue(demanda2.isDisplayed(), "El campo de disponibilidad debe estar visible");
        demanda2.clear();
        demanda2.sendKeys("4");

        WebElement demanda3 = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div/div[2]/table/tbody/tr[4]/td[3]/input")   
        ); 
        Assert.assertTrue(demanda3.isDisplayed(), "El campo de disponibilidad debe estar visible");
        demanda3.clear();
        demanda3.sendKeys("10");

        //5. Presionar el boton de resolver 
        WebElement botonResolver = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/header/div/nav/div[5]/div/button[1]")
        );
        Assert.assertTrue(botonResolver.isDisplayed(), "El boton de resolver debe estar visible");
        botonResolver.click();
        System.out.println("Botón de resolver clickeado");

        //6. Verificar el costo total
        WebElement costoTotal = driver.findElement(
            By.xpath("//*[@id=\"app\"]/div[2]/main/div/div[2]/div/div[3]/div[2]/div[1]/span[2]")
        );
        Assert.assertTrue(costoTotal.isDisplayed(), "El costo total debe estar visible");
        System.out.println("El costo total fue encontrado y visible");
        System.out.println("El costo total es correcto: " + costoTotal.getText());
        Assert.assertEquals(costoTotal.getText(), "135.00", "El costo total debe ser '135.00'");

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