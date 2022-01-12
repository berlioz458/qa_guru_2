package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        registrationPage.openPage();
    }

    @Test
    void allFillFieldsTest() {
        registrationPage
                .setFirstNameInput("Kate")
                .setLastNameInput("Shulinina")
                .setUserEmail("berlioz458@gmail.com")
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
                .shouldHaveValue("Kate Shulinina")
                .shouldHaveValue("berlioz458@gmail.com")
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
                .setFirstNameInput("Kate")
                .setLastNameInput("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").click();

        $(".table").shouldHave(
                text("Kate Shulinina"),
                text("Female"),
                text("0123456789"),
                text("06 September,1995"));
        $(".table").shouldNotHave(
                text("Computer Science"),
                text("berlioz458@gmail.com"),
                text("Reading, Music"),
                text("131.jpg"),
                text("Novosibirsk"),
                text("NCR Delhi")
                );
    }

    @Test
    void emptyFillFieldsTest() {
        $("#submit").click();
        $(".table").shouldNotBe(visible);
    }

    @Test
    void invalidMaxLengthPhoneNumberTest() {
        registrationPage
                .setFirstNameInput("Kate")
                .setLastNameInput("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("012345678910");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").click();

        $(".table").shouldBe(visible);
        $(".table").shouldHave(
                text("Kate Shulinina"),
                text("Female"),
                text("0123456789"),
                text("06 September,1995"));
    }

    @Test
    void invalidMinLengthPhoneNumberTest() {
        registrationPage
                .setFirstNameInput("Kate")
                .setLastNameInput("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("012345678");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").click();

        $(".table").shouldNotBe(visible);
    }

    @Test
    void invalidPatternPhoneNumberTest() {
        registrationPage
                .setFirstNameInput("Kate")
                .setLastNameInput("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("ваfff&%^$#");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").click();

        $(".table").shouldNotBe(visible);
    }

    @Test
    void invalidPatternEmailFieldTest() {
        registrationPage
                .setFirstNameInput("Kate")
                .setLastNameInput("Shulinina");
        //pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
        $("#userEmail").setValue("a.-_A0@a.-_A0.aaaBBB");
        $(byText("Female")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").click();

        $(".table").shouldNotBe(visible);


    }
}
