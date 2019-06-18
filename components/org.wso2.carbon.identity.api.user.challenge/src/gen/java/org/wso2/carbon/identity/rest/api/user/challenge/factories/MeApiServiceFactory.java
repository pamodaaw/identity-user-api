package org.wso2.carbon.identity.rest.api.user.challenge.factories;

import org.wso2.carbon.identity.rest.api.user.challenge.MeApiService;
import org.wso2.carbon.identity.rest.api.user.challenge.impl.MeApiServiceImpl;

public class MeApiServiceFactory {

   private final static MeApiService service = new MeApiServiceImpl();

   public static MeApiService getMeApi()
   {
      return service;
   }
}
