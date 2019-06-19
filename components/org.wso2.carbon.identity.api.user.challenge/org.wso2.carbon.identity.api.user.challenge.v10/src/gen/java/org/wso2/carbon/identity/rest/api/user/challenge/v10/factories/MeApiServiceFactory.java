package org.wso2.carbon.identity.rest.api.user.challenge.v10.factories;

import org.wso2.carbon.identity.rest.api.user.challenge.v10.MeApiService;
import org.wso2.carbon.identity.rest.api.user.challenge.v10.impl.MeApiServiceImpl;

public class MeApiServiceFactory {

   private final static MeApiService service = new MeApiServiceImpl();

   public static MeApiService getMeApi()
   {
      return service;
   }
}
