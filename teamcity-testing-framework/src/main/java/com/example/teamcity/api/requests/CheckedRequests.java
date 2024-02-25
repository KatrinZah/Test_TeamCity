package com.example.teamcity.api.requests;

import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.checked.CheckedUser;
import com.example.teamcity.api.requests.checked.ProjectCheckedRequest;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class CheckedRequests {

    private CheckedUser userRequest;
    private ProjectCheckedRequest projectRequest;
    private CheckedBuildConfig buildConfigRequest;

    //private final EnumMap<Endpoint, CheckedBase> checkedRequests = new EnumMap<>(Endpoint.class);


    public CheckedRequests(RequestSpecification spec){

        this.userRequest = new CheckedUser(spec);
        this.buildConfigRequest = new CheckedBuildConfig(spec);
        this.projectRequest = new ProjectCheckedRequest(spec);
    }

//    public CheckedRequests(RequestSpecification spec, RequestSpecification spec2) {
//        for (var endpoint : Endpoint.values()) {
//            checkedRequests.put(endpoint, new CheckedBase(spec, endpoint));
//        }
//    }

   // public CheckedBase getRequest(Endpoint endpoint) {
//        return checkedRequests.get(endpoint);
//    }
}