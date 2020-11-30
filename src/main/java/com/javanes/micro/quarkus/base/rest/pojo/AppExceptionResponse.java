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

package com.javanes.micro.quarkus.base.rest.pojo;

import java.util.Date;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "AppExceptionResponse", description = "POJO de errores de la aplicación.")
public class AppExceptionResponse {

    @Schema(description = "Identificador de la llamada al servidor.",  example = "asdf-asdf-asdf-asdf" )
    private String exchangeId;
    @Schema(description = "Código de error.", example = "1" )
    private int code;
    @Schema(description = "Mensaje de error.", example = "Error generico de la aplicación." )
    private String message;
    @Schema(description = "Error técnico.", example = "Error en la base de datos." )
    private String exceptionMessage;
    @Schema(description = "Momento en el que ocurrio el error.", example = "2020-11-29T12:27:27.286Z[UTC]")
    private Date timestamp;

    public AppExceptionResponse(){
        timestamp = new Date();
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
