import {createStore, applyMiddleware} from "redux";
import thunkMiddleware from "redux-thunk";

import reducers from "./reducers";

const logger = () => next => action => {
  if (action.hasOwnProperty("type")) {
    console.log("[REDUX]", action.type, action);
  }

  return next(action);
};

const createStoreWithMiddleware = applyMiddleware(/*logger,*/ thunkMiddleware)(createStore);
export default createStoreWithMiddleware(reducers);
