package com.example.teamcity.ui;

import com.codeborne.selenide.Condition;
import com.example.teamcity.api.requests.checked.ProjectCheckedRequest;
import com.example.teamcity.api.specifications.Specifications;
import com.example.teamcity.ui.pages.admin.CreateNewProject;
import com.example.teamcity.ui.pages.favorites.ProjectsPage;
import org.testng.annotations.Test;

public class CreateNewProjectTest extends BaseUiTest{
    @Test
    public void authorizeUserShouldBeAbleCreateNewProject(){
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/KatrinZah/Test_TeamCity";
        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(testData.getProject().getName()));
    }
    @Test
    public void authorizeUserTryToRunBuild(){
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/KatrinZah/Test_TeamCity";
        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        var projectPage = new ProjectsPage();

        projectPage.open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(testData.getProject().getName()));

        projectPage.clickOnSubproject();
    }
    @Test
    public void authorizeUserTryCreateNewProjectWithEmptyField(){
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/KatrinZah/Test_TeamCity";
        var projectName = "";
        loginAsUser(testData.getUser());

        var createNewProject = new CreateNewProject();

        createNewProject
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(projectName, testData.getBuildType().getName());

        createNewProject.pageErrors();
    }
    @Test
    public void checkApiIsCreateNewProject(){
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/KatrinZah/Test_TeamCity";
        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(testData.getProject().getName()));

        System.out.println("Test UI = = = =" + testData.getProject().getId());

        var project = new ProjectCheckedRequest(Specifications.getSpecifications()
                .authSpec(testData.getUser()))
                .get(testData.getProject().getId());
        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());

    }
}
