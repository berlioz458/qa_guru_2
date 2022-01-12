package com.demoqa.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CityComponent {
    public void setCity(String value) {
        $("#city").click();
        $(byText(value)).click();
    }
}
