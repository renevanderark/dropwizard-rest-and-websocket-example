# Dropwizard scaffold app

This is a sample application illustrating the use of [Dropwizard](http://www.dropwizard.io) with some samples for REST and websockets.

See the [live demo](https://desolate-hamlet-17428.herokuapp.com/) on heroku.

## Build Requirements

This app depends on the following tools being present:

- Java 8 SDK or greater
- Maven 3
- npm > 5 (see [nvm project page](https://github.com/creationix/nvm) for further details on running a current version of npm)

## Quick start

This command will build with maven (and npm) and start your app.

```bash
  ./start.sh
```

Visit: [http://localhost:8182](http://localhost:8182)

## Features

### Backend

Barebone server side dropwizard instance: ```src/main/java```
It has a RootEndpoint for static assets and a SampleEndpoint for Rest / Websocket examples.

### Frontend

The frontend is a single page javascript app through an html template, exposing some environment settings to the javascripts through a globals var```console.log(globals.env)```.

The frontend is built with [React](https://facebook.github.io/react/).  

Small npm + webpack project to build static assets: ```src/main/scripts/node```

Static resources (css / javascript / fonts) are hosted from ```src/main/resources/assets``` via ```/assets/*```

You can of course always write vanilla javascript in ```src/main/resources/assets/javascripts```


## Development modes

No hot deploy, to rebuild stop and rerun: ```./start.sh```

However, you can run App.main from Intellij with these command line args: ```server config.yaml``` to enable the task below for hot deploy of javascripts:
```bash
   cd src/main/scripts/node
   npm run devrun # run webpack in watch mode
```