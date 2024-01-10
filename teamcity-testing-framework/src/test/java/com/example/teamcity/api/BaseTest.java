package com.example.teamcity.api;

import com.example.teamcity.api.ganerators.TestDataStorage;
import com.example.teamcity.api.requests.CheckedRequests;
import com.example.teamcity.api.requests.UncheckedRequests;
import com.example.teamcity.api.specifications.Specifications;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected SoftAssertions softy;

    public TestDataStorage testDataStorage;
    public CheckedRequests checkedWithSuperUser = new CheckedRequests(Specifications.getSpecifications().superUserSpec());
    public UncheckedRequests uncheckedWithSuperUser = new UncheckedRequests(Specifications.getSpecifications().superUserSpec());


    @BeforeMethod
    public void beforeTest(){
        softy = new SoftAssertions();
        testDataStorage = TestDataStorage.getTestDataStorage();

    }

    @AfterMethod
    public void afterTest(){
        testDataStorage.delete();
        softy.assertAll();
    }
}
