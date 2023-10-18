package ru.netology.test;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class PaymentTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        SQLHelper.clearBD();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldBuyPaymentValidActiveCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).paymentApprovedForm();
        Assertions.assertEquals("APPROVED", SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyPaymentDeclinedCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getDeclinedCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).paymentDeclinedForm();
        Assertions.assertEquals("DECLINED", SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldIncompleteNumberCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getIncompleteNumberCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).cardNumberWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldEmptyNumberCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getEmptyNumberCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).cardNumberWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyPaymentZeroNumberCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getZeroNumberCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).cardNumberWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldEmptyMonth() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getEmptyMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).monthWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyPaymentZeroMonth() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getZeroMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).monthInvalidCardDate();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyPaymentMore12Month() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getMore12Month(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).monthInvalidCardDate();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldEmptyYear() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getEmptyYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).yearWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyPaymentZeroYear() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getZeroYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).yearInvalidCardDate();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyPaymentPreviousYear() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getPreviousYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).yearInvalidCardDate();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyPaymentMore5Year() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getMore5Years(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).yearWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }


    @Test
    void shouldEmptyOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getEmptyOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).ownerEmptyForm();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyCyrillicOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCyrillicOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).ownerWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuySymbolOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getSymbolOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).ownerWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldBuyNumeralOwner() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getNumeralOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).ownerWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldEmptyCVC() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getEmptyCVC());
        mainPage.paymentButton().paymentForm(card).cvcWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldTwoNumeralsCVC() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getTwoNumeralsCVC());
        mainPage.paymentButton().paymentForm(card).cvcWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

    @Test
    void shouldZeroNumberCVC() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getZeroNumberCVC());
        mainPage.paymentButton().paymentForm(card).cvcWrongFormat();
        Assertions.assertNull(SQLHelper.getPaymentStatus().getCode());
    }

}