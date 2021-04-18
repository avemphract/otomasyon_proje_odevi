package entities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utilities.Log;

import java.util.ArrayList;
import java.util.List;

public class Product {
    WebElement webElement;

    By linkLocation=By.xpath(".//a");
    By titleLocation=By.xpath(".//a/div/div/div[1]/div[1]/h3/span");
    List<By> priceLocations=new ArrayList<>();
    {
        priceLocations.add(By.xpath(".//*[@class='extra-price']"));
        priceLocations.add(By.xpath(".//*[@class='extra-price']"));
        priceLocations.add(By.xpath(".//*[@class='extra-price']"));
        priceLocations.add(By.xpath(".//*[@class='fiyat robotobold price-txt']"));
        priceLocations.add(By.xpath(".//*[@class='fiyat price-txt robotobold price']"));

    }
    public String link;
    public String title;
    public String price;

    public Product(WebElement webElement) {
        this.webElement = webElement;

        link=webElement.findElement(linkLocation).getAttribute("href");
        title=webElement.findElement(titleLocation).getText();

        int i=0;
        WebElement element;
        while (true) {
            try {
                element = webElement.findElement(priceLocations.get(i));
                break;
            } catch (NoSuchElementException e) {
                i++;
            } catch (Exception e){
                Log.fatal(e.getMessage());
                throw e;
            }
        }

        price=element.getText();
    }
}
