/*
 *  Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.identity.rest.api.user.challenge.v1.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.ApiResponseMessage;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.UserIdApiService;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.core.UserChallengeService;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeAnswerDTO;

import javax.ws.rs.core.Response;
import java.util.List;

public class UserIdApiServiceImpl extends UserIdApiService {

    @Autowired
    private UserChallengeService challengeService;


    @Override
    public Response addChallengeAnswersOfAUser(String userId,List<ChallengeAnswerDTO> challengeAnswer){
        // do some magic!
        return Response.ok().entity(challengeService.setChallengeAnswersOfUser(userId, challengeAnswer)).build();
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
        return Response.ok().entity(challengeService.getChallengeAnswersOfUser(userId)).build();
    }
    @Override
    public Response getChallengesForAUser(String userId,Integer offset,Integer limit){
        // do some magic!
        return Response.ok().entity(challengeService.getChallengesForUser(userId, offset, limit)).build();
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
