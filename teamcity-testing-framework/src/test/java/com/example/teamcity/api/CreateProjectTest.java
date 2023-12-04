package com.example.teamcity.api;

import com.example.teamcity.api.enums.Role;
import com.example.teamcity.api.ganerators.TestDataGenerator;
import com.example.teamcity.api.requests.CheckedRequests;
import com.example.teamcity.api.requests.UncheckedRequests;
import com.example.teamcity.api.requests.checked.CheckedUser;
import com.example.teamcity.api.requests.checked.ProjectCheckedRequest;
import com.example.teamcity.api.specifications.Specifications;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseApiTest {
    @Test
    public void unauthorizedUserTryToCreateProject() {
        var testData = testDataStorage.addTestData();

        new UncheckedRequests(Specifications.getSpecifications().unAuthSpec())
                .getProjectRequest()
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(Matchers.containsString("Authentication required"));

        uncheckedWithSuperUser.getProjectRequest()
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:" +
                        testData.getProject().getId() + "'"));
    }

    @Test
    public void authorizedUserTryToCreateProject() {
        var testData = testDataStorage.addTestData();

        new CheckedUser(Specifications.getSpecifications()
                .superUserSpec()).create(testData.getUser());

        var project = new CheckedRequests(Specifications.getSpecifications().authSpec(testData.getUser()))
                .getProjectRequest()
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }

    @Test
    public void superUserTryToCreateProject() {
        var testData = testDataStorage.addTestData();

        var project = checkedWithSuperUser.getProjectRequest()
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }

    @Test
    public void systemAdminShouldHaveRightsToCreateProject() {
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.setNewRoles(Role.SYSTEM_ADMIN));

        uncheckedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        var project = new ProjectCheckedRequest(Specifications.getSpecifications()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }

    @Test
    public void projectAdminShouldNotHaveRightsToCreateProject() {
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.setNewRoles(Role.PROJECT_ADMIN));

        uncheckedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        new CheckedRequests(Specifications.getSpecifications().authSpec(testData.getUser()))
                .getProjectRequest()
                .create(testData.getProject());

        uncheckedWithSuperUser.getProjectRequest()
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:" +
                        testData.getProject().getId() + "'"));
    }

    @Test
    public void projectManagerShouldNotHaveRightsToCreateProject(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.setNewRoles(Role.PROJECT_MANAGER));

        uncheckedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        new UncheckedRequests(Specifications.getSpecifications().authSpec(testData.getUser()))
                .getProjectRequest()
                        .create(testData.getProject());

        uncheckedWithSuperUser.getProjectRequest()
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:"+
                        testData.getProject().getId()+"'"));
    }

    @Test
    public void projectDeveloperShouldNotHaveRightsToCreateProject() {
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.setNewRoles(Role.PROJECT_DEVELOPER));

        uncheckedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        new UncheckedRequests(Specifications.getSpecifications().authSpec(testData.getUser()))
                .getProjectRequest()
                .create(testData.getProject());

        uncheckedWithSuperUser.getProjectRequest()
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:" +
                        testData.getProject().getId() + "'"));
    }

    @Test
    public void projectViewerShouldNotHaveRightsToCreateProject(){
        var testData = testDataStorage.addTestData();
        testData.getUser().setRoles(TestDataGenerator.setNewRoles(Role.PROJECT_VIEWER));

        uncheckedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        new UncheckedRequests(Specifications.getSpecifications().authSpec(testData.getUser()))
                .getProjectRequest()
                .create(testData.getProject());

        uncheckedWithSuperUser.getProjectRequest()
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:"+
                        testData.getProject().getId()+"'"));
    }



}
