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
        if(LOG.isWarnEnabled()){
            LOG.warn("CASO_NO_MANEJADO{}",values, exception);
        }            
        AppExceptionResponse response = new AppExceptionResponse();

        if(values != null && !values.isEmpty()){
            response.setExchangeId(values.get(0));
        }

        if(LOG.isDebugEnabled()){
            response.setExceptionDetail(getStackTrace(exception));
        }

        response.setCode(AppExceptionEnum.STATUS_DESCONOCIDO.getAppCode());
        response.setMessage(exception.getMessage());
        return Response.status(AppExceptionEnum.STATUS_DESCONOCIDO.getHttpCode()).entity(response).build();
    }

    private String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

}
