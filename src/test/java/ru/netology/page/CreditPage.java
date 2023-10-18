package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement approvedForm = $(".notification__content");
    private SelenideElement cardError = $(byText("Номер карты")).parent().$(".input__sub");
    private SelenideElement monthError = $(byText("Месяц")).parent().$(".input__sub");
    private SelenideElement yearError = $(byText("Год")).parent().$(".input__sub");
    private SelenideElement ownerError = $(byText("Владелец")).parent().$(".input__sub");
    private SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(".input__sub");
    private SelenideElement errorForm = $(".notification__content");

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
        approvedForm.shouldHave(ownText("Операция одобрена Банком.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    public void creditDeclinedForm() {
        errorForm.shouldHave(ownText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(15));
    }

    public void cardNumberWrongFormat() {
        cardError.shouldHave(ownText("Неверный формат")).shouldBe(visible);
    }

    public void monthWrongFormat() {
        monthError.shouldHave(ownText("Неверный формат")).shouldBe(visible);
    }

    public void monthInvalidCardDate() {
        monthError.shouldHave(ownText("Неверно указан срок действия карты")).shouldBe(visible);
    }

    public void yearWrongFormat() {
        yearError.shouldHave(ownText("Неверный формат")).shouldBe(visible);
    }

    public void yearInvalidCardDate() {
        yearError.shouldHave(ownText("Истёк срок действия карты")).shouldBe(visible);
    }

    public void ownerEmptyForm() {
        ownerError.shouldHave(ownText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    public void ownerWrongFormat() {
        ownerError.shouldHave(ownText("Неверный формат")).shouldBe(visible);
    }

    public void cvcWrongFormat() {
        cvcError.shouldHave(ownText("Неверный формат")).shouldBe(visible);
    }
}
