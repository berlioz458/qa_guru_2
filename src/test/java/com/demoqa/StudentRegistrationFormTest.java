package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void successTest() {
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

        $("#uploadPicture").uploadFile(new File("src/131.jpg"));

        $("#currentAddress").setValue("Novosibirsk");

        $("#state").click();
        $(byText("NCR")).click();

        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").click();

        $(".table").
                shouldHave(text("Kate Shulinina")).
                shouldHave(text("berlioz458@gmail.com")).
                shouldHave(text("Female")).
                shouldHave(text("0123456789")).
                shouldHave(text("06 September,1995")).
                shouldHave(text("Computer Science")).
                shouldHave(text("Reading, Music")).
                shouldHave(text("131.jpg")).
                shouldHave(text("Novosibirsk")).
                shouldHave(text("NCR Delhi"));
    }
}
