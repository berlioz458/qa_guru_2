package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        registrationPage.openPage();
    }

    @Test
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
    void emptyFillFieldsTest() {
        registrationPage.submitForm();
        $(".table").shouldNotBe(visible);
    }

    @Test
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
}
