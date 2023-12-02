package com.example.teamcity.api.ganerators;

import com.example.teamcity.api.models.BuildType;
import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.unchecked.ProjectUncheckedeRequest;
import com.example.teamcity.api.requests.unchecked.UncheckedUser;
import com.example.teamcity.api.specifications.Specifications;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestData {

    private User user;
    private NewProjectDescription project;
    private BuildType buildType;

    public void delete(){
        var spec = Specifications.getSpecifications().authSpec(user);
        new ProjectUncheckedeRequest(spec).delete(project.getId());
        //new ProjectUncheckedeRequest(user).delete(project.getId());
        new UncheckedUser(spec).delete(user.getUsername());
    }
}
