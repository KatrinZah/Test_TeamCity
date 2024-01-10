package com.example.teamcity.ui;

import com.codeborne.selenide.Condition;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.requests.checked.CheckedAllProjectsList;
import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.unchecked.ProjectUncheckedeRequest;
import com.example.teamcity.api.specifications.Specifications;
import com.example.teamcity.ui.pages.admin.CreateNewProject;
import com.example.teamcity.ui.pages.favorites.ProjectsPage;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class CreateNewProjectTest extends BaseUiTest{
    private String url = "https://github.com/KatrinZah/Test_TeamCity";
    @Test
    public void authorizeUserShouldBeAbleCreateNewProject(){
        var testData = testDataStorage.addTestData();
        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(testData.getProject().getName()));

        var listProjects = new CheckedAllProjectsList(Specifications.getSpecifications().authSpec(testData.getUser())).getProjectsList();

        var userProject = listProjects.getProject().stream()
                .filter(project -> testData.getProject().getName().equals(project.getName()))
                .findFirst();

        Project project = userProject.orElseGet(() -> null);
        softy.assertThat(project.getName()).isEqualTo((testData.getProject().getName()));
    }
    @Test
    public void authorizeUserTryToRunBuild(){
        var testData = testDataStorage.addTestData();
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

        var buildConfiguration = new CheckedBuildConfig(Specifications.getSpecifications().authSpec(testData.getUser()))
                .get(testData.getBuildType().getName());
        softy.assertThat(testData.getBuildType().getName()).isEqualTo(buildConfiguration.getName());
    }
    @Test
    public void authorizeUserTryCreateNewProjectWithEmptyField(){
        var testData = testDataStorage.addTestData();
        var projectName = "";
        loginAsUser(testData.getUser());

        var createNewProject = new CreateNewProject();

        createNewProject
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(projectName, testData.getBuildType().getName());

        new ProjectUncheckedeRequest(Specifications.getSpecifications().authSpec(testData.getUser()))
                .get(testData.getBuildType().getName())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);

        createNewProject.pageErrors();
    }
}
