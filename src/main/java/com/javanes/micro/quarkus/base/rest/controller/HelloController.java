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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.javanes.micro.quarkus.base.rest.pojo.HelloRequest;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

public interface HelloController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response sayHello(@NotEmpty @HeaderParam String exchangeId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello/{name}")
    public Response sayHello(@NotEmpty @HeaderParam String exchangeId, @PathParam String name);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response saveHello(@NotEmpty String exchangeId, @RequestBody HelloRequest body);
}
