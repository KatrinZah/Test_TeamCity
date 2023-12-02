package com.example.teamcity.api.requests;

import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.checked.CheckedUser;
import com.example.teamcity.api.requests.checked.ProjectCheckedRequest;
import com.example.teamcity.api.requests.unchecked.ProjectUncheckedeRequest;
import com.example.teamcity.api.requests.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.requests.unchecked.UncheckedUser;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter

public class CheckedRequests {

    private CheckedUser userRequest;
    private ProjectCheckedRequest projectRequest;
    private CheckedBuildConfig buildConfigRequest;

    public CheckedRequests(RequestSpecification spec){

        this.userRequest = new CheckedUser(spec);
        this.buildConfigRequest = new CheckedBuildConfig(spec);
        this.projectRequest = new ProjectCheckedRequest(spec);
    }
}