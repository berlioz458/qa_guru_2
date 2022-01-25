package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.RegistrationPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {


    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        registrationPage.openPage();
    }

    @Test
    @DisplayName("Проверка возможности регистрации при заполнении всех полей формы")
    @Epic("Форма регистрации")
    @Story("Регистрация при заполнении всех полей в форме")
    @Severity(SeverityLevel.CRITICAL)
    void allFillFieldsTest() {
        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setUserEmail(email)
                .setGenderRadioButton().setUserNumberInput("0123456789");
        registrationPage.calendarComponents.setDate("06", "September", "1995");
        registrationPage
                .setSubjectsInput("Computer")
                .setHobbieReadingCheckBox()
                .setHobbieMusicCheckBox()
                .setPictureUpload("131.jpg")
                .setCurrentAddressInput("Novosibirsk");
        registrationPage.stateComponent.setStage("NCR");
        registrationPage.cityComponent.setCity("Delhi");
        registrationPage.submitForm();
        registrationPage
                .shouldHaveValue(firstName + " " + lastName)
                .shouldHaveValue(email)
                .shouldHaveValue("Female")
                .shouldHaveValue("0123456789")
                .shouldHaveValue("06 September,1995")
                .shouldHaveValue("Computer Science")
                .shouldHaveValue("Reading, Music")
                .shouldHaveValue("131.jpg")
                .shouldHaveValue("Novosibirsk")
                .shouldHaveValue("NCR Delhi");

    }

    @Test
    @DisplayName("Проверка возможности регистрации при заполнении только обязательных полей формы")
    @Epic("Форма регистрации")
    @Story("Регистриция при заполнении только обязательных полей")
    @Severity(SeverityLevel.CRITICAL)
    void mandatoryFillFieldsTest() {
        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setGenderRadioButton()
                .setUserNumberInput("0123456789");
        registrationPage.calendarComponents.setDate("06", "September", "1995");
        registrationPage.submitForm();

        registrationPage
                .shouldHaveValue(firstName + " " + lastName)
                .shouldHaveValue("Female")
                .shouldHaveValue("0123456789")
                .shouldHaveValue("06 September,1995");

        registrationPage
                .shouldNotHaveValue("Computer Science")
                .shouldNotHaveValue("berlioz458@gmail.com")
                .shouldNotHaveValue("Reading, Music")
                .shouldNotHaveValue("131.jpg")
                .shouldNotHaveValue("Novosibirsk")
                .shouldNotHaveValue("NCR Delhi");

    }

    @Test
    @DisplayName("Проверка невозможности регистрации если поля оставить пустые")
    @Epic("Форма регистрации")
    @Story("Регистрация при незаполненных полях на форме")
    @Severity(SeverityLevel.NORMAL)
    void emptyFillFieldsTest() {
        registrationPage.submitForm();
        $(".table").shouldNotBe(visible);
    }

    @Test
    @DisplayName("Валидация поля с номером телефона")
    @Epic("Форма регистрации")
    @Story("Возможность заполнения поля валидным номером телефона")
    @Severity(SeverityLevel.NORMAL)
    void validMaxLengthPhoneNumberTest() {
        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setGenderRadioButton()
                .setUserNumberInput("0123456789");
        registrationPage.calendarComponents.setDate("06", "September", "1995");
        registrationPage.submitForm();

        $(".table").shouldBe(visible);
        registrationPage
                .shouldHaveValue(firstName + " " + lastName)
                .shouldHaveValue("Female")
                .shouldHaveValue("0123456789")
                .shouldHaveValue("06 September,1995");

    }

    @Test
    @DisplayName("Ввод в номер телефона меньше символов допустимого значения")
    @Epic("Форма регистрации")
    @Story("Возможность заполнения поля не валидным номером телефона по длине")
    @Severity(SeverityLevel.TRIVIAL)
    void invalidMinLengthPhoneNumberTest() {
        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setGenderRadioButton()
                .setUserNumberInput("012345678");
        registrationPage.calendarComponents.setDate("06", "September", "1995");
        registrationPage.submitForm();

        $(".table").shouldNotBe(visible);
    }

    @Test
    @DisplayName("Ввод не валидных символов в номер телефона")
    @Epic("Форма регистрации")
    @Story("Возможность заполнения поля необычными символами")
    @Severity(SeverityLevel.TRIVIAL)
    void invalidPatternPhoneNumberTest() {
        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setGenderRadioButton()
                .setUserNumberInput("ваfff&%^$#");
        registrationPage.calendarComponents.setDate("06", "September", "1995");
        registrationPage.submitForm();
        $(".table").shouldNotBe(visible);
    }

    @Test
    @DisplayName("Валидация поля с почтовым адресом")
    @Epic("Форма регистрации")
    @Story("Возможность заполнения поля не по паттерну")
    @Severity(SeverityLevel.TRIVIAL)
    void invalidPatternEmailFieldTest() {
        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setUserEmail("a.-_A0@a.-_A0.aaaBBB")
                .setGenderRadioButton()
                .setUserNumberInput("0123456789");
        //pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
        registrationPage.calendarComponents.setDate("06", "September", "1995");
        registrationPage.submitForm();
        $(".table").shouldNotBe(visible);
    }

    @AfterEach
    void addAttach(){
        Attach.screenshotAs("Page screen after test");
    }
}
