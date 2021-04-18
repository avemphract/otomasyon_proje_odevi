package pages;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log;

import java.util.concurrent.TimeUnit;

public class ProductPage extends BasePage {
    private static final String URL="https://www.gittigidiyor.com/dizustu-laptop-notebook-bilgisayar/monster-abra-a5-v158_spp_783355";

    By addCartLocation = By.id("add-to-basket");
    By priceLocation =By.xpath("//html/body/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div[2]/div[2]/ul/li/div[2]/p[3]/span[2]");
    //By priceLocation=By.id("sp-price-discountPrice ");
    By countLocation =By.xpath("//html/body/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div[2]/div[2]/ul/li/div[2]/p[2]");
    By headerCartLocation =By.xpath("//html/body/div[1]/div[3]/div/div[4]/div[3]/a");
    By removeCartLocation =By.xpath("//html/body/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div[2]/div[2]/ul/li/a");
    By productLocation =By.xpath("//html/body/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div[2]/div[2]/ul/li");
    By productListLocation =By.xpath("//html/body/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div[2]/div[2]/ul/li");
    By totalItemCountLocation =By.xpath("//html/body/div[1]/div[3]/div/div[4]/div[3]/div/div/div/div[2]/div[1]/span");

    By checkRemainingCount=By.xpath("//html/body/div[3]/div[2]/div/div[1]/div[1]/div[2]/div[2]/div[1]/div/div[3]/div[4]/div[2]/div[2]/div/div/p[1]/span");

    public ProductPage() {
        url=URL;
    }

    @Override
    public ProductPage initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        return (ProductPage) proInit(webDriver,webDriverWait,URL);
    }

    @Test
    public void test(){
        sepeteEkle();

        sepeteEkle();

        clearSepet();


    }

    public void sepeteEkle(){
        WebElement addCartElement=webDriver.findElement(addCartLocation);
        WebElement body = webDriver.findElement(By.xpath("//html/body"));
        body.click();
        webDriver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        while (true){
            body.sendKeys(Keys.PAGE_DOWN);
            try {
                Thread.sleep(100);
                addCartElement.click();
                break;
            }
            catch (Exception e){

            }
        }
        webDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        Log.info("Ürün Sepete Eklendi");
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException ignored){

        }

    }
    public void clearSepet(){
        Actions actions=new Actions(webDriver);

        actions.moveToElement(webDriver.findElement(headerCartLocation)).perform();
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException ignored){

        }
        actions.moveToElement(webDriver.findElement(productLocation)).perform();
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException ignored){

        }
        webDriver.findElement(removeCartLocation).click();
        Log.info("Sepet temizlendi");
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){

        }
    }
    public String getPrice(){
        new Actions(webDriver).moveToElement(webDriver.findElement(headerCartLocation)).perform();try {
            Thread.sleep(200);
        }
        catch (InterruptedException ignored){

        }
        try {
            Thread.sleep(200);
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productListLocation));
        }
        catch (TimeoutException e){
            return "sepette ürün bulunmamaktadır.";
        }
        catch (InterruptedException e){

        }
        WebElement priceElement=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(priceLocation));
        return priceElement.getText();
    }
    public String getCount(){
        new Actions(webDriver).moveToElement(webDriver.findElement(headerCartLocation)).perform();
        try {
            Thread.sleep(200);
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productListLocation));
        }
        catch (TimeoutException e){
            return "sepette ürün bulunmamaktadır.";
        }
        catch (InterruptedException ignored){

        }


        try {
            if (webDriver.findElement(totalItemCountLocation).getText().equals("0 Ürün"))
                return "sepette ürün bulunmamaktadır.";
        }
        catch (TimeoutException ignored){
        }
        WebElement countElement=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(countLocation));
        return countElement.getText();
    }
}
