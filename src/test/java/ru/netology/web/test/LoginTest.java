package ru.netology.web.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class LoginTest {

    @AfterAll
    static void cleanUP() {
        DataHelper.cleanUP();
    }

    @Test
    void shouldLogin() {
      open("http://localhost:9999");
      var loginPage = new LoginPageV1();
      var authInfo = DataHelper.getAuthInfo();
      var verificationPage = loginPage.validLogin(authInfo);
      var verificationCode = DataHelper.getLastVerificationCode();
      var dashboardPage = verificationPage.validVerify(verificationCode);
    }

}

