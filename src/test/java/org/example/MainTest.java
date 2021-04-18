package org.example;

import entities.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LogPage;
import pages.MainPage;
import pages.ProductPage;
import pages.SearchPage;
import utilities.Log;

import java.util.concurrent.TimeUnit;

/**Stoktaki ürün sayısı 1 olduğunda sepete 2 tane ürün ekleyememektedir. */
public class MainTest {
    static WebDriver webDriver;
    static WebDriverWait webDriverWait;


    @Before
    public void beforeTest(){
        Log.startLog("MainTest başlıyor...");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
        webDriver =new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();

        webDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

        webDriverWait=new WebDriverWait(webDriver,10);
        Log.info("Ağ sürücüsü açıldı.");
    }
    @Test
    public void test(){

        MainPage mainPage=new MainPage().initialize(webDriver,webDriverWait);
        webDriver.get(mainPage.url);

        mainPage.toSignIn();
        Log.info("catalkayae15@itu.edu.tr hesabına giriş yapılıyor");
        new LogPage().initialize(webDriver,webDriverWait).signIn("catalkayae15@itu.edu.tr","123456abc");

        mainPage.search("bilgisayar");
        SearchPage searchPage=new  SearchPage().initialize(webDriver,webDriverWait);
        Log.info("2. sayfa açılıyor.");
        searchPage.pageSelect(2);

        Log.info("İstenen sayfa: "+2+" Açılan sayfa: "+searchPage.getSelectedFromPage());
        Assert.assertEquals("2", searchPage.getSelectedFromPage());
        Log.info("Açılan sayfa ile istenen sayfa aynı.");

        Product product = new SearchPage().initialize(webDriver,webDriverWait).selectElement();
        Log.info("Seçilen ürünün sayfası açılıyor...");
        ProductPage productPage=new ProductPage().initialize(webDriver,webDriverWait);


        productPage.sepeteEkle();
        Log.info("Aramada ürünün fiyatı: "+product.price+" Sayfada ürünün fiyatı: "+productPage.getPrice());
        try {
            Assert.assertEquals(product.price,productPage.getPrice());
            Log.info("Aramadaki ürünün fiyatı ile sayfadaki ürünün fiyatı aynı");
        }catch (AssertionError error){
            Log.fatal(error.getMessage());
            throw error;
        }

        productPage.sepeteEkle();
        Log.info("Ürün sayısı: "+productPage.getCount());
        try {
            Assert.assertEquals("2 Adet",productPage.getCount());
            Log.info("Ürün sayısı 2 olduğu doğruladı");
        }
        catch (AssertionError error){
            Log.fatal(error.getMessage());
            throw error;
        }

        productPage.clearSepet();
        Log.info("Ürün sayısı: "+productPage.getCount());

    }
}
