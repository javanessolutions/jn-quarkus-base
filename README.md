# Proyecto: jn-quarkus-base

Este es el arquetipo base de Quarkus 1.7 para el desarrollo de microservicios.

Para mayor Referencia visitar los sitios: 

* [Product Documentation for Red Hat build of Quarkus 1.7](https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.7/)
* [Quarkus Site](https://quarkus.io/)


## Requerimientos

Para usar este arquetipo, es suficiente tener instalado en tu maquina:

* JDK 11
* [Maven 3.6.3](https://maven.apache.org/index.html)
* Openshift Client (oc)

Así mismo, necesitas configurar tu Maven de acuerdo a la guía de Red Hat: [Getting started with Red Hat build of Quarkus](https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.7/html/getting_started_with_red_hat_build_of_quarkus/index) en el capitulo 2.1.

Si quieres hacer un uso avanzado de Quarkus y compilar de manera nativa. Necesitas usar [GraalVM](https://www.graalvm.org) de la versión 11 para tu sistema operativo, así como, sus componentes adicionales: [Native Image](https://www.graalvm.org/reference-manual/native-image/), [LLVM toolchain](https://www.graalvm.org/reference-manual/llvm/Compiling/#llvm-toolchain-for-compiling-cc) y [Wasm](https://www.graalvm.org/reference-manual/wasm/).

Si deseas crear las imagenes nativas desde un contenedor tendrás que tener instalado [Docker](https://www.docker.com) y revisar las guías correspondientes de Quarkus y Red Hat.


## Comandos de quarkus para desarrollar

### Ejecución local de la aplicación (en tu maquina)

Puedes ejecutar la aplicación localmente con el siguiente comando, ademas no es necesario que vuelvas a compilar para tomar los cambios que hagas:
```
./mvnw clean quarkus:dev
```

La forma más facil de probar la aplicación directamente es usando curl:
```
curl -X GET http://localhost:8080/v1/hello-controller/hello
```

### Para desplegar la aplicación en Openshift

Para realizar tus despliegues en OpenShift, es necesario que con la utilería oc hayas hecho lo siguiente previamente:

```
$ oc login https://algun.servidor.openshift.com
$ oc project my-project
```
Con los dos comandos anteriores estarás seguro de que has hecho login y te encuentras en el projecto donde quieras hacer tu despliegue ([Aqui esta la guía de oc](https://docs.openshift.com/container-platform/3.11/cli_reference/get_started_cli.html)).

#### Primer paso: Crear los recursos de tu aplicación

Para los recursos de la aplicación se ha creado la carpeta `src/main/kubernetes`

La configuración de tu archivo `src/main/resources/application.properties` la usamos para el desarrollo local en tu máquina. Para la inyección de configuraciones se ha creado el archivo `src/main/kubernetes/configmap.yml` que contiene la configuración de tu micro servicio en openshift. La configuración lo monta posteriormente en el pod en `deployments/config/application.properties`.

Creación de tu ConfigMap
```
oc create -f src/main/kubernetes/configmap.yml
```

Si quieres actualizar tu configuración en el configmap:
```
oc replace -f src/main/kubernetes/configmap.yml
```
#### Segundo paso: Desplegar tu aplicación

Para hacer el despliegue ejecuta este comando para que puedas ver tu contenedor dentro de OpenShift:
```
./mvnw clean package \
-Dquarkus.container-image.build=true \
-Dquarkus.kubernetes.deploy=true \
-Dquarkus.openshift.expose=false \
-DskipTests \
-Dquarkus.openshift.labels.app.openshift.io/runtime=java
``````
Si deseas que se cree una ruta de manera automática para acceder al servicio entonces deberás cambiar `-Dquarkus.openshift.expose=true`.

### Empaquetar y correr la aplicación

La aplicación puede ser empaquetada usando `./mvnw package`. que crea el archivo `jn-quarkus-base-1.0-SNAPSHOT-runner.jar` en el directorio `/target`.
Contempla que no es un _über-jar_ y que las dependencias se encuentran en el directorio `target/lib`..

La aplicación puede correr directamente con el siguiente comando: 
```
java -jar target/jn-quarkus-base-1.0-SNAPSHOT-runner.jar
```


### Creación de un ejecutable nativo (en tu maquina)

Para crear un paquete nativo en tu maquina (para tu sistema operativo), es necesario tener instalado GraalVM, así como la variable de ambiente debe de apuntar al directorio de base, ejemplo:
```
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.3.0/Contents/Home
```

Se usa el siguiente comando: `./mvnw package -Pnative` para crear el binario.

### Creación de un ejecutable nativo con un contenedor (para linux)

Si no cuentas con GraalVM, pero si con Docker. Se puede correr una contrucción nativa dentro de un contenedor, para lo cual debes de correr el siguiente comando:
```
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

El ejecutable resultante es una aplicación nativa Linux y puedes ejecutar tu aplicación nativa con: `./target/jn-quarkus-base-1.0-SNAPSHOT-runner`

Para mayor referencia: https://quarkus.io/guides/building-native-image.

### Creación de un contenedor nativo para OpenShift

Para crear el contenedor deberás ejecutar el comando siguiente:
```
./mvnw clean package \
-Pnative \
-Dquarkus.native.container-build=true \
-Dquarkus.container-image.build=true \
-Dquarkus.kubernetes.deploy=true \
-Dquarkus.openshift.expose=true \
-DskipTests \
-Dquarkus.openshift.labels.app.openshift.io/runtime=native
```
Si por algun motivo ves *OOMKilled* en tu contenedor donde se hizo la costrucción de tu contenedor. Tendras que aumentar en el yaml el tamaño de la memoria asignada en el *BuildConfig*, con 4Gb deberá de ser suficiente siendo 8 el ideal.

El proceso de compilación con 500 milicores tardará aproximadamente 18 minutos.


# Arquetipo

A continuación se explica la estructura del arquetipo y sus reglas generales de uso.

## Características instaladas

* **cdi**. Contextos e inyección de código
* **resteasy**. Paquete base para REST.
* **resteasy-jsonb**. Paquete base para JSon (hay varias opciones, pero para paquetes chicos esta es la mejor).
* **hibernate-validator**. Validaciones para pojos e interfaces rest.
* **kubernetes**. Manejo de ambientación de contenedores en OpenShift.
* **smallrye-health**. Para configurar las pruebas de contenedores.
* **smallrye-openapi**. Para decorar y documentar los swagger generados.
* **swagger-ui**. Utilería en tiempo de desarrollo para invocar desde el browser los servicios que se estan desarrollando.


## Estructura de paquetes

La estructura de paquetes es como sigue:
```
com.javanes.micro.<archetipe.name> -> Paquete Base.
  |- constants -> Paquete destinado a constantes.
  |- enums -> Paquete destinado a los enums.
  |- exception -> Paquete destinado a las excepciones.
  |- util -> Paquete destinado a las Utilerias.
  |- config -> Paquete destinado a las configuraciones generales.
  |- rest -> Paquete de de la capa rest.
     |- advice -> Paquete de componentes advice o interceptors.
     |- controller -> Paquete de las interfaces de los controladores.
        |- impl -> Paquete de de implementacion de los controladores.
     |- pojo -> Paquete VO (Value Objects, POJOs) que se usan dentro de la capa rest.
  |- service -> Paquete de las interfaces de los servicios destinados a las reglas de negocio.
     |- impl -> Paquete de implementacion de los servicios de negocio
  |- persistence -> Paquete de la capa de persistencia.
     |- pojo -> Paquete destinado a los Entity (POJOs) de la capa de persistencia.
     |- dao -> Paquete de componentes interface DAO (Data Access Object)
        |- impl -> Paquete de implementacion de DAO
```

La estructura final del paquete base es la siguiente:

Si en el pom.xml existe 
```
  <artifactId>lmg-ent-customer-manager</artifactId>
```

Entonces el paquete base será:
```
com.javanes.micro.customer.manager
```

## Manejo de excepciones

Para el arquetipo fue creada una clase general en 'com.javanes.micro.quarkus.base.exception' llamada `AppException` la cual como podrás ver en el en esta clase base se maneja como estandar en todas las interfaces de las 3 capas:

* Controller
* Service
* Persistence

La razón es que esta clase permite tener un control de errores adecuado para la aplicación que se encadena a través de las 3 capas pudiendo ser certero en el manejo de todos los errores conocidos por la aplicación, para ello se ha creado ademas una enumeración en el paquete `com.javanes.micro.quarkus.base.enums` que te permitirá crear un diccionario de errores HTTP, códigos de retorno y mensajes para la aplicación cliente.

Para lo anterior se han creado dos advisors en el paquete `com.javanes.micro.quarkus.base.rest.advice`

* `AppExceptionHandler` para el manejo de errores aplicativos.
* `GeneralExceptionHancler` para cualquier error desconocido de la aplicación.

## Configuración de la aplicación

Para realizar la configuración de la aplicación el paquete `com.javanes.micro.quarkus.base.config` puede ser usado simplemente creando una clase y anotandola con `@ConfigProperties`, para esto ya se ha creado una clase llamada `AppConfiguration` la cual únicamente requiere definir los campos dentro del archivo `application.properties`

Todas las propiedades dentro de este archivo que se encuentren con el prefijo `app-config` serán leidas automáticamente por esta clase, la cual puede ser inyectada en cualquier componente de la aplicación.



## Como crear un nuevo controlador

Los controladores de la capa REST tienen como única intención recibir la llamada de los clientes y delegar inmediatamente a la capa de servicio la llamada.

Por la razón anterior casi no tienen código mas que aquel necesario para delegar la llamada y proveer la respuesta final a través de la clase `Response` de RS para indicar el estatus.

La creación de controladores REST se hace con los siguientes pasos:

1. Crear la interfaz del servicio REST en el paquete `com.javanes.micro.quarkus.base.rest.controller`, esta interfaz solamente tendrá el decorado rest del servicio y puedes extenderla con `openapi`.

```
public interface HelloController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hello")
    public Response sayHello(@NotEmpty @HeaderParam String exchangeId) throws ApplicationException;
}
```

2. Crear la implementación del servicio rest en el paquete `com.javanes.micro.quarkus.base.rest.controller.impl`, hay que notar que en la implementación se identifica el base path del servicio. La ventaja de esto es que la implementación de los controladores la puedes versionar en el path `v1`, `v2`, etc.

```
@Path("/v1/hello-controller")
public class HelloContollerImplV1 implements HelloController {

    public Response sayHello(@NotEmpty @HeaderParam String exchangeId) throws ApplicationException{
        LOG.debug(String.format("EXCHANGE_ID: %s", exchangeId));
        HelloResponse response = new HelloResponse();
        response.setResponse("Hello World V1");
        return Response.ok().entity(response).build();
    }
}
```

## Como crear un nuevo servicio

La creación de servicios que pueden ser inyectados para proveer lógica aplicativa es muy sencilla y consta de los siguientes pasos:

1. Crear la interfaz del servicio en el paquete `com.javanes.micro.quarkus.base.service`, esta interfaz solamente tendrá la definición del servicio y puede ser muy similar a la interfaz controller.
2. Crear la implementación del servicio en el paquete `com.javanes.micro.quarkus.base.service.impl`.
3. Anotar la clase de implementación con `@ApplicationScoped` para que pueda ser inyectada en cualquier componente.

## Como crear un nuevo servicio de persistencia

La creación de servicios que pueden ser inyectados para proveer lógica aplicativa es muy sencilla y consta de los siguientes pasos:

1. Crear la interfaz del servicio en el paquete `com.javanes.micro.quarkus.base.persistence`, esta interfaz solamente tendrá la definición del servicio y puede ser muy similar a la interfaz controller.
2. Crear la implementación del servicio en el paquete `com.javanes.micro.quarkus.base.persistence.impl`.
3. Anotar la clase de implementación con `@ApplicationScoped` para que pueda ser inyectada en cualquier componente.