package com.example.teamcity.api.requests;

import com.example.teamcity.api.requests.unchecked.ProjectUncheckedeRequest;
import com.example.teamcity.api.requests.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.requests.unchecked.UncheckedUser;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter

public class UncheckedRequests {

    private UncheckedUser userRequest;
    private ProjectUncheckedeRequest projectRequest;
    private UncheckedBuildConfig buildConfigRequest;

    public UncheckedRequests(RequestSpecification spec){

        this.userRequest = new UncheckedUser(spec);
        this.buildConfigRequest = new UncheckedBuildConfig(spec);
        this.projectRequest = new ProjectUncheckedeRequest(spec);
    }
}
