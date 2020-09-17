package ru.netology.web.test;

import lombok.val;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static ru.netology.web.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.data.DataHelper.getSecondCardInfo;
import static ru.netology.web.page.DashboardPage.*;

class MoneyTransferTest {

    @Test
    void shouldLogin() {
        int amount = 1000;
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalance = dashboardPage.getCardBalance(getFirstCardInfo());
        val secondCardBalance = dashboardPage.getCardBalance(getSecondCardInfo());
        val transferPage = dashboardPage.replenishButtonClick(getSecondCardInfo());
        transferPage.moneyTransfer(amount, getFirstCardInfo().getCardNumber());
        val dashboardPageAfterTransfer = new DashboardPage();
        dashboardPageAfterTransfer.getCardBalance(getFirstCardInfo());
        assertThat(dashboardPageAfterTransfer, containsString(String.valueOf(getFirstCardInfo())));
        assertThat(dashboardPageAfterTransfer, containsString(String.valueOf(getSecondCardInfo())));

    }

    private void assertThat(DashboardPage dashboardPageAfterTransfer, Matcher<String> containsString) {
    }

}


