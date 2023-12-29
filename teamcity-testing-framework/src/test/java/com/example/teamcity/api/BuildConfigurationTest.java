package com.example.teamcity.api;

import com.example.teamcity.api.requests.checked.CheckedUser;
import com.example.teamcity.api.requests.checked.ProjectCheckedRequest;
import com.example.teamcity.api.specifications.Specifications;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest {

    @Test
    public void buildConfigurationTest() {
        var testData = testDataStorage.addTestData();

        new CheckedUser(Specifications.getSpecifications()
                .superUserSpec()).create(testData.getUser());

        System.out.println(testData.getUser().getUsername());

        var project = new ProjectCheckedRequest(Specifications.getSpecifications()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }

    @Test
    public void nameBuildConfigurationTest() {
        var testData = testDataStorage.addTestData();

        new CheckedUser(Specifications.getSpecifications()
                .superUserSpec()).create(testData.getUser());

        System.out.println(testData.getUser().getUsername());

        var project = new ProjectCheckedRequest(Specifications.getSpecifications()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getName()).isEqualTo(testData.getProject().getName());

    }

    @Test
    public void parentProjectIdBuildConfigurationTest() {
        var testData = testDataStorage.addTestData();

        new CheckedUser(Specifications.getSpecifications()
                .superUserSpec()).create(testData.getUser());

        System.out.println(testData.getUser().getUsername());

        var project = new ProjectCheckedRequest(Specifications.getSpecifications()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getParentProjectId()).isEqualTo(testData.getProject().getParentProject());

    }

}


