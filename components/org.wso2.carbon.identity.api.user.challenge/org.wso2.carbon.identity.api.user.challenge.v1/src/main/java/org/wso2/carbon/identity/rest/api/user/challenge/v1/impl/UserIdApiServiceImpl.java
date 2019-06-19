package org.wso2.carbon.identity.rest.api.user.challenge.v1.impl;

import org.wso2.carbon.identity.recovery.IdentityRecoveryException;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.*;


import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeAnswerDTO;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.rest.api.user.challenge.v1.core.ChallengeService.getChallengesForUser;

public class UserIdApiServiceImpl extends UserIdApiService {
    @Override
    public Response addChallengeAnswersOfAUser(String userId,List<ChallengeAnswerDTO> challengeAnswer){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteChallengeAnswerOfAUser(String challengeSetId,String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteChallengeAnswersOfAUser(String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getAnsweredChallengesOfAUser(String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getChallengesForAUser(String userId,Integer offset,Integer limit){
        // do some magic!
        try {
            return Response.ok().entity(getChallengesForUser(userId, offset, limit)).build();
        } catch (IdentityRecoveryException e) {
            //TODO handle and throw correct error
            throw new WebApplicationException();
        }
    }
    @Override
    public Response updateChallengeAnswerOfAUser(String challengeSetId,String userId,ChallengeAnswerDTO challengeAnswer){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateChallengeAnswersOfAUser(String userId,List<ChallengeAnswerDTO> challengeAnswers){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
