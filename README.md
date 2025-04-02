# Hello PrizmDoc Viewer with Java and Angular

A small Java Spring and Angular application which shows how to use PrizmDoc Viewer in an Angular context.

## Pre-Requisites

- [JDK 17]
- [Maven] 3.0+

## Setup

### Configure the Application to Connect to PAS

Configure how to connect to PAS (PrizmDoc Application Services) by editing `server/src/main/resources/application.yml`.

#### Use PrizmDoc Cloud (Easiest)

If you're just getting started, the easiest thing to do is to use [PrizmDoc Cloud]. We host PAS for you and all you need is your [PrizmDoc Cloud](https://cloud.accusoft.com) API key. If you don't have an API key, you can get one for free at <https://cloud.accusoft.com>.

For [PrizmDoc Cloud], your `application.yml` will need to contain:

```yml
prizmdoc:
  pas:
    baseUrl: https://api.accusoft.com/prizmdoc/
  cloud:
    apiKey: YOUR_API_KEY
```

Where `YOUR_API_KEY` is your [PrizmDoc Cloud](https://cloud.accusoft.com) API key.

#### Host PAS Yourself (Advanced)

If you are self-hosting your own PAS instance, your `application.yml` contents will be slightly different:

```yml
prizmdoc:
  pas:
    baseUrl: YOUR_PAS_BASE_URL
    secretKey: YOUR_PAS_SECRET_KEY
```

`prizmdoc.pas.baseUrl` should be the base URL for requests to your PAS instance (like `"http://localhost:3000/"`).

`prizmdoc.pas.secretKey` must match the `secretKey` value specified in your PAS config file.

You will need to uncomment the "Self-Hosted PAS Configuration" section,
and comment out the "PrizmDoc Cloud Configuration" section to enable the correct proxy configuration.

### Hybrid Viewing

This sample includes a [Hybrid Viewing](https://help.accusoft.com/PrizmDoc/latest/HTML/client-side-pdf.html) option, which allows users to seamlessly switch between server-side and client-side rendering modes, providing a faster and smoother viewing experience.

To enable client-side viewing, update the following configuration in `application.yml`:

```yml
  useClientSideViewing: true
```

## Running the Sample

First, build the client and server applications with:

```bash
mvn clean install
```

This will build the client Angular application, package its output into a
standalone JAR file the server application can use as a dependency, and build
the server application.

Then, run the server application:

```bash
cd server
mvn spring-boot:run
```

This will launch a web application on `http://localhost:8080`.

If you have configured your connection to PAS correctly, you should see output like this (some lines removed for clarity):

```bash
[INFO] Scanning for projects...
.
.
.

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.3.RELEASE)

.
.
.
2020-10-09 15:20:33.795  INFO 63124 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-10-09 15:20:33.808  INFO 63124 --- [  restartedMain] sample.Application                       : Started Application in 19.656 seconds (JVM running for 20.726)
```

When you visit `http://localhost:8080`, you should see a home page with a list of example documents. Click on a document name to navigate to another page which displays the document using PrizmDoc Viewer.

## Developing Using the Client Dev Server

The instructions above performed a production build of the Angular client code and then started the application server using those pre-built client assets.

If you want to do development on the Angular application in the `client` directory, you can get faster feedback on your changes by using the built-in `ng serve` development server, which supports automatic hot reloading of changes in the browser as you make changes to the code.

_**NOTE:** this requires [Node.js](https://nodejs.org) 12.20 or higher to be installed._

_**NOTE:** this assumes you have built the client and server applications using `mvn clean install` as described above._

Here is how to use the client dev server:

### 1. Make Sure the Example Application Server Is Started

First, make sure the application server is running (if it is not already):

```bash
cd server
mvn spring-boot:run
```

This will launch the example application server on `http://localhost:8080`.

### 2. Start the Client Angular App in Dev Mode

Given the application server is already running at `http://localhost:8080`, use a separate terminal window to start the client dev server like so:

```bash
cd client
npm start
```

This will launch the a client dev server at `http://localhost:4200` and open the page in your browser.

The dev server (on port `4200`) is configured to proxy api routes to the application server (on port `8080`) so that the client can make REST API calls to the application server (this proxy setting is configured in `client/proxy.conf.json`).

[JDK 17]: https://adoptium.net/temurin/releases?version=17
[Maven]: https://maven.apache.org/index.html
[PrizmDoc Cloud]: https://cloud.accusoft.com
