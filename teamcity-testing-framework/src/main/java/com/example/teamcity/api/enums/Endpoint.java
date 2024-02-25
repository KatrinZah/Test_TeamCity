package com.example.teamcity.api.enums;

import com.example.teamcity.api.models.AgentList;
import com.example.teamcity.api.models.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoint {
    //BUILD_TYPES("/app/rest/buildTypes", BuildType.class),
    //USERS("/app/rest/users", User.class),
    //PROJECTS("/app/rest/projects", ProjectResponse.class),
    AGENT("/app/rest/agents", AgentList.class);

    private final String url;
    private final Class<? extends BaseModel> modelClass;
}
