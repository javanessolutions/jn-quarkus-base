# Proyecto: jn-quarkus-base

Este es el arquetipo base de Quarkus 1.7 para el desarrollo de aplicaciónes.

Para mayor Referencia visitar los sitios: 

https://access.redhat.com/documentation/en-us/red_hat_build_of_quarkus/1.7/
https://quarkus.io/

## Comandos de quarkus para que sean usados por este arquetipo

### Ejecución local de la aplicación (en tu maquina)

Puedes ejecutar la aplicación localmente con el siguiente comando, ademas no es necesario
que vuelvas a compilar para tomar los cambios que hagas:
```
./mvnw quarkus:dev
```

La forma más facil de probar la aplicación directamente es usando curl:
```
curl -X GET http://localhost:8080/hello
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `jn-quarkus-base-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/jn-quarkus-base-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/jn-quarkus-base-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Para desplegar la aplicación en Openshift

```
./mvnw clean package -Dquarkus.kubernetes.deploy=true
```
