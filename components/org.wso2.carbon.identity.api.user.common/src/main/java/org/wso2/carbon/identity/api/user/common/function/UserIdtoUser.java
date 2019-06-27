/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.api.user.common.function;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.api.user.common.error.APIError;
import org.wso2.carbon.identity.api.user.common.error.ErrorResponse;
import org.wso2.carbon.identity.application.common.model.User;
import org.wso2.carbon.user.core.UserStoreConfigConstants;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.UUID;
import java.util.function.Function;

import static org.wso2.carbon.identity.api.user.common.Constants.ErrorMessages.ERROR_CODE_INVALID_USERNAME;

public class UserIdtoUser implements Function<String[],User> {

    private static final Log log = LogFactory.getLog(UserIdtoUser.class);

    @Override
    public User apply(String... args) {
        return extractUser(args[0], args[1]);
    }

    private User extractUser(String userId, String tenantDomain) {

        try {
            String decodedUsername = new String(Base64.getDecoder().decode(userId));

            if (StringUtils.isBlank(userId)) {
                throw new WebApplicationException("UserID is empty.");
            }
            String[] strComponent = decodedUsername.split("/");

            String username;
            String realm = UserStoreConfigConstants.PRIMARY;

            if (strComponent.length == 1) {
                username = strComponent[0];
            } else if (strComponent.length == 2) {
                realm = strComponent[0];
                username = strComponent[1];
            } else {
                throw new WebApplicationException("Provided UserID is " +
                        "not in the correct format.");
            }

            User user = new User();
            user.setUserName(username);
            user.setUserStoreDomain(realm);
            user.setTenantDomain(tenantDomain);

            return user;
        } catch (Exception e){
            throw new APIError(Response.Status.BAD_REQUEST, new ErrorResponse.Builder().withError
                    (ERROR_CODE_INVALID_USERNAME).build(log, e, "Invalid userId: " +
                    userId));
        }
    }
}
