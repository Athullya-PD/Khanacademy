

package pagepackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageKhan {
    WebDriver driver;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/div[1]/nav/div[1]/div[3]/a[2]")
    WebElement loginmenu;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[2]/input")
    WebElement email;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[4]/input")
    WebElement pass;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/button")
    WebElement loginbtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/p/a")
    WebElement createacc;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[3]/div/div/div[2]/div/div[1]/button")
    WebElement monthDropdown;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[3]/div/div/div[2]/div/div[3]/button")
    WebElement yearDropdown;

    @FindBy(xpath = "/html/body/div[4]/div/div/button[9]/div[2]/div")
    WebElement septemberOption;

    @FindBy(xpath = "/html/body/div[4]/div/div/button[10]/div[2]/div")
    WebElement year2016Option;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[3]/ul/li/button")
    WebElement signupbtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[1]/input")
    WebElement emailsign;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[3]/div/input")
    WebElement usersign;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[5]/input")
    WebElement passsignin;
    
    @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/section[2]/div/nav/button[1]")
    WebElement backbtn;
    
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[6]/a")
    WebElement accountbtn;
    
    @FindBy(xpath = " /html/body/div[2]/div[2]/div/div/div[3]/button")
    WebElement cookiesclose;
   
    @FindBy(xpath = "//*[@id=\":r1:\"]/span[2]")
    WebElement dropdown;
    
    @FindBy(xpath = "//*[@id=\\\":r0:\\\"]/button[4]/div[2]/div")
    WebElement customrg;
    
    @FindBy(xpath = "//*[@id=\"start-date-field\"]")
    WebElement date1;
    
    @FindBy(xpath = "//*[@id=\"end-date-field\"]")
    WebElement date2;
     
    @FindBy(xpath = "/html/body/div[5]/div/div[3]/div/div/div/div/div[1]/span[2]")
    WebElement farrow1;
    
    @FindBy(xpath = "/html/body/div[5]/div/div[3]/div/div/div/div/div[1]/span[2]")
    WebElement farrow2;
    
    @FindBy(xpath = "/html/body/div[5]/div/div[3]/div/div/div/div/div[2]/div/div[3]/div[4]/div[4]")
    WebElement fromdate;
    
    @FindBy(xpath = "/html/body/div[5]/div/div[3]/div/div/div/div/div[2]/div/div[3]/div[5]/div[3]")
    WebElement enddate;
    
    @FindBy(xpath = "/html/body/div[5]/div/div/div/div/div[3]/button")
    WebElement confirmbtn;
    
    public PageKhan(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginm() {
        loginmenu.click();
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");
    }

    public void logindetails() {
        String path = "C:\\Users\\hp\\Desktop\\Testing-Luminar\\Khanprojet.xlsx";
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) {
                String user = sheet.getRow(i).getCell(0).toString();
                String pwd = sheet.getRow(i).getCell(1).toString();

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value='';", email);
                js.executeScript("arguments[0].value='';", pass);

                email.sendKeys(user);
                pass.sendKeys(pwd);

                loginbtn.click();
                Thread.sleep(2000);
            }

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createaccclick() {
        createacc.click();
    }

    public void dropdown() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);

            wait.until(ExpectedConditions.elementToBeClickable(monthDropdown)).click();
            Thread.sleep(500);

            for (int i = 0; i < 8; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
                Thread.sleep(100);
            }

            actions.sendKeys(Keys.ENTER).perform();
            Thread.sleep(500);

            wait.until(ExpectedConditions.elementToBeClickable(yearDropdown)).click();
            Thread.sleep(500);

            for (int i = 0; i < 10; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
                Thread.sleep(100);
            }

            actions.sendKeys(Keys.ENTER).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signupclick() {
        signupbtn.click();
    }

    public void signupdetails() {
        emailsign.sendKeys("athullya1998@gmail.com");
        usersign.sendKeys("aathuu123");
        passsignin.sendKeys("Qwerty1@");
    }

    public void signinginn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement signinginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/nav/button[2]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signinginBtn);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signinginBtn);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sign In click failed.");
        }
    }
    public void backbuttonClick() {
    	backbtn.click();
    	accountbtn.click();
    }
    public void loginWithSignupCredentials() {
        email.clear();
        pass.clear();
        
        email.sendKeys("athulyahbj");
        pass.sendKeys("Qwerty1@");
        
        loginbtn.click();
    }
    public void selectCustomRangeWithKeyboard() {
        cookiesclose.click();  
        dropdown.click();  

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN)  
               .sendKeys(Keys.ARROW_DOWN) 
               .sendKeys(Keys.ENTER)       
               .build()
               .perform();
    }
    public void datepicker() throws IOException {
    	date1.click();
    	farrow1.click();
    	fromdate.click();
    	date2.click();
    	farrow2.click();
    	enddate.click();
    	confirmbtn.click();
    	
    	// Take screenshot after selecting date
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("C:\\Users\\hp\\Desktop\\Testing-Luminar\\screenshot\\custom_date_selection.png");
        FileHandler.copy(src, dest);
    }
    public void printPageTitle() {
        String title = driver.getTitle();
        System.out.println("Current Page Title: " + title);
    }
    public void closeCurrentTab() {
        driver.close(); 
    }


}


