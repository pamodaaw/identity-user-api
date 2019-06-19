package org.wso2.carbon.identity.rest.api.user.challenge.v10.factories;

import org.wso2.carbon.identity.rest.api.user.challenge.v10.UserIdApiService;
import org.wso2.carbon.identity.rest.api.user.challenge.v10.impl.UserIdApiServiceImpl;

public class UserIdApiServiceFactory {

   private final static UserIdApiService service = new UserIdApiServiceImpl();

   public static UserIdApiService getUserIdApi()
   {
      return service;
   }
}
