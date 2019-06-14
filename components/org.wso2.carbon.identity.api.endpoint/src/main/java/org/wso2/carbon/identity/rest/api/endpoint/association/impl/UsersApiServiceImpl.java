package org.wso2.carbon.identity.rest.api.endpoint.association.impl;

import org.wso2.carbon.identity.rest.api.endpoint.association.*;
import org.wso2.carbon.identity.rest.api.endpoint.association.dto.*;


import org.wso2.carbon.identity.rest.api.endpoint.association.dto.ErrorDTO;
import org.wso2.carbon.identity.rest.api.endpoint.association.dto.UserDTO;
import org.wso2.carbon.identity.rest.api.endpoint.association.dto.AssociationUserRequestDTO;
import org.wso2.carbon.identity.rest.api.endpoint.association.dto.AssociationSwitchRequestDTO;
import org.wso2.carbon.identity.rest.api.endpoint.association.dto.AssociationRequestDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public class UsersApiServiceImpl extends UsersApiService {
    @Override
    public Response usersMeAssociationsAssociateUserIdDelete(String associateUserId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersMeAssociationsDelete(){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersMeAssociationsGet(){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersMeAssociationsPost(AssociationUserRequestDTO association){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersMeAssociationsSwitchPut(AssociationSwitchRequestDTO switchUserReqeust){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersUserIdAssociationsAssociateUserIdDelete(String userId,String associateUserId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersUserIdAssociationsDelete(String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersUserIdAssociationsGet(String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response usersUserIdAssociationsPost(AssociationRequestDTO association,String userId){
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
