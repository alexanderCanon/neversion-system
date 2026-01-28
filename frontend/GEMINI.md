# Gemini Project Context: neversion-site

## Project Overview

This is a web application built with Angular, a popular framework for creating single-page applications (SPAs). It was generated using the Angular CLI, which provides a standard project structure and development tools. The project is written in TypeScript.

The main application module is `AppModule`, which bootstraps the root component `AppComponent`. The project is configured with standard build processes for development and production environments.

## Building and Running

The following scripts are available in `package.json` to manage the application lifecycle:

*   **`npm start`**: Runs the application in development mode. The app will be available at `http://localhost:4200/` and will automatically reload when source files are changed.
*   **`npm run build`**: Builds the application for production. The output artifacts will be stored in the `dist/neversion-site/` directory.
*   **`npm test`**: Executes the unit tests using Karma.
*   **`npm run watch`**: Builds the application in development mode and watches for changes.

The underlying commands executed by these scripts are from the Angular CLI (`ng serve`, `ng build`, `ng test`).

## Development Conventions

*   **Structure**: The project follows the standard Angular CLI directory structure. All application source code is located in the `src/` directory.
*   **Components**: Components are the basic building blocks of the application. Use `ng generate component <component-name>` to create a new component.
*   **Modules**: The application is organized into modules. The root module is `AppModule` in `src/app/app.module.ts`.
*   **Styling**: Global styles are defined in `src/styles.css`. Component-specific styles are defined in their respective `.css` files.
*   **Testing**: Unit tests are written using Jasmine and run with Karma. Test files have the `.spec.ts` extension.
