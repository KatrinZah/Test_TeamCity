package com.example.teamcity.api.requests.checked;

import com.example.teamcity.api.models.ProjectList;
import com.example.teamcity.api.requests.Request;
import com.example.teamcity.api.requests.unchecked.UncheckedAllProjectsList;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class CheckedAllProjectsList extends Request {
    public CheckedAllProjectsList(RequestSpecification spec){
        super(spec);
    }
    public ProjectList getProjectsList(){
        return new UncheckedAllProjectsList(spec)
                .get()
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(ProjectList.class);
    }
}
