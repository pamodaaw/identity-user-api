package org.wso2.carbon.identity.rest.api.endpoint.association.factories;

import org.wso2.carbon.identity.rest.api.endpoint.association.UsersApiService;
import org.wso2.carbon.identity.rest.api.endpoint.association.impl.UsersApiServiceImpl;

public class UsersApiServiceFactory {

   private final static UsersApiService service = new UsersApiServiceImpl();

   public static UsersApiService getUsersApi()
   {
      return service;
   }
}
