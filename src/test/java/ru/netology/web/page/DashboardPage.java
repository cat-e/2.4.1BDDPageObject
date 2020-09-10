package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    public void Dashboard() {
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getCardBalance(){
        val text = cards.find((Condition) card1).text();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
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
        $("[data-test-id=action-deposit]").click();
        amountField.setValue(String.valueOf(TransferAmount.getAmount()));
        fromField.setValue("5559 0000 0000 0001");
        toField.setValue("5559 0000 0000 0002");
        transferButton.click();
        return new DashboardPage();

    }


}