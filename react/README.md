# JavaScript/React Front-End
This is the shared JavaScript/React front-end for the Java applications.

## Running the Front-End Locally
You can start the front-end locally on it's own with a mock backend.

* Ensure (Node.js is installed)[https://nodejs.org/en/download].
* Start json-server from the `react` directory with:

  ```
  npm run json-server
  ```

  The mock data can be accessed at `http://localhost:3001/todos`.

* Set an environment variable `NODE_OPTIONS` to `--openssl-legacy-provider`. 
  In *NIX based systems:

  ```
  export NODE_OPTIONS=--openssl-legacy-provider
  ```

* Start the front-end from the `react` directory:

  ```
  npm start
  ```

  Access the frontend at `http://localhost:3000`.
