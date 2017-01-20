# Dropwizard scaffold app

This is a sample application illustrating the use of dropwizard with some samples.

See the [live demo](https://desolate-hamlet-17428.herokuapp.com/) on heroku.

## Build Requirements

This app depends on the following tools being present:

- Java 8 SDK or greater
- Maven 3
- npm > 5 (see [nvm project page](https://github.com/creationix/nvm) for further details)

## Quick start

This command will build with maven (and npm) and start your app.

```bash
  ./start.sh
```

Visit: http://localhost:8182

## Features

Barebone server side dropwizard instance: ```src/main/java```

Small npm + webpack project to build static assets: ```src/main/scripts/node```

No hot deploy, to rebuild stop and rerun: ```./start.sh```

However, you can App.main from Intellij with these command line args: ```server config.yaml``` to enable the task below for hot deploy of static javascripts:
```bash
   cd src/main/scripts/node
   npm run devrun # run webpack in watch mode
```