package pages;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log;

import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{
    public static final String URL="https://www.gittigidiyor.com/";

    //Not Logged
    By mouseOverLocation=By.xpath("//html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]");
    WebElement mouseOverElement;

    By girisYapLocation=By.xpath("//html/body/div[1]/header/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div/a");
    WebElement girisYapElement;

    //Logged
    By searchTextLocation=By.name("k");
    WebElement searchTextElement;

    public MainPage() {
        url=URL;
    }

    @Override
    public MainPage initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        return (MainPage) proInit(webDriver, webDriverWait, URL);
    }

    @Test
    public void toSignIn() {
        mouseOverElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(mouseOverLocation));
        int i=0;
        webDriver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        while (true){
            try {
                i++;
                Actions actions=new Actions(webDriver);
                actions.moveToElement(mouseOverElement).perform();
                //girisYapElement=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(girisYapLocation));
                girisYapElement=webDriver.findElement(girisYapLocation);
                girisYapElement.click();
                if (i>100)
                    throw new AssertionError();
                break;
            }
            catch (NoSuchElementException ignored){

            }
        }
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /*@Test
    public void searchTest(){
        search("bilgisayar");
    }*/

    public void search(String searchName){
        Log.info("'"+searchName+"' kelimesi aratılıyor.");
        searchTextElement=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(searchTextLocation));
        searchTextElement.sendKeys(searchName);

        Actions actions=new Actions(webDriver);
        actions.sendKeys(searchTextElement,Keys.ENTER).perform();
    }
}
