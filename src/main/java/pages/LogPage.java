package pages;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogPage extends BasePage {
    private static final String URL="https://www.gittigidiyor.com/uye-girisi";
    By usernameLocation =By.name("kullanici");
    WebElement usernameElement;

    By passwordLocation =By.name("sifre");
    WebElement passwordElement;

    By buttonLocation=By.id("gg-login-enter");
    WebElement buttonElement;

    By captchaCheckboxLocation=By.xpath("//html/body/div[2]/div[3]/div[1]/div/div/span");
    WebElement captchaCheckboxElement;

    public LogPage() {
        url=URL;
    }


    @Override
    public LogPage initialize(WebDriver webDriver, WebDriverWait webDriverWait) {
        return (LogPage) proInit(webDriver,webDriverWait,URL);
    }

    @Test
    public void signIn(String username,String password) {
        webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        usernameElement=webDriver.findElement(usernameLocation);
        passwordElement=webDriver.findElement(passwordLocation);
        buttonElement=webDriver.findElement(buttonLocation);

        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);


        webDriver.manage().timeouts().implicitlyWait(500,TimeUnit.MILLISECONDS);
        if (webDriver.findElements(captchaCheckboxLocation).size()>0){
            captchaCheckboxElement=webDriver.findElement(captchaCheckboxLocation);
            captchaCheckboxElement.click();
        }


        buttonElement.click();



    }
}
