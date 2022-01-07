package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeEach
    void setUp() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void allFillFieldsTest() {
        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Shulinina");
        $("#userEmail").setValue("berlioz458@gmail.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#subjectsInput").setValue("Computer").pressEnter();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("131.jpg");
        $("#currentAddress").setValue("Novosibirsk");
        $("#state").scrollTo().click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();

        $(".table").shouldHave(
                text("Kate Shulinina"),
                text("berlioz458@gmail.com"),
                text("Female"),
                text("0123456789"),
                text("06 September,1995"),
                text("Computer Science"),
                text("Reading, Music"),
                text("131.jpg"),
                text("Novosibirsk"),
                text("NCR Delhi"));
    }

    @Test
    void mandatoryFillFieldsTest() {
        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").scrollTo().click();

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
        $("#submit").scrollTo().click();
        $(".table").shouldNotBe(visible);
    }

    @Test
    void invalidMaxLengthPhoneNumberTest() {
        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("012345678910");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").scrollTo().click();

        $(".table").shouldBe(visible);
        $(".table").shouldHave(
                text("Kate Shulinina"),
                text("Female"),
                text("0123456789"),
                text("06 September,1995"));
    }

    @Test
    void invalidMinLengthPhoneNumberTest() {
        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("012345678");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").scrollTo().click();

        $(".table").shouldNotBe(visible);
    }

    @Test
    void invalidPatternPhoneNumberTest() {
        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Shulinina");
        $(byText("Female")).click();
        $("#userNumber").setValue("ваfff&%^$#");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").scrollTo().click();

        $(".table").shouldNotBe(visible);
    }

    @Test
    void invalidPatternEmailFieldTest() {
        $("#firstName").setValue("Kate");
        $("#lastName").setValue("Shulinina");
        //pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"
        $("#userEmail").setValue("a.-_A0@a.-_A0.aaaBBB");
        $(byText("Female")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#submit").scrollTo().click();

        $(".table").shouldNotBe(visible);


    }
}
