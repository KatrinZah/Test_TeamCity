package com.example.teamcity.api;

import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.requests.checked.AuthRequest;
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
    public void getTokenTest(){
      var testData = testDataStorage.addTestData();

      var user = testData.getUser();
//        user.setUsername("admin");
//        user.setPassword("admin");

//        var token = new AuthRequest(user).getCsrfToken();
//        System.out.println(token);

        var spec =  new CheckedUser(Specifications.getSpecifications().superUserSpec())
                .create(testData.getUser());

        System.out.println(testData.getUser().getUsername());
        System.out.println(testData.getUser().getPassword());

     // var spec = Specifications.getSpecifications().authSpec(user);//unAuthSpec();//superUserSpec();

//        Object projectDescription = NewProjectDescription
//                .builder()
//                .parentProject(Project.builder()
//                        .locator("_Root")
//                        .build())
//                .name("name1pp1p1")
//                .id("id1pp1p1")
//                .copyAllAssociatedSettings(true)
//                .build();

       // new ProjectCheckedRequest(checkedWithSuperUser).create(projectDescription);//(projectDescription);

//        System.out.println(testData.getProject());


     // new CheckedUser(spec).create(user);


////                new User();
////        user.setUsername("admin");
////        user.setPassword("admin");
//
//        var token = new AuthRequest(user).getCsrfToken();
//        System.out.println(token);


    }

    @Test
    public void testTest(){

        var testData = testDataStorage.addTestData();
        var user = testData.getUser();
        var role = new com.example.teamcity.api.models.Role();
        //role.setRoleId(Role.SYSTEM_ADMIN);
        user.setUsername("username1");
        user.setPassword("password1");
        //user.setRoles = Role.SYSTEM_ADMIN;
        var token = new AuthRequest(user).getCsrfToken();

        var projectDescription = NewProjectDescription
                .builder()
                .parentProject(Project.builder()
                        .locator("_Root")
                        .build())
                .name("name1iii")
                .id("id1iii")
                .copyAllAssociatedSettings(true)
                .build();

        var project = new ProjectCheckedRequest(Specifications.getSpecifications().authSpec(user)).create(projectDescription);

    }


}
//    public void buildConfigurationTest() {
//        var user = User.builder()
//                .username("admin")
//                .password("admin")
//                .build();
//
//
//        var projectDescription = NewProjectDescription
//                .builder()
//                .parentProject(Project.builder()
//                        .locator("_Root")
//                        .build())
//                .name("name1")
//                .id("id1")
//                .copyAllAssociatedSettings(true)
//                .build();


    //var project = new ProjectCheckedRequest(user).create(projectDescription);

        //var token = RestAssured.get("http://admin:admin@localhost:8111/authenticationTest.html?csrf")
//        var token = RestAssured
//                .given()
//                .spec(Specifications.getSpecifications().authSpec(user))
//                .get("/authenticationTest.html?csrf")
//                .then().assertThat().statusCode(HttpStatus.SC_OK)
//                .extract().asString();

   // var token = new AuthRequest(user).getCsrfToken();

 //    -   System.out.println(token);
   // }

//}

