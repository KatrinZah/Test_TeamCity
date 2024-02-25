package com.example.teamcity.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.teamcity.api.BaseTest;
import com.example.teamcity.api.config.Config;
import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.checked.CheckedUser;
import com.example.teamcity.api.specifications.Specifications;
import com.example.teamcity.ui.pages.LoginPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.Map;

public class BaseUiTest extends BaseTest {
    @BeforeSuite
    @BeforeTest
    public void setupUiTests(){
//        Configuration.browser = "firefox";
//        Configuration.baseUrl = "http://" + Config.getProperty("host");
//        Configuration.remote = Config.getProperty("remote");
//        Configuration.reportsFolder = "target/surefire-reports";
//        Configuration.downloadsFolder = "target/downloads";
//
//        BrowserSettings.serup(Config.getProperty("browser"));

        // Нет никакой необходимости писать класс-конфигуратор браузера, так как Selenide последних версий делает это из коробки
        Configuration.browser = Config.getProperty("browser");
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
        // Проводим тестирование на фиксированном разрешении экрана
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableLog", true
        ));
        Configuration.downloadsFolder = "target/downloads";
        Configuration.reportsFolder = "target/reports/tests";

        // Подключаем степы и скриншоты в Allure репорте
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));

    }

    public void loginAsUser(User user){
        new CheckedUser(Specifications.getSpecifications().superUserSpec())
                .create(user);
        new LoginPage().open().login(user);
    }

}
