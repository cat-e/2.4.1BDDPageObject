package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.web.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.data.DataHelper.getSecondCardInfo;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private static ElementsCollection cards = $$(".list__item");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    public static SelenideElement card1 = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0]");
    private static SelenideElement card2 = $("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d]");

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        val text = cards.findBy(text(cardInfo.getCardNumber().substring(15, 19))).text();
        return extractBalance(text);
    }


    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage replenishButtonClick(DataHelper.CardInfo cardInfo) {
        cards.findBy(text(cardInfo.getCardNumber().substring(15, 19))).$(".button").click();
        return new DashboardPage();
    }

    @Value
    public static class TransferAmount {
        private static int amount;

        public static int getAmount() {
            return amount;
        }
    }

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from");
    private SelenideElement toField = $("[data-test-id=to");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashboardPage moneyTransfer(Integer amount) {
        amountField.setValue(String.valueOf(TransferAmount.getAmount()));
        fromField.setValue(String.valueOf(getFirstCardInfo()));
        toField.setValue(String.valueOf(getSecondCardInfo()));
        transferButton.click();
        return new DashboardPage();

    }


}