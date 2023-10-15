package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement approvedForm = $(".notification_status_ok");
    private SelenideElement cardError = $(byText("Номер карты")).parent().$(".input__sub");
    private SelenideElement monthError = $(byText("Месяц")).parent().$(".input__sub");
    private SelenideElement yearError = $(byText("Год")).parent().$(".input__sub");
    private SelenideElement ownerError = $(byText("Владелец")).parent().$(".input__sub");
    private SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(".input__sub");
    private SelenideElement errorForm = $(".notification__content");

    public PaymentPage paymentForm(CardInfo card) {
        cardNumber.setValue(card.getCardNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        owner.setValue(card.getOwner());
        cvc.setValue(card.getCVC());
        continueButton.click();
        return this;
    }

    public void paymentApprovedForm() {
        approvedForm.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorForm() {
        errorForm.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void cardNumberError() {
        cardError.shouldBe(Condition.visible);
    }

    public void monthError() {
        monthError.shouldBe(Condition.visible);
    }

    public void yearError() {
        yearError.shouldBe(Condition.visible);
    }

    public void ownerError() {
        ownerError.shouldBe(Condition.visible);
    }

    public void cvcError() {
        cvcError.shouldBe(Condition.visible);
    }
}