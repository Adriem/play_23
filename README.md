Play-slick template
=================================

This is a simple slick template using play 2.3.7 with slick 0.8.1.

This app serves the data needed by the app [BetaBeersZGZ June 2015](https://github.com/RecuencoJones/BB062015), a talk about AngularJS and the migration needed from a completely JQuery web

### Running the app in development mode

To run this app use `activator run` and the server will run on `localhost:9000`, this may take a while to download all the dependencies, framework, compiler, builder, etc. if you don't want to wait for this use the distribution zip

To change the port use `-Dhttp.port=<port>` as a param along with the run command as this app may colide with the node http server used in the other app

For further information about the console: [PlayConsole(Activator)](https://www.playframework.com/documentation/2.3.x/PlayConsole)

### Running the app in production mode

Unzip the [distribution zip](play_23-1.0-SNAPSHOT.zip) and run the script located un /bin/, the first parameter is the class path dependecies followed by the activator parameters, i.e. to run the app in other port: `play_23.bat . -Dhttp.port=8080`