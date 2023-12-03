package com.example.teamcity.api.requests.checked;


import com.example.teamcity.api.models.Project;
//import com.example.teamcity.api.models.RequestSpecification;
import com.example.teamcity.api.requests.CrudInterface;
import com.example.teamcity.api.requests.Request;
import com.example.teamcity.api.requests.unchecked.ProjectUncheckedeRequest;
import org.apache.http.HttpStatus;
import io.restassured.specification.RequestSpecification;

public class ProjectCheckedRequest extends Request implements CrudInterface {


    public ProjectCheckedRequest(RequestSpecification spec) {
        super(spec);
    }

    @Override

    public Project create(Object obj) {
        return new ProjectUncheckedeRequest(spec).create(obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Project get(String id) {
        return new ProjectUncheckedeRequest(spec)
                .get(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public String delete(String id) {
        return new ProjectUncheckedeRequest(spec)//(user)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}


