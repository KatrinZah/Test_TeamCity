package com.example.teamcity.api;

import com.example.teamcity.api.ganerators.TestDataStorage;
import com.example.teamcity.api.requests.CheckedRequests;
import com.example.teamcity.api.requests.UncheckedRequests;
import com.example.teamcity.api.specifications.Specifications;
import lombok.Getter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

@Getter

public class BaseApiTest extends BaseTest{

   // public TestData testData;
    public TestDataStorage testDataStorage;
    public CheckedRequests checkedWithSuperUser = new CheckedRequests(Specifications.getSpecifications().superUserSpec());
    public UncheckedRequests uncheckedWithSuperUser = new UncheckedRequests(Specifications.getSpecifications().superUserSpec());

    @BeforeMethod
    public void setupTest(){
        //testData = new TestDataGenerator().generate();
        testDataStorage = TestDataStorage.getTestDataStorage();
    }

    @AfterTest
    public void cleanTest(){
        //testData.delete();
        testDataStorage.delete();
    }

}
