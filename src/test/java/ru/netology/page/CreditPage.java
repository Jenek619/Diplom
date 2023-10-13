package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private SelenideElement cardNumber = $(byText("Номер карты"));
    private SelenideElement month = $(byText("Месяц"));
    private SelenideElement year = $(byText("Год"));
    private SelenideElement owner = $(byText("Владелец"));
    private SelenideElement cvc = $(byText("CVC/CVV"));
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement approvedForm = $(".notification__content");

    public CreditPage creditForm(CardInfo card) {
        cardNumber.setValue(card.getCardNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        owner.setValue(card.getOwner());
        cvc.setValue(card.getCVC());
        continueButton.click();
       return this;

    }

    public void creditApprovedForm() {
        approvedForm.shouldBe(visible, Duration.ofSeconds(15));
    }
}
