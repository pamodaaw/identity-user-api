package org.wso2.carbon.identity.rest.api.user.challenge.v1;

import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeAnswerDTO;

import java.util.List;

import javax.ws.rs.core.Response;

public abstract class UserIdApiService {
    public abstract Response addChallengeAnswersOfAUser(String userId,List<ChallengeAnswerDTO> challengeAnswer);
    public abstract Response deleteChallengeAnswerOfAUser(String challengeSetId,String userId);
    public abstract Response deleteChallengeAnswersOfAUser(String userId);
    public abstract Response getAnsweredChallengesOfAUser(String userId);
    public abstract Response getChallengesForAUser(String userId,Integer offset,Integer limit);
    public abstract Response updateChallengeAnswerOfAUser(String challengeSetId,String userId,ChallengeAnswerDTO challengeAnswer);
    public abstract Response updateChallengeAnswersOfAUser(String userId,List<ChallengeAnswerDTO> challengeAnswers);
}

