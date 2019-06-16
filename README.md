# identity-api

This repository contains modules related to the user identity apis feature

### Generating the Stubs

To generate stub, the [swagger2cxf-maven-plugin](https://github.com/hevayo/swagger2cxf-maven-plugin) is used.

We have done improvements to this plugin so that the stubs can be generated within a given package name. Therefore 
you can define multiple swagger files and deploy them in a single web application. To get the improvements locally, 
please follow the given steps.

1. Clone the repository [https://github.com/IsuraD/swagger2cxf-maven-plugin](https://github.com/IsuraD/swagger2cxf-maven-plugin)
```
git clone https://github.com/IsuraD/swagger2cxf-maven-plugin.git
```
2. Checkout the branch **configurable_package**
```
git checkout configurable_package
```
3. Build the plugin 
```
mvn clean install
```

Now, the locally built swagger2cxf plugin will be picked from the local .m2 repository when generating the stubs.

1. Include the API definition in the given location of this maven project (identity-api). Assume the file name of the
 API definition is *api.yaml*
    ```
    +-- identity-api
    |   +-- components
    |       +-- org.wso2.carbon.identity.api.endpoint
    |           +-- src
    |               +-- main
    |                   +-- resources
    |                       +--api.yaml
    |           +-- pom.xml
    ```
2. Include the given plugin to the `pom.xml` file of the module `org.wso2.carbon.identity.api.endpoint`
    ```
    <plugin>
        <groupId>org.wso2.maven.plugins</groupId>
        <artifactId>swagger2cxf-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
        <configuration>
            <packageName>association</packageName>
            <inputSpec>${project.basedir}/src/main/resources/api.yaml</inputSpec>
        </configuration>
    </plugin>
    ```
    Give the required package name for generated stubs as `<packageName>` and the file path to API definition yaml 
    file as `<inputSpec>`
3. Run the following command inside the module `org.wso2.carbon.identity.api.endpoint` to generate the stubs
    ```
    mvn swagger2cxf:generate
    ```
4. Comment out the plugin added for your API definition before committing to the git.
    


