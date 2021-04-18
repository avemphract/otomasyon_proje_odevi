package pages;

import org.junit.BeforeClass;
import utilities.Log;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;
    protected String baseUrl="https://www.gittigidiyor.com/";
    public String url;

    protected BasePage proInit(WebDriver webDriver, WebDriverWait webDriverWait, String url) {
        this.webDriver = webDriver;
        this.webDriverWait=webDriverWait;
        this.url=url;
        Log.info(url+" sayfası yükleniyor...");
        return this;
    }

    public static void setProperty(){
        System.setProperty("webdriver.chrome.driver","/Users/Katafrakt/IdeaProjects/OtomasyonProjesi/Driver/chromedriver.exe");
    }
    public abstract BasePage initialize(WebDriver webDriver, WebDriverWait webDriverWait);

    @BeforeClass
    public static void beforeClass(){
        Log.startLog("Test başlıyor");
    }

    @Before
    public void beforeTest(){
        setProperty();
        webDriver=new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriverWait=new WebDriverWait(webDriver,10);

        webDriver.get(url);
    }

    @After
    public void closeBrowser(){
        Log.endLog("Test bitti");
        //webDriver.close();
    }
}
