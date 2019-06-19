package org.wso2.carbon.identity.rest.api.user.challenge.v1;

import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeAnswerDTO;

import java.util.List;

import javax.ws.rs.core.Response;

public abstract class MeApiService {
    public abstract Response addChallengeAnswersForLoggedInUser(String userId,List<ChallengeAnswerDTO> challengeAnswer);
    public abstract Response deleteChallengeAnswerOfLoggedInUser(String challengeSetId,String userId);
    public abstract Response deleteChallengeAnswersOfLoggedInUser(String userId);
    public abstract Response getAnsweredChallengesOfLoggedInUser(String userId);
    public abstract Response getChallengesForLoggedInUser(Integer offset,Integer limit);
    public abstract Response updateChallengeAnswerOfLoggedInUser(String challengeSetId,String userId,ChallengeAnswerDTO challengeAnswer);
}

