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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javanes.micro.quarkus.base.exception.AppException;
import com.javanes.micro.quarkus.base.rest.pojo.AppExceptionResponse;

@Provider
public class AppExceptionHandler implements ExceptionMapper<AppException> {

    final static Logger LOG = LoggerFactory.getLogger(AppExceptionHandler.class);

    @Context
    private HttpHeaders httpHeaders;
    
    @Override
    public Response toResponse(AppException exception) {
        List<String> values = httpHeaders.getRequestHeader("exchangeId");
        if(LOG.isInfoEnabled()){
            LOG.info("ERROR_APLICATIVO{}: {}",values, exception.getMessage());
        }

        AppExceptionResponse response = new AppExceptionResponse();
        if(values != null && !values.isEmpty()){
            response.setExchangeId(values.get(0));
        }

        response.setCode(exception.getAppCode());
        response.setMessage(exception.getMessage());

        if(LOG.isDebugEnabled()){
            LOG.debug("DETALLE_ERROR_APLICATIVO{}", values, exception);
            response.setExceptionDetail(getStackTrace(exception));
        }

        return Response.status(exception.getHttpStatus()).entity(response).build();
    }

    private String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

}
