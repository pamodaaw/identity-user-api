package org.wso2.carbon.identity.rest.api.endpoint.association;

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

public abstract class UsersApiService {
    public abstract Response usersMeAssociationsAssociateUserIdDelete(String associateUserId);
    public abstract Response usersMeAssociationsDelete();
    public abstract Response usersMeAssociationsGet();
    public abstract Response usersMeAssociationsPost(AssociationUserRequestDTO association);
    public abstract Response usersMeAssociationsSwitchPut(AssociationSwitchRequestDTO switchUserReqeust);
    public abstract Response usersUserIdAssociationsAssociateUserIdDelete(String userId,String associateUserId);
    public abstract Response usersUserIdAssociationsDelete(String userId);
    public abstract Response usersUserIdAssociationsGet(String userId);
    public abstract Response usersUserIdAssociationsPost(AssociationRequestDTO association,String userId);
}

