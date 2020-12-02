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

package com.javanes.micro.quarkus.base.enums;

import javax.ws.rs.core.Response;

public enum AppExceptionEnum {
    STATUS_DESCONOCIDO(Response.Status.INTERNAL_SERVER_ERROR, -1),
    STATUS_NO_IMPLEMENTADO(Response.Status.NOT_IMPLEMENTED,-1),
    STATUS_ERROR_GENERICO(Response.Status.BAD_REQUEST, 1);

    private final Response.Status httpCode;
    private final int appCode;

    AppExceptionEnum(Response.Status httpCode, int appCode) {
        this.httpCode = httpCode;
        this.appCode = appCode;
    }

    public Response.Status getHttpCode() {
        return httpCode;
    }

    public int getAppCode() {
        return appCode;
    }
}
