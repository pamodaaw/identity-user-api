package org.wso2.carbon.identity.rest.api.user.association.v10.factories;

import org.wso2.carbon.identity.rest.api.user.association.v10.UsersApiService;
import org.wso2.carbon.identity.rest.api.user.association.v10.impl.UsersApiServiceImpl;

public class UsersApiServiceFactory {

   private final static UsersApiService service = new UsersApiServiceImpl();

   public static UsersApiService getUsersApi()
   {
      return service;
   }
}
