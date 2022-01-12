package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.components.CalendarComponents;
import com.demoqa.components.CityComponent;
import com.demoqa.components.StateComponent;
import org.checkerframework.checker.units.qual.C;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    SelenideElement firstNameInput =  $("#firstName"),
                    lastNameInput = $("#lastName"),
                    resultTable = $(".table"),
                    userEmailInput = $("#userEmail"),
                    genderRadioButton = $(byText("Female")),
                    userNumberInput = $("#userNumber"),
                    subjectsInput = $("#subjectsInput"),
                    hobbieReadingCheckBox = $(byText("Reading")),
                    hobbieMusicCheckBox = $(byText("Music")),
                    pictureUpload = $("#uploadPicture"),
                    currentAddressInput = $("#currentAddress"),
                    submitButton = $("#submit")
                    ;

    public CalendarComponents calendarComponents = new CalendarComponents();
    public StateComponent stateComponent = new StateComponent();
    public CityComponent cityComponent = new CityComponent();

    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    public RegistrationPage setFirstNameInput(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastNameInput(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage shouldHaveValue(String value) {
        resultTable.shouldHave(text(value));
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGenderRadioButton() {
        genderRadioButton.click();

        return this;
    }

    public RegistrationPage setUserNumberInput(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbieMusicCheckBox() {
        hobbieMusicCheckBox.click();

        return this;
    }

    public RegistrationPage setHobbieReadingCheckBox() {
        hobbieReadingCheckBox.click();

        return this;
    }

    public RegistrationPage setPictureUpload(String value) {
        pictureUpload.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setCurrentAddressInput(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public RegistrationPage submitForm(){
        submitButton.click();

        return this;
    }
}
