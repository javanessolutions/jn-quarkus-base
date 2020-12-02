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

package com.javanes.micro.quarkus.base.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.javanes.micro.quarkus.base.config.AppConfiguration;
import com.javanes.micro.quarkus.base.enums.AppExceptionEnum;
import com.javanes.micro.quarkus.base.exception.AppException;
import com.javanes.micro.quarkus.base.rest.pojo.HelloResponse;
import com.javanes.micro.quarkus.base.service.HelloService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class HelloServiceImpl implements HelloService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Inject
    AppConfiguration appConfiguration;

    public HelloResponse sayHello(final String name) {
        HelloResponse response = new HelloResponse();
        LOG.debug(String.format("CALL[sayHello]: %s", name));
        response.setResponse(
                String.format("%s %s %s", appConfiguration.getGreeting(), name, appConfiguration.getSufix()));
        return response;
    }


    @Override
    public HelloResponse saveHello(String name) {
        throw new AppException(AppExceptionEnum.STATUS_NO_IMPLEMENTADO, "Funcionalidad pendiente.");
    }
}
