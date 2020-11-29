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

import com.javanes.micro.quarkus.base.enums.AppExceptionEnum;

public class AppException extends RuntimeException {

    private AppExceptionEnum code;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AppException() {
        super();
        code = AppExceptionEnum.STATUS_GENERIC_ERROR;
    }

    public AppException(String message) {
        super(message);
        code = AppExceptionEnum.STATUS_GENERIC_ERROR;
    }

    public AppException(Throwable cause) {
        super(cause);
        code = AppExceptionEnum.STATUS_GENERIC_ERROR;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        code = AppExceptionEnum.STATUS_GENERIC_ERROR;
    }

    public AppException(AppExceptionEnum code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(AppExceptionEnum code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public AppException(AppExceptionEnum code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public AppExceptionEnum getCode() {
        return code;
    }

}
