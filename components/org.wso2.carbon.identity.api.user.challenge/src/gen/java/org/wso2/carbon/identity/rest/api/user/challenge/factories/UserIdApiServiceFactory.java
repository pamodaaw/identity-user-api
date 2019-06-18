package org.wso2.carbon.identity.rest.api.user.challenge.factories;

import org.wso2.carbon.identity.rest.api.user.challenge.UserIdApiService;
import org.wso2.carbon.identity.rest.api.user.challenge.impl.UserIdApiServiceImpl;

public class UserIdApiServiceFactory {

   private final static UserIdApiService service = new UserIdApiServiceImpl();

   public static UserIdApiService getUserIdApi()
   {
      return service;
   }
}
