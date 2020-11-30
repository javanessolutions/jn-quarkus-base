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

package com.javanes.micro.quarkus.base.rest.advice;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.javanes.micro.quarkus.base.enums.AppExceptionEnum;
import com.javanes.micro.quarkus.base.rest.pojo.AppExceptionResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Throwable> {

    final static Logger LOG = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @Context
    private HttpHeaders httpHeaders;

    @Override
    public Response toResponse(Throwable exception) {
        List<String> values = httpHeaders.getRequestHeader("exchangeId");
        LOG.warn("UNHANDLED_CASE{}",values, exception);
        AppExceptionResponse response = new AppExceptionResponse();
        if(values != null && !values.isEmpty()){
            response.setExchangeId(values.get(0));
        }else{
            response.setExchangeId("");
        }
        response.setCode(AppExceptionEnum.STATUS_UNKNOWN.getCode());
        response.setMessage(AppExceptionEnum.STATUS_UNKNOWN.getMessage());
        response.setExceptionMessage(exception.getMessage());
        return Response.status(AppExceptionEnum.STATUS_UNKNOWN.getHttpCode()).entity(response).build();
    }

}
