package com.example.teamcity.api;

import com.codeborne.selenide.Condition;
import com.example.teamcity.ui.BaseUiTest;
import com.example.teamcity.ui.pages.AgentAuthPage;
import org.testng.annotations.Test;

public class TeamCityAgentTest extends BaseUiTest{//BaseApiTest{

    @Test
    public void setupTeamCityAgentTest() {
        var testData = testDataStorage.addTestData();
        loginAsUser(testData.getUser());

        new AgentAuthPage()
                .open()
                .authTeamCityAgent()
                .getAuthStatus().shouldHave(Condition.text("Authorized"));
    }

//    @Test
//    public void setupTeamCityAgentTest2(){
//        var agents = waitTeamCityAgent();
//       // authorizeAgent("true", "/name:" + agents.get(0).getName());
//    }

//    private List<Agent> waitTeamCityAgent() {
//        var checkedRequest = checkedWithSuperUser.getRequest(Endpoint.AGENT);
//        var atomicAgentResponse = new AtomicReference<>(new AgentList());
//        Awaitility.await()
//                .atMost(Duration.ofSeconds(30))
//                .pollInterval(Duration.ofSeconds(5))
//                .until(() -> {
//                    atomicAgentResponse.set((AgentList) checkedRequest.get("?locator=enabled:true,authorized:false"));
//                    return !atomicAgentResponse.get().getAgent().isEmpty();
//
//                });
//        return atomicAgentResponse.get().getAgent();
//    }

}
