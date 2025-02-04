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

package org.wso2.carbon.identity.api.user.common;

import static org.wso2.carbon.identity.api.user.common.Constants.ErrorPrefix.USER_MANAGEMENT_PREFIX;

public class Constants {
    public static final String OPERATION_ADD = "ADD";
    public static final String TENANT_NAME_FROM_CONTEXT = "TenantNameFromContext";
    public static final String ERROR_CODE_DELIMITER = "-";
    public static final String CORRELATION_ID_MDC = "Correlation-ID";

    public enum ErrorPrefix {

        USER_MANAGEMENT_PREFIX("UMG-");

        private final String prefix;

        ErrorPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    public enum ErrorMessage {

        ERROR_CODE_INVALID_USERNAME(USER_MANAGEMENT_PREFIX.getPrefix() + "10001", "Invalid UserID provided", "The " +
                "provided userId is invalid.");

        private final String code;
        private final String message;
        private final String description;

        ErrorMessage(String code, String message, String description) {
            this.code = code;
            this.message = message;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return code + " | " + message;
        }

    }

}
