package com.demoqa.components;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class CalendarComponents {
    public void setDate(String day, String mouth, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(mouth);
        String dayLocator = format(".react-datepicker__day.react-datepicker__day--0%s",day);
        $(dayLocator).click();
    }
}
