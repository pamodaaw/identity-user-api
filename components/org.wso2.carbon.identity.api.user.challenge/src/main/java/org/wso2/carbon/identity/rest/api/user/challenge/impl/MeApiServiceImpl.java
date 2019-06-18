package org.wso2.carbon.identity.rest.api.user.challenge.impl;

import org.wso2.carbon.identity.rest.api.user.challenge.*;
import org.wso2.carbon.identity.rest.api.user.challenge.dto.*;


import org.wso2.carbon.identity.rest.api.user.challenge.dto.ChallengeAnswerDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.dto.ErrorDTO;
import java.util.List;
import org.wso2.carbon.identity.rest.api.user.challenge.dto.UserChallengeAnswerDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.dto.ChallengeSetDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public class MeApiServiceImpl extends MeApiService {
    @Override
    public Response addChallengeAnswersForLoggedInUser(String userId,List<ChallengeAnswerDTO> challengeAnswer){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteChallengeAnswerOfLoggedInUser(String challengeSetId,String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response deleteChallengeAnswersOfLoggedInUser(String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getAnsweredChallengesOfLoggedInUser(String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getChallengesForLoggedInUser(Integer offset,Integer limit){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateChallengeAnswerOfLoggedInUser(String challengeSetId,String userId,ChallengeAnswerDTO challengeAnswer){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
