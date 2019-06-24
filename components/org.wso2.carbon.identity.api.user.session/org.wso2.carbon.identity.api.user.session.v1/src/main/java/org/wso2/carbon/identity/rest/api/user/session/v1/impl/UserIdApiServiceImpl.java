package org.wso2.carbon.identity.rest.api.user.session.v1.impl;

import org.wso2.carbon.identity.rest.api.user.session.v1.ApiResponseMessage;
import org.wso2.carbon.identity.rest.api.user.session.v1.UserIdApiService;

import javax.ws.rs.core.Response;

public class UserIdApiServiceImpl extends UserIdApiService {

    @Override
    public Response getSessionsByUserId(String userId) {


            UserSession[] userSessions = ServiceManager().getSessionsByUserId(userId);
//        try {
//            UserSession[] userSessions = getSessionManager().getSessionsByUserId(userId);
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
//        } catch (SessionManagementException e) {
//            return Response.serverError().build();
//        }
    }

    @Override
    public Response terminateSessionBySessionId(String userId, String sessionId) {

//        try {
//            Boolean isSuccessfullyTerminated = getSessionManager().terminateSessionBySessionId(sessionId);
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
//        } catch (SessionManagementException e) {
//            return Response.serverError().build();
//        }
    }

    @Override
    public Response terminateSessionsByUserId(String userId) {

//        try {
//            Boolean isSuccessfullyTerminated = getSessionManager().terminateSessionsByUserId(userId);
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
//        } catch (SessionManagementException e) {
//            return Response.serverError().build();
//        }
    }
}
