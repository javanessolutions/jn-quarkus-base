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

Finalmente para hacer el despliegue basta con el siguiente comando para que puedas ver tu contenedor dentro de OpenShift:
```
./mvnw clean package -Dquarkus.kubernetes.deploy=true
```


### Empaquetar y correr la aplicación

La aplicación puede ser empaquetada usando `./mvnw package`. que crea el archivo `jn-quarkus-base-1.0-SNAPSHOT-runner.jar` en el directorio `/target`.
Contempla que no es un _über-jar_ y que las dependencias se encuentran en el directorio `target/lib`..

La aplicación puede correr directamente con el siguiente comando: 
```
java -jar target/jn-quarkus-base-1.0-SNAPSHOT-runner.jar
```


### Creación de un ejecutable nativo

Para crear un paquete nativo, es necesario tener instalado GraalVM, así como la variable de ambiente debe de apuntar al directorio de base, ejemplo:
```
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.3.0/Contents/Home
```

Se usa el siguiente comando: `./mvnw package -Pnative` para crear el binario.

Si no cuentas con GraalVM, se puede correr una contrucción nativa dentro de un contenedor, para lo cual debes de correr el siguiente comando:

```
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

Puedes ejecutar tu aplicación nativa con: `./target/jn-quarkus-base-1.0-SNAPSHOT-runner`

Para mayor referencia: https://quarkus.io/guides/building-native-image.

# Arquetipo

A continuación se explica la estructura del arquetipo y sus reglas generales de uso.

## Características instaladas

* cdi. Contextos e inyección de código
* resteasy. Paquete base para REST.
* resteasy-jsonb. Paquete base para JSon (hay varias opciones, pero para paquetes chicos esta es la mejor).
* hibernate-validator. Validaciones para pojos e interfaces rest.
* kubernetes. Manejo de ambientación de contenedores en OpenShift.
* smallrye-health. Para configurar las pruebas de contenedores.
* smallrye-openapi. Para decorar y documentar los swagger generados.
* swagger-ui. Utilería en tiempo de desarrollo para invocar desde el browser los servicios que se estan desarrollando.


## Estructura de paquetes

La estructura de paquetes es como sigue:
```
com.javanes.micro.<archetipe.name> -> Paquete Base.
  |- enums -> Paquete destinado a los enums.
  |- exception -> Paquete destinado a las excepciones.
  |- utils -> Paquete destinado a las Utilerias.
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