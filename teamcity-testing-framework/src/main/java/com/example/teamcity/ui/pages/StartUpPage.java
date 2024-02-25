package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;


public class StartUpPage extends Page{
    protected static final Duration LONG_WAITING = Duration.ofMinutes(3);
    private SelenideElement createAdminAccountHeader = element(Selectors.byId("header"));
    private SelenideElement proceedButton = $("#proceedButton");//element(Selectors.byId("proceedButton"));
    private SelenideElement acceptLisense = $("#accept");//element(Selectors.byId("accept"));
    private final SelenideElement dbTypeSelect = $("#dbType");
    private SelenideElement submitButton = $(".continueBlock > .submitButton");

    public StartUpPage open(){
        //Selenide.open("/");
        Selenide.open("/mnt");
        return this;
    }

    public SelenideElement getHeader() {
        return createAdminAccountHeader.shouldBe(visible, Duration.ofSeconds(35));
    }

    public StartUpPage setupTeamCityServer(){
 //       waitUntilPageIsLoaded();
//        proceedButton.click();
//        waitUntilPageIsLoaded();
//        proceedButton.click();
//        waitUntilPageIsLoaded();
//        acceptLisense.shouldBe(Condition.enabled, Duration.ofMinutes(5));
//        acceptLisense.scrollTo();
//        acceptLisense.click();
//        submit();

        proceedButton.click();
        dbTypeSelect.shouldBe(visible, LONG_WAITING);
        proceedButton.click();
        acceptLisense.should(exist, LONG_WAITING).scrollTo().click();
        submitButton.click();

        return this;
    }

}
