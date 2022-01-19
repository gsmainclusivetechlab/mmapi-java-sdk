# GSMA Mobile Money Payments Java SDK Samples

The sample code snippets are all completely independent and self-contained. You can analyze them to get an understanding of how a particular method can be implemented in your application. 
In order to run these sample codes, you must have a valid `consumer key`, `consumer secret` and `api key`.


## Requirements

-   Java JDK-1.8 or higher
-   Apache Maven 3 or higher


## Setup

- Clone https://github.com/gsmainclusivetechlab/mmapi-java-sdk.git
- Import `mmapi-java-sdk-samples` project into your IDE
- Copy 'mmapi-java-sdk' jar file from `jar` folder to your project's classpath or build `mmapi-java-sdk` project using 'mvn clean package' command and copy this jar file to classpath
- Rename `config.properties.sample` file in `src\test\resources` to `config.properties` and replace placeholders with values for your `consumer key`, `consumer secret` and `api key`.

For example:

```java
CONSUMER_KEY=<your_consumer_key_here>
CONSUMER_SECRET=<your_consumer_secret_here>
API_KEY=<your_api_key_here>
CALLBACK_URL=<your_callback_url_here>
```

You are now ready to run your sample codes.

- Run individual samples