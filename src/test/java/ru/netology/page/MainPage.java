package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement payment = $(byText("Купить"));
    private SelenideElement credit = $(byText("Купить в кредит"));


    public PaymentPage paymentButton() {
        payment.click();
        return new PaymentPage();
    }

    public CreditPage creditButton() {
        credit.click();
        return new CreditPage();
    }


}
