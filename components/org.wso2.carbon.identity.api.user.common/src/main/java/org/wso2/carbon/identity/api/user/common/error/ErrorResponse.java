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

package org.wso2.carbon.identity.api.user.common.error;


import org.apache.commons.logging.Log;
import org.apache.log4j.MDC;
import org.wso2.carbon.identity.api.user.common.Constants;

import java.util.UUID;

import static org.wso2.carbon.identity.api.user.common.Constants.CORRELATION_ID_MDC;

public class ErrorResponse extends ErrorDTO {

    public static class Builder {
        private String code;
        private String message;
        private String description;
        private String ref;

        public Builder() {

        }

        public Builder withCode(String code) {
            this.code = code;
            return this;

        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;

        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withError(Constants.ErrorMessages error) {
            this.code = error.getCode();
            this.message = error.getMessage();
            this.description = error.getDescription();
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse error = new ErrorResponse();
            error.setCode(this.code);
            error.setMessage(this.message);
            error.setDescription(this.description);
            error.setRef(getCorrelation());
            return error;
        }

        public ErrorResponse build(Log log, Exception e, String message) {
            ErrorResponse error = build();
            String errorMessageFormat = "errorCode: %s | message: %s";
            String errorMsg = String.format(errorMessageFormat, error.getCode(), message);
            if (!isCorrelationIDPresent()) {
                errorMsg = String.format("correlationID: %s | " + errorMsg, error.getRef());
            }
            log.error(errorMsg, e);
            return error;
        }

        private String getCorrelation() {

            if (isCorrelationIDPresent()) {
                this.ref = MDC.get(CORRELATION_ID_MDC).toString();
            } else {
                this.ref = UUID.randomUUID().toString();

            }
            return this.ref;
        }

        private boolean isCorrelationIDPresent() {
            return MDC.get(CORRELATION_ID_MDC) != null;
        }
    }
}
