package pages;

import entities.Product;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Log;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SearchPage extends BasePage {
    private static final String URL="https://www.gittigidiyor.com/arama/?k=bilgisayar";

    By pageElementsLocation=By.xpath("//*[@class='pager pt30 hidden-m gg-d-24']/ul/li/a");

    List<WebElement> pageElements;
    TreeMap<Integer,WebElement> pageMap=new TreeMap<>();

    By entityLocation=By.xpath("//*[@class='catalog-view clearfix products-container']/li");

    public int currentPage=1;

    public SearchPage() {
        url=URL;
    }

    @Override
    public SearchPage initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        return (SearchPage) proInit(webDriver,webDriverWait,URL);
    }

    @Test
    public void pageSelectTest(){
        pageSelect(2);
        //selectElement();
    }

    public String getSelectedFromPage(){
        WebElement currentPageElement=webDriver.findElement(By.xpath("//*[@class='selected']")).findElement(By.xpath("./a"));
        return currentPageElement.getText();
    }

    public void pageSelect(int requestedPage){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pageElementsLocation));
        pageElements=webDriver.findElements(pageElementsLocation);
        pageMap.clear();

        for (int i=0;i<pageElements.size()-1;i++){
            if (i==0&&currentPage>1)
                continue;

            if (currentPage<6)
                pageMap.put(i+1,pageElements.get(i));
            else {
                pageMap.put(i+1+currentPage-5,pageElements.get(i));
            }
        }

        if (pageMap.containsKey(requestedPage)) {
            currentPage=requestedPage;
            webDriver.get(pageMap.get(requestedPage).getAttribute("href"));
        }

    }
    @Test
    public void testSelectElement(){
        selectElement();
    }

    public Product selectElement(){
        List<WebElement> webElements=webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(entityLocation));
        int r= new Random().nextInt(webElements.size());
        Log.info(webElements.size()+" ürün arasından "+r+"."+" ürün seçildi.");
        Product product=new Product(webElements.get(r));
        webDriver.get(product.link);
        return product;
    }
}
