import com.pages.Loginpage;
import io.cucumber.java.en.*;

public class Stepdefs {

    Loginpage loginpage =new Loginpage();


    String actualUsersCount;
    String actualBooksCount;
    String actualBorrowedBooksCount;
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginpage.login(user);



    }
    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {

    }
    @Then("the informations should be same with database")
    public void the_informations_should_be_same_with_database() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
