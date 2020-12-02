/*
 * Copyright (c) 2017 Javanes Solutions S.A. de C.V. All rights reserved.
 *
 * Licensed under the GNU General Public License, Version 3 (the 
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 *   https://www.gnu.org/licenses/gpl-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.javanes.micro.quarkus.base.exception;

import javax.ws.rs.core.Response;

import com.javanes.micro.quarkus.base.enums.AppExceptionEnum;

public class AppException extends RuntimeException {

    private Response.Status httpStatus;
    private int appCode;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AppException() {
        super();
        httpStatus = AppExceptionEnum.STATUS_ERROR_GENERICO.getHttpCode();
        appCode = AppExceptionEnum.STATUS_ERROR_GENERICO.getAppCode();
    }

    public AppException(String message) {
        super(message);
        httpStatus = AppExceptionEnum.STATUS_ERROR_GENERICO.getHttpCode();
        appCode = AppExceptionEnum.STATUS_ERROR_GENERICO.getAppCode();
    }

    public AppException(Throwable cause) {
        super(cause);
        httpStatus = AppExceptionEnum.STATUS_ERROR_GENERICO.getHttpCode();
        appCode = AppExceptionEnum.STATUS_ERROR_GENERICO.getAppCode();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        httpStatus = AppExceptionEnum.STATUS_ERROR_GENERICO.getHttpCode();
        appCode = AppExceptionEnum.STATUS_ERROR_GENERICO.getAppCode();
    }

    public AppException(AppExceptionEnum code, String message) {
        super(message);
        httpStatus = code.getHttpCode();
        appCode = code.getAppCode();
    }

    public AppException(AppExceptionEnum code, Throwable cause) {
        super(cause);
        httpStatus = code.getHttpCode();
        appCode = code.getAppCode();
    }

    public AppException(AppExceptionEnum code, String message, Throwable cause) {
        super(message, cause);
        httpStatus = code.getHttpCode();
        appCode = code.getAppCode();
    }

    public Response.Status getHttpStatus() {
        return httpStatus;
    }

    public int getAppCode() {
        return appCode;
    }

}
