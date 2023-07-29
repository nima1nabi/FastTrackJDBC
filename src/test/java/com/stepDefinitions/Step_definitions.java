package com.stepDefinitions;

import com.pages.DashboardPage;
import com.pages.Loginpage;
import com.utility.BrowserUtil;
import com.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class Step_definitions {
    Loginpage loginpage = new Loginpage();
    DashboardPage dashboardPage = new DashboardPage();
    String actualUsersCount;
    String actualBooksCount;
    String actualBorrowedBooksCount;
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginpage.login(user);
        BrowserUtil.waitFor(4);

    }
    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {
        //Trying different ways of getting the module texts
        actualUsersCount = dashboardPage.usersNumber.getText();
        System.out.println("actualUsersCount = " + actualUsersCount);

        actualBooksCount = dashboardPage.getModuleCount("Books");
        System.out.println("actualBooksCount = " + actualBooksCount);

        actualBorrowedBooksCount = dashboardPage.getModuleCount("Borrowed Books");
        System.out.println("actualBorrowedBooksCount = " + actualBorrowedBooksCount);


    }
    @Then("the informations should be same with database")
    public void the_informations_should_be_same_with_database() {
        //DB_Util.createConnection();
        String query = "select count(*) from users";
        DB_Util.runQuery(query);
        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserCount = " + expectedUserCount);
        // COMPARE
        Assert.assertEquals(expectedUserCount,actualUsersCount);

        // BOOKS
        // RUN QUERY
        DB_Util.runQuery("select count(*) from books");
        // GET BOOKS COUNT
        String expectedBooksCount = DB_Util.getCellValue(1, 1);
        System.out.println("expectedBooksCount = " + expectedBooksCount);
        // COMPARE
        Assert.assertEquals(expectedBooksCount,actualBooksCount);

        // BORROWED BOOKS
        // RUN QUERY
        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        // GET BORROWED BOOKS COUNT
        String expectedBorrowedBooksCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBooksCount = " + expectedBorrowedBooksCount);
        // COMPARE
        Assert.assertEquals(expectedBorrowedBooksCount,actualBorrowedBooksCount);




    }

}
