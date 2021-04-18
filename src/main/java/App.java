import pages.LogPage;
import pages.MainPage;
import pages.ProductPage;
import pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import utilities.Log;

/**
 * Hello world!
 *
 */
public class App 
{
    static WebDriver webDriver;
    static WebDriverWait webDriverWait;
    public static void main( String[] args )
    {
        Log.startLog("App başlıyor...");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver","/Users/Katafrakt/IdeaProjects/OtomasyonProjesi/Driver/chromedriver.exe");
        webDriver =new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();

        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);

        webDriverWait=new WebDriverWait(webDriver,10);
        Log.info("Ağ sürücüsü açıldı.");

        begin();
    }

    public static void begin() {
        MainPage mainPage=new MainPage().initialize(webDriver,webDriverWait);
        webDriver.get(mainPage.url);

        mainPage.toSignIn();
        new LogPage().initialize(webDriver,webDriverWait).signIn("catalkayae15@itu.edu.tr","123456abc");

        mainPage.search("bilgisayar");

        Log.info("2. sayfa açılıyor.");
        new SearchPage().initialize(webDriver,webDriverWait).pageSelect(2);


        Log.info("Rastgele bir ürün seçiliyor");
        new SearchPage().initialize(webDriver,webDriverWait).selectElement();
        ProductPage productPage=new ProductPage().initialize(webDriver,webDriverWait);

        productPage.sepeteEkle();

        productPage.sepeteEkle();

        productPage.clearSepet();

    }
}
