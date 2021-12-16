package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        //TODO: решить вопрос с календарем
        //$("#dateOfBirthInput").setValue("06 Sep 1995");

        //$("#subjectsInput").setValue("Computer Science").pressEnter().setValue("Physics").pressEnter();
        $("#subjectsInput").setValue("Computer");
        $(byText("Computer Science")).click();


        $(byText("Reading")).click();
        $(byText("Music")).click();

        //TODO: решить вопрос с незакрывающимся окном выбора файла
        $(byText("Select picture")).click();
        File file = new File("src/131.jpg");
        $("#uploadPicture").uploadFile(file);

        $("#currentAddress").setValue("Novosibirsk");
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
}
