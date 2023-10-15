package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class CreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        SQLHelper.clearBD();
    }

    @Test
    void shouldBuyCreditValidActiveCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).creditApprovedForm();
        Assertions.assertEquals("APPROVED", SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditDeclinedCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getDeclinedCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).errorForm();
        Assertions.assertEquals("DECLINED", SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldIncompleteNumberCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getIncompleteNumberCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).cardNumberError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldEmptyNumberCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getEmptyNumberCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).cardNumberError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyPaymentZeroNumberCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getZeroNumberCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).errorForm();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldEmptyMonth() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getEmptyMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).monthError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditZeroMonth() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getZeroMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).monthError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditMore12Month() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getMore12Month(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).monthError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldEmptyYear() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getEmptyYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).yearError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditZeroYear() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getZeroYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).yearError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditPreviousYear() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getPreviousYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).yearError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditMore5Year() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getMore5Years(), getCurrentOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).errorForm();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }


    @Test
    void shouldEmptyOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getEmptyOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).ownerError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditCyrillicOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCyrillicOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).errorForm();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditSymbolOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getSymbolOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).errorForm();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldBuyCreditNumeralOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getNumeralOwner(), getCurrentCVC());
        mainPage.creditButton().creditForm(card).errorForm();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldEmptyCVC() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getEmptyCVC());
        mainPage.creditButton().creditForm(card).cvcError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldTwoNumeralsCVC() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getTwoNumeralsCVC());
        mainPage.creditButton().creditForm(card).cvcError();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

    @Test
    void shouldZeroNumberCVC() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getZeroNumberCVC());
        mainPage.creditButton().creditForm(card).errorForm();
        Assertions.assertNull(SQLHelper.getCreditStatus().getCode());
    }

}
