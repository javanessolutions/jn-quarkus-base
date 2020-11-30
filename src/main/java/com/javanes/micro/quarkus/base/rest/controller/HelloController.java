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

package com.javanes.micro.quarkus.base.rest.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javanes.micro.quarkus.base.rest.pojo.AppExceptionResponse;
import com.javanes.micro.quarkus.base.rest.pojo.HelloRequest;
import com.javanes.micro.quarkus.base.rest.pojo.HelloResponse;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


public interface HelloController {

    @Operation(
        summary = "Saludo",
        description = "Saludo general."
    )
    @Parameter(
        name = "exchangeId",
        description = "Identificador de la llamada al servidor.",
        required = true,
        example = "asdf-asdf-asdf-asdf",
        schema = @Schema(type = SchemaType.STRING)
    )
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Ejecución exitosa.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                                implementation = HelloResponse.class,
                                description = "Datos de la respuesta.")
                )
            ),
            @APIResponse(
                responseCode = "400",
                description = "Error del servicio.",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                    implementation = AppExceptionResponse.class,
                                    description = "Error controlado por la aplicación."
                                )
                    ),
                    @Content(
                        mediaType = "text/plain",
                        schema = @Schema(
                                    type = SchemaType.STRING,
                                    description = "Cuando existen errores de validación de entrada.",
                                    example = "[PARAMETER][sayHello.exchangeId][no debe estar vacío][]"
                                )
                    )
                },
                headers = @Header(
                    name = "validation-exeption", 
                    schema = @Schema(type = SchemaType.BOOLEAN),
                    description = "Cuando existe un error de validación de parametros de entrada validados con hibernate-validator."
                )
            ),
            @APIResponse(
                responseCode = "500",
                description = "Error del servidor no controlado.",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                    implementation = AppExceptionResponse.class,
                                    description = "Error no controlado por la aplicación."
                                )
                    ),
                    @Content(
                        mediaType = "text/plain",
                        schema = @Schema(
                                    type = SchemaType.STRING,
                                    description = "Error como fue interpretado por el servidor."
                                )
                    )
                }
            )
        }
    )
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response sayHello(@NotEmpty @HeaderParam String exchangeId);

    @Operation(
        summary = "Saludo",
        description = "Saludo personalizado."
    )
    @Parameters(
        value = {
            @Parameter(
                name = "exchangeId",
                description = "Identificador de la llamada al servidor.",
                required = true,
                example = "asdf-asdf-asdf-asdf",
                schema = @Schema(type = SchemaType.STRING)
            ),
            @Parameter(
                name = "name",
                description = "Persona a saludar.",
                required = true,
                example = "Alejandro",
                schema = @Schema(type = SchemaType.STRING)
            )
        }
    )
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Ejecución exitosa.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                                implementation = HelloResponse.class,
                                description = "Datos de la respuesta.")
                )
            ),
            @APIResponse(
                responseCode = "400",
                description = "Error del servicio.",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                    implementation = AppExceptionResponse.class,
                                    description = "Error controlado por la aplicación."
                                )
                    ),
                    @Content(
                        mediaType = "text/plain",
                        schema = @Schema(
                                    type = SchemaType.STRING,
                                    description = "Cuando existen errores de validación de entrada.",
                                    example = "[PARAMETER][sayHello.exchangeId][no debe estar vacío][]"
                                )
                    )
                },
                headers = @Header(
                    name = "validation-exeption", 
                    schema = @Schema(type = SchemaType.BOOLEAN),
                    description = "Cuando existe un error de validación de parametros de entrada validados con hibernate-validator."
                )
            ),
            @APIResponse(
                responseCode = "500",
                description = "Error del servidor no controlado.",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                    implementation = AppExceptionResponse.class,
                                    description = "Error no controlado por la aplicación."
                                )
                    ),
                    @Content(
                        mediaType = "text/plain",
                        schema = @Schema(
                                    type = SchemaType.STRING,
                                    description = "Error como fue interpretado por el servidor."
                                )
                    )
                }
            )
        }
    )
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello/{name}")
    public Response sayHello(@NotEmpty @HeaderParam String exchangeId, @PathParam String name);

    @Operation(
        summary = "Guarda saludo",
        description = "Pinta el saludo de la persona en la bitacora."
    )
    @Parameters(
        value = {
            @Parameter(
                name = "exchangeId",
                description = "Identificador de la llamada al servidor.",
                required = true,
                example = "asdf-asdf-asdf-asdf",
                schema = @Schema(type = SchemaType.STRING)
            ),
            @Parameter(
                name = "body",
                required = true,
                schema = @Schema(implementation = HelloRequest.class)
            )
        }
    )
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Ejecución exitosa.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                                implementation = HelloResponse.class,
                                description = "Datos de la respuesta.")
                )
            ),
            @APIResponse(
                responseCode = "400",
                description = "Error del servicio.",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                    implementation = AppExceptionResponse.class,
                                    description = "Error controlado por la aplicación."
                                )
                    ),
                    @Content(
                        mediaType = "text/plain",
                        schema = @Schema(
                                    type = SchemaType.STRING,
                                    description = "Cuando existen errores de validación de entrada.",
                                    example = "[PARAMETER][sayHello.exchangeId][no debe estar vacío][]"
                                )
                    )
                },
                headers = @Header(
                    name = "validation-exeption", 
                    schema = @Schema(type = SchemaType.BOOLEAN),
                    description = "Cuando existe un error de validación de parametros de entrada validados con hibernate-validator."
                )
            ),
            @APIResponse(
                responseCode = "500",
                description = "Error del servidor no controlado.",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                    implementation = AppExceptionResponse.class,
                                    description = "Error no controlado por la aplicación."
                                )
                    ),
                    @Content(
                        mediaType = "text/plain",
                        schema = @Schema(
                                    type = SchemaType.STRING,
                                    description = "Error como fue interpretado por el servidor."
                                )
                    )
                }
            )
        }
    )
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response saveHello(@NotEmpty @HeaderParam String exchangeId, @NotNull @RequestBody HelloRequest body);
}
