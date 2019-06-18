package org.wso2.carbon.identity.rest.api.user.association.factories;

import org.wso2.carbon.identity.rest.api.user.association.UsersApiService;
import org.wso2.carbon.identity.rest.api.user.association.impl.UsersApiServiceImpl;

public class UsersApiServiceFactory {

   private final static UsersApiService service = new UsersApiServiceImpl();

   public static UsersApiService getUsersApi()
   {
      return service;
   }
}
