package org.wso2.carbon.identity.rest.api.user.challenge;

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

public abstract class MeApiService {
    public abstract Response addChallengeAnswersForLoggedInUser(String userId,List<ChallengeAnswerDTO> challengeAnswer);
    public abstract Response deleteChallengeAnswerOfLoggedInUser(String challengeSetId,String userId);
    public abstract Response deleteChallengeAnswersOfLoggedInUser(String userId);
    public abstract Response getAnsweredChallengesOfLoggedInUser(String userId);
    public abstract Response getChallengesForLoggedInUser(Integer offset,Integer limit);
    public abstract Response updateChallengeAnswerOfLoggedInUser(String challengeSetId,String userId,ChallengeAnswerDTO challengeAnswer);
}

