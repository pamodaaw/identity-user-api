package org.wso2.carbon.identity.rest.api.user.association.v10.impl;

import org.wso2.carbon.identity.rest.api.user.association.v10.*;
import org.wso2.carbon.identity.rest.api.user.association.v10.dto.*;


import org.wso2.carbon.identity.rest.api.user.association.v10.dto.ErrorDTO;
import org.wso2.carbon.identity.rest.api.user.association.v10.dto.UserDTO;
import org.wso2.carbon.identity.rest.api.user.association.v10.dto.AssociationUserRequestDTO;
import org.wso2.carbon.identity.rest.api.user.association.v10.dto.AssociationSwitchRequestDTO;
import org.wso2.carbon.identity.rest.api.user.association.v10.dto.AssociationRequestDTO;

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
