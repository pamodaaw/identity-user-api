package org.wso2.carbon.identity.rest.api.user.challenge.v10.impl;

import org.wso2.carbon.identity.rest.api.user.challenge.v10.*;
import org.wso2.carbon.identity.rest.api.user.challenge.v10.dto.*;


import org.wso2.carbon.identity.rest.api.user.challenge.v10.dto.ChallengeAnswerDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v10.dto.ErrorDTO;
import java.util.List;
import org.wso2.carbon.identity.rest.api.user.challenge.v10.dto.UserChallengeAnswerDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v10.dto.ChallengeSetDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

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
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
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
