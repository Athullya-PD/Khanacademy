package pagepackage;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    WebElement month;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[3]/div/div/div[2]/div/div[3]/button")
    WebElement year;
 // Month and Year dropdown buttons
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[3]/div/div/div[2]/div/div[1]/button")
    WebElement monthDropdown;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[3]/div/div/div[2]/div/div[3]/button")
    WebElement yearDropdown;

    // Month and Year options from popup
    @FindBy(xpath = "/html/body/div[4]/div/div/button[9]/div[2]/div") // September
    WebElement septemberOption;

    @FindBy(xpath = "/html/body/div[4]/div/div/button[10]/div[2]/div") // Year 2016
    WebElement year2016Option;
    
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/div[3]/ul/li/button") // Year 2016
    WebElement signupbtn;
    
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[1]/input") // Year 2016
    WebElement emailsign;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[3]/div/input") // Year 2016
    WebElement usersign;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div[2]/main/div/div/section[2]/div/div/form/div[5]/input") // Year 2016
    WebElement passsignin;
     @FindBy(xpath = "//*[@id=\"main-content\"]/div/div/section[2]/div/nav/button[2]") // Year 2016
    WebElement signingin;
    
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

            for (int i = 1; i < rowCount; i++) { // Skip row 0 (header)
                String user = sheet.getRow(i).getCell(0).toString();
                String pwd = sheet.getRow(i).getCell(1).toString();

                // Clear fields using JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value='';", email);
                js.executeScript("arguments[0].value='';", pass);

                // Fill fields
                email.sendKeys(user);
                pass.sendKeys(pwd);

                // Click login
                loginbtn.click();

                // Optional wait for page action
                Thread.sleep(2000);

                // Reinitialize elements (if needed)
                PageFactory.initElements(driver, this);
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

            // Click Month dropdown
            wait.until(ExpectedConditions.elementToBeClickable(monthDropdown)).click();
            Thread.sleep(500);

            // Press down arrow 8 times to reach "September"
            for (int i = 0; i < 8; i++) {
                actions.sendKeys(Keys.ARROW_DOWN).perform();
                Thread.sleep(100); // Small pause between key presses
            }

            // Press Enter to select
            actions.sendKeys(Keys.ENTER).perform();

            Thread.sleep(500); // Wait for selection to register

            // Click Year dropdown
            wait.until(ExpectedConditions.elementToBeClickable(yearDropdown)).click();
            Thread.sleep(500);

            // Assuming 2016 is the 10th option (adjust if needed)
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
	   emailsign.sendKeys("athullyapd@gmail.com");
	   usersign.sendKeys("athulyaaa67123");
	   passsignin.sendKeys("Qwertyu1@");
	   signingin.click();
   }
   public void signinginn() {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	   signingin.click();
   }
    
}
