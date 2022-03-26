package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.demoqa.config.CredentialsConfig;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.RegistrationPage;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);


    @BeforeAll
    static void setUp() {
        //String remoteUrl = System.getProperty("remoteUrl");
        String remoteUrl = "selenoid.autotests.cloud/wd/hub";
        String login = config.login();
        String password = config.password();

        System.out.println(login + " " + password);

        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browserSize = "1920x1080";
        Configuration.remote = String.format("https://%s:%s@%s", login, password, remoteUrl);
        //Configuration.browser = System.getProperty("browser");
        //Configuration.browserVersion = System.getProperty("browserVersion");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttach(){
        Attach.screenshotAs("Page screen after test");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
