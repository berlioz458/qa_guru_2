package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.components.CalendarComponents;
import com.demoqa.components.CityComponent;
import com.demoqa.components.StateComponent;
import io.qameta.allure.Step;
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

    @Step("Перейти на страницу регистрации")
    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Ввести имя пользователя")
    public RegistrationPage setFirstNameInput(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Ввести фамилию пользователя")
    public RegistrationPage setLastNameInput(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Проверяет наличие значения в таблице")
    public RegistrationPage shouldHaveValue(String value) {
        resultTable.shouldHave(text(value));
        return this;
    }

    @Step("Проверяет отсутствие значения в таблице")
    public RegistrationPage shouldNotHaveValue(String value) {
        resultTable.shouldNotHave(text(value));
        return this;
    }

    @Step("Ввести email пользователя")
    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    //TODO перенести выбор гендера в компонент
    @Step("Выбрать гендер пользователя")
    public RegistrationPage setGenderRadioButton() {
        genderRadioButton.click();

        return this;
    }

    @Step("Ввести номер телефона")
    public RegistrationPage setUserNumberInput(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Выбрать предмет")
    public RegistrationPage setSubjectsInput(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    //TODO перенести выбор хобби в компонент
    @Step("Выбрать музыку как хобби")
    public RegistrationPage setHobbieMusicCheckBox() {
        hobbieMusicCheckBox.click();

        return this;
    }

    @Step("Выбрать чтение как хобби")
    public RegistrationPage setHobbieReadingCheckBox() {
        hobbieReadingCheckBox.click();

        return this;
    }

    @Step("Загрузить картинку профиля")
    public RegistrationPage setPictureUpload(String value) {
        pictureUpload.uploadFromClasspath(value);

        return this;
    }

    @Step("Ввести адрес проживания")
    public RegistrationPage setCurrentAddressInput(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    @Step("Нажать на кнопку")
    public RegistrationPage submitForm(){
        submitButton.click();

        return this;
    }
}
