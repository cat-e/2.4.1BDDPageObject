package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.data.DataHelper.getSecondCardInfo;
import static ru.netology.web.page.DashboardPage.*;

class MoneyTransferTest {

    @Test
    void shouldLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val dashboardPage = new DashboardPage();
        val firstCardBalance = dashboardPage.getCardBalance(getFirstCardInfo());
        val secondCardBalance = dashboardPage.getCardBalance(getSecondCardInfo());
        dashboardPage.replenishButtonClick(getSecondCardInfo());
//        dashboardPage.moneyTransfer(200);

    }

}
