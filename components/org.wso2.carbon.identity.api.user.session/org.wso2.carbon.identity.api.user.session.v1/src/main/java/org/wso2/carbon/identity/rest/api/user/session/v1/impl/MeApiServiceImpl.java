package org.wso2.carbon.identity.rest.api.user.session.v1.impl;

import org.wso2.carbon.identity.rest.api.user.session.v1.*;
import org.wso2.carbon.identity.rest.api.user.session.v1.dto.*;


import org.wso2.carbon.identity.rest.api.user.session.v1.dto.SessionsDTO;
import org.wso2.carbon.identity.rest.api.user.session.v1.dto.ErrorDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public class MeApiServiceImpl extends MeApiService {
    @Override
    public Response getSessionsOfLoggedInUser(){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response terminateSessionByLoggedInUser(String sessionId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response terminateSessionsByLoggedInUser(){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
