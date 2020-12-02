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

package com.javanes.micro.quarkus.base.rest.controller.impl;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.javanes.micro.quarkus.base.config.AppConfiguration;
import com.javanes.micro.quarkus.base.rest.controller.HelloController;
import com.javanes.micro.quarkus.base.rest.pojo.HelloRequest;
import com.javanes.micro.quarkus.base.service.HelloService;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/v2/hello-controller")
public class HelloContollerImplV2 implements HelloController {

    /**
     * Logger de la clase, en todos los casos se usará el de Jboss.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HelloContollerImplV2.class);

    @Inject
    AppConfiguration appConfiguration;

    @Inject
    HelloService helloService;

    public Response sayHello(@NotEmpty @HeaderParam String exchangeId) {
        LOG.debug(String.format("EXCHANGE_ID: %s", exchangeId));
        //
        // En los metodos del controller no se pone lógica, unicamente sirven
        // como fachadas para los servicios.
        // - Siempre regresan una instancia de la clase "Response".
        // - Escoger el StatusCode de respuesta (200,201,204,400,406,409,etc).
        // Para ello la clase "Response" brinda facilidades.
        // - Cualquier excepción no controlada es manejada por:
        // @See GeneralExceptionHandler
        return Response.ok().entity(helloService.sayHello(appConfiguration.getDefaultName())).build();
    }

    public Response sayHello(@NotEmpty @HeaderParam String exchangeId, @PathParam String name) {
        LOG.debug(String.format("EXCHANGE_ID: %s", exchangeId));
        //
        // En los metodos del controller no se pone lógica, unicamente sirven
        // como fachadas para los servicios.
        // - Siempre regresan una instancia de la clase "Response".
        // - Escoger el StatusCode de respuesta (200,201,204,400,406,409,etc).
        // Para ello la clase "Response" brinda facilidades.
        // - Cualquier excepción no controlada es manejada por:
        // @See GeneralExceptionHandler
        return Response.ok().entity(helloService.sayHello(name)).build();
    }

    public Response saveHello(@NotEmpty @HeaderParam String exchangeId, @NotNull @RequestBody HelloRequest body) {
        LOG.debug(String.format("EXCHANGE_ID: %s", exchangeId));
        //
        // En los metodos del controller no se pone lógica, unicamente sirven
        // como fachadas para los servicios.
        // - Siempre regresan una instancia de la clase "Response".
        // - Escoger el StatusCode de respuesta (200,201,204,400,406,409,etc).
        // Para ello la clase "Response" brinda facilidades.
        // - Cualquier excepción no controlada es manejada por:
        // @See GeneralExceptionHandler
        return Response.ok().entity(helloService.saveHello(body.getName())).build();
    }

}
