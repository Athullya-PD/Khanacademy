package testpackage;

import org.testng.annotations.Test;
import basepackage.BaseKhan;
import pagepackage.PageKhan;

public class TestKhan extends BaseKhan {

    @Test
    public void loginClickTest() {
        PageKhan ob = new PageKhan(driver);
        ob.loginm(); 
        ob.scrollDown();
        ob.logindetails();
        ob.createaccclick();
        ob.dropdown();
        ob.signupclick();
        ob.signupdetails();
        //ob.signinginn();
    }
}
