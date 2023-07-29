package com.stepDefinitions;

import com.utility.ConfigurationReader;

import com.utility.DB_Util;
import com.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;


public class Hooks {

    @Before
    public void Setup()
    {
        Driver.getDriver().manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
    }

    @After
    public void tearDown(Scenario scenario)
    {
        if(scenario.isFailed())
        {
           byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
           scenario.attach(screenshot,"img/png","screenshot");
        }

        Driver.closeDriver();
    }


    @Before("@db")
    public void setupDB(){
        DB_Util.createConnection();
        System.out.println("Connecting database....");

    }

    @After("@db")
    public void destroyDB(){
        DB_Util.destroy();
        System.out.println("Closing database....");

    }





}
