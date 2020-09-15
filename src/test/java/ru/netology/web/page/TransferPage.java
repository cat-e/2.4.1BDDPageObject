package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.web.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.data.DataHelper.getSecondCardInfo;

@Value
public class TransferPage {


    private static int amount;

    public static int getAmount() {
        return amount;
    }



    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from");
    private SelenideElement toField = $("[data-test-id=to");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage moneyTransfer(Integer amount, String card) {
        amountField.setValue(String.valueOf(TransferPage.getAmount()));
        fromField.setValue(String.valueOf(getFirstCardInfo()));
//        toField.setValue(String.valueOf(getSecondCardInfo()));
        transferButton.click();
        return new TransferPage();

    }


}
