package com.example.teamcity.api.requests.unchecked;

import com.example.teamcity.api.requests.Request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static com.example.teamcity.api.requests.unchecked.ProjectUncheckedeRequest.PROJECT_ENDPOINT;
import static io.restassured.RestAssured.given;

public class UncheckedAllProjectsList extends Request {
    public UncheckedAllProjectsList(RequestSpecification specification){
        super(specification);
    }
    public Response get(){
        return given().spec(spec).get(PROJECT_ENDPOINT);
    }
}

