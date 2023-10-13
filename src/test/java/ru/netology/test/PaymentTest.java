package ru.netology.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class PaymentTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        SQLHelper.clearBD();
    }

    @Test
    void shouldBuyPaymentValidActiveCard() {
        var mainPage = new MainPage();
        CardInfo card = new CardInfo(getActiveCard(), getCurrentMonth(), getCurrentYear(), getCurrentOwner(), getCurrentCVC());
        mainPage.paymentButton().paymentForm(card).paymentApprovedForm();
        Assertions.assertEquals("APPROVED", SQLHelper.getpaymentStatus());
    }
}
