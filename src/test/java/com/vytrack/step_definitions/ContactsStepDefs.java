package com.vytrack.step_definitions;

import com.vytrack.pages.*;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.DBUtils;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.*;
import jdk.nashorn.internal.runtime.OptimisticReturnFilters;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class ContactsStepDefs {

    @Then("the user should see following options")
    public void the_user_should_see_following_options(List<String> menuOptions) {
        BrowserUtils.waitFor(2);
        //get the list of webelement and convert them to list of string with the help of ready method
        List<String> actualOptions = BrowserUtils.getElementsText(new DashboardPage().menuOptions);

        Assert.assertEquals(menuOptions,actualOptions);
        System.out.println("actualOptions = " + actualOptions);
        System.out.println("menuOptions = " + menuOptions);
    }

    @When("the user logs in using following credentials")
    public void the_user_logs_in_using_following_credentials(Map<String,String> userInfo) {
        System.out.println("userInfo = " + userInfo);
        new LoginPage().login(userInfo.get("username"),userInfo.get("password"));
        Assert.assertEquals(new DashboardPage().getUserName(),userInfo.get("firstname") +" "+ userInfo.get("lastname"));
    }

    @When("the user clicks the {string} from contacts")
    public void the_user_clicks_the_from_contacts(String email) {

      //  BrowserUtils.waitFor(5);
        //click the row with email
        ContactsPage contactsPage = new ContactsPage();
        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        wait.until(ExpectedConditions.titleIs("All - Contacts - Customers"));
        contactsPage.getContactEmail(email).click();

    }

    @Then("the information should be same with database")
    public void the_information_should_be_same_with_database() {
        //get actual data from UI-GUI-Front end-Browser-Website(whatever we see)
        BrowserUtils.waitFor(3);
        ContactInfoPage contactInfoPage = new ContactInfoPage();
        String actualFullname = contactInfoPage.contactFullName.getText();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone = contactInfoPage.phone.getText();

        System.out.println("actualFullname = " + actualFullname);
        System.out.println("actualEmail = " + actualEmail);
        System.out.println("actualPhone = " + actualPhone);

        //get expected data from database
        String query = "select concat(first_name,\" \",last_name) as fullname,last_name,e.email,p.phone\n" +
                "from orocrm_contact c join orocrm_contact_email e\n" +
                "on c.id = e.owner_id join orocrm_contact_phone p\n" +
                "on e.owner_id = p.owner_id\n" +
                "where e.email = 'mbrackstone9@example.com'";

        DBUtils.createConnection();

        //Since the result is only one row, we saved in Map<String,Object>
        //if you are dealing with multiple rows, use List<Map<String,Object>>
        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        String expectedFullName = (String) rowMap.get("fullname");
        String expectedEmail = (String) rowMap.get("email");
        String expectedPhone = (String) rowMap.get("phone");

        System.out.println("expectedFullName = " + expectedFullName);
        System.out.println("expectedEmail = " + expectedEmail);
        System.out.println("expectedPhone = " + expectedPhone);

        DBUtils.close();

        //Compare UI to DB
        Assert.assertEquals(expectedFullName,actualFullname);
        Assert.assertEquals(expectedEmail,actualEmail);
        Assert.assertEquals(expectedPhone,actualPhone);

     //   System.out.println(rowMap);




    }

    @Then("the information {string} should be same with database")
    public void the_information_should_be_same_with_database(String email) {

        //get actual data from UI-GUI-Front end-Browser-Website(whatever we see)
        BrowserUtils.waitFor(3);
        ContactInfoPage contactInfoPage = new ContactInfoPage();
        String actualFullname = contactInfoPage.contactFullName.getText();
        String actualEmail = contactInfoPage.email.getText();
        String actualPhone = contactInfoPage.phone.getText();

        System.out.println("actualFullname = " + actualFullname);
        System.out.println("actualEmail = " + actualEmail);
        System.out.println("actualPhone = " + actualPhone);

        //get expected data from database
        String query = "select concat(first_name,\" \",last_name) as fullname,last_name,e.email,p.phone\n" +
                "from orocrm_contact c join orocrm_contact_email e\n" +
                "on c.id = e.owner_id join orocrm_contact_phone p\n" +
                "on e.owner_id = p.owner_id\n" +
                "where e.email = '"+ email + "'";

        DBUtils.createConnection();

        //Since the result is only one row, we saved in Map<String,Object>
        //if you are dealing with multiple rows, use List<Map<String,Object>>
        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        String expectedFullName = (String) rowMap.get("fullname");
        String expectedEmail = (String) rowMap.get("email");
        String expectedPhone = (String) rowMap.get("phone");

        System.out.println("expectedFullName = " + expectedFullName);
        System.out.println("expectedEmail = " + expectedEmail);
        System.out.println("expectedPhone = " + expectedPhone);

        DBUtils.close();

        //Compare UI to DB
        Assert.assertEquals(expectedFullName,actualFullname);
        Assert.assertEquals(expectedEmail,actualEmail);
        Assert.assertEquals(expectedPhone,actualPhone);

        //   System.out.println(rowMap);
    }
}
