package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    //Карта
    public static String getActiveCard() {
        return "4444_4444_4444_4441";
    }

    public static String getDeclinedCard() {
        return "4444_4444_4444_4442";
    }

    public static String getEmptyNumberCard() {
        return "";
    }

    public static String getZeroNumberCard() {
        return "0000_0000_0000_0000";
    }

    public static String getIncompleteNumberCard() {
        return "4444 4444 4444 444";
    }

    //Месяц
    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getMore12Month() {
        return "13";
    }

    //Год
    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getZeroYear() {
        return "00";
    }

    public static String getPreviousYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getMore5Years() {
        return LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy"));
    }

    //Владелец
    public static String getCurrentOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getEmptyOwner() {
        return "";
    }

    public static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }


    public static String getSymbolOwner() {
        return "%^%$%%^%$$%^";
    }

    public static String getNumeralOwner() {
        return "1234567891866";
    }

    //CVC
    public static String getCurrentCVC() {
        FakeValuesService fakerValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakerValuesService.numerify("###");
    }

    public static String getEmptyCVC() {
        return "";
    }

    public static String getTwoNumeralsCVC() {
        FakeValuesService fakerValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakerValuesService.numerify("##");
    }

    public static String getZeroNumberCVC() {
        return "000";
    }

    @Value
    public static class paymentStatus {
        String code;
    }

    @Value
    public static class creditStatus {
        String code;
    }

}