package com.example.teamcity.api;

import com.example.teamcity.api.enums.Role;
import com.example.teamcity.api.ganerators.TestDataGenerator;
import com.example.teamcity.api.requests.UncheckedRequests;
import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.checked.ProjectCheckedRequest;
import com.example.teamcity.api.requests.unchecked.ProjectUncheckedeRequest;
import com.example.teamcity.api.requests.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.specifications.Specifications;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


public class RolesTest extends BaseApiTest{

    @Test
    public void unTest(){
        var testData = testDataStorage.addTestData();

        new ProjectUncheckedeRequest(Specifications.getSpecifications()
                .unAuthSpec())
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(Matchers.containsString("Authentication required"));

        new ProjectUncheckedeRequest(Specifications.getSpecifications()
                .superUserSpec())
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);


    }

    @Test
    public void unauthorizedUserShouldNotHaveRightToCreateProject(){
        var testData = testDataStorage.addTestData();
//unauth
       new UncheckedRequests(Specifications.getSpecifications().unAuthSpec())
               .getProjectRequest()
               .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(Matchers.containsString("Authentication required"));

       //        uncheckedWithSuperUser.getProjectRequest()
//                .create(testData.getProject())
//                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
//                .body(Matchers.containsString("Authentication required"));
        //super
        uncheckedWithSuperUser.getProjectRequest()
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:"+
                   testData.getProject().getId()+"'"));

    }

@Test
public void systemAdminShouldHaveRightsToCreateProject(){
        var testData = testDataStorage.addTestData();

        testData.getUser().setRoles(TestDataGenerator
                .generateRoles(Role.SYSTEM_ADMIN, "p:" + testData.getProject().getId()));

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        var project = new ProjectCheckedRequest(Specifications.getSpecifications()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }


    @Test

    public void projectAdminShouldHaveRightsToCreateBuildConfigToHisProject (){
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getProjectRequest()
                .create(testData.getProject());

        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.PROJECT_ADMIN, "p:" + testData.getProject().getId()));

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


//       new ProjectCheckedRequest(Specifications.getSpecifications()
//                .authSpec(testData.getUser()))
//                .create(testData.getProject());

        var buildConfig = new CheckedBuildConfig(Specifications.getSpecifications().authSpec(testData.getUser()))
                .create(testData.getBuildType());

        softy.assertThat(buildConfig.getId()).isEqualTo(testData.getBuildType().getId());

    }

    @Test

    public void projectAdminShouldNotHaveRightsToCreateBuildConfigToAnotherProject(){

        var testData_1 = testDataStorage.addTestData();
        var testData_2 = testDataStorage.addTestData();

        checkedWithSuperUser.getProjectRequest().create(testData_1.getProject());
        checkedWithSuperUser.getProjectRequest().create(testData_2.getProject());


        testData_1.getUser().setRoles(TestDataGenerator
                .generateRoles(Role.PROJECT_ADMIN, "p:" + testData_1.getProject().getId()));
        checkedWithSuperUser.getUserRequest()
                .create(testData_1.getUser());


       testData_2.getUser().setRoles(TestDataGenerator
               .generateRoles(Role.PROJECT_ADMIN, "p:" + testData_2.getProject().getId()));
         checkedWithSuperUser.getUserRequest()
                 .create(testData_2.getUser());


        new UncheckedBuildConfig(Specifications.getSpecifications().authSpec(testData_2.getUser()))
                .create(testData_1.getBuildType())
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);//BAD_REQUEST);

    }

}
