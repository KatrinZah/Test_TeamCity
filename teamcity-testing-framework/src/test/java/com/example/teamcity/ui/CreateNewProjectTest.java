package com.example.teamcity.ui;

import com.codeborne.selenide.Condition;
import com.example.teamcity.ui.pages.favorites.ProjectsPage;
import com.example.teamcity.ui.pages.admin.CreateNewProject;
import org.testng.annotations.Test;

public class CreateNewProjectTest extends BaseUiTest{
    @Test
    public void authorizeUserShouldBeAbleCreateNewProject(){
        var testDate = testDataStorage.addTestData();
        var url = "https://github.com/KatrinZah/Test_TeamCity";
        loginAsUser(testDate.getUser());

        new CreateNewProject()
                .open(testDate.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testDate.getProject().getName(), testDate.getBuildType().getName());

        new ProjectsPage().open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(testDate.getProject().getName()));
    }
}
