# Robot Controller Client
This is the client application that allows users to control the Robot. Simply enter the commands 
as a script in the input box and execute to see the Robot change position

### Sample Script

```
POSITION 1 3 EAST
FORWARD 3
WAIT
TURNAROUND
FORWARD 1
RIGHT
FORWARD 2
```

### How to run the project

This project was implemented using `Node v21.7.1` and `npm v10.5.0` so make sure you have compatible versions installed.

In the project directory, you can run:

### `npm install` 

Installs the required node modules and dependencies

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

### End to End Automated Tests

E2E automated tests are written using Cypress. While the application is running you can run `npx cypress run` 
to execute the tests

## Areas to improve
### Current status
All the basic requirements mentioned in the Coding Challenge is completed however given more time there can be
more improvements done

### Improvements

- Break the application into proper components
- Move some properties to configuration files
- Write unit tests
- Implement certain validations for script input and grid movement
- Improve UI with better styling and user experience



