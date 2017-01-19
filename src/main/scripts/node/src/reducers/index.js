import {combineReducers} from "redux";
import sampleReducer from "./sample-reducer";

export default combineReducers({
  sample: sampleReducer
});
