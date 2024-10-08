package manager;

import com.google.common.io.Files;
import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.util.List;

public class HelperBase {

    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
//        clearNew(element);
        clearTextField(element);
        if (text!=null) {
            element.sendKeys(text);
        }
    }

    public void clearNew(WebElement element){
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void clearTextField(WebElement element){
//        WebElement element = wd.findElement(locator);
        String os = System.getProperty("os.name");
        if (os.startsWith("Win")){
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        }else {
            element.sendKeys(Keys.COMMAND, "a");
        }
        element.sendKeys(Keys.DELETE);
    }

    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size()>0;
    }

    public boolean isAlertPresent(String message){
        Alert alert = new WebDriverWait(wd, 10).until(ExpectedConditions.alertIsPresent());
        if (alert!=null&&alert.getText().contains(message)){
            System.out.println(alert.getText());
            pause(5000);
            alert.accept();
            return true;
        }
        return false;
    };

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh(){
        wd.navigate().refresh();
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
