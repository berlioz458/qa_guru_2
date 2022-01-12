package com.demoqa.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateComponent {
    public void setStage(String value) {
        $("#state").click();
        $(byText(value)).click();
    }
}
