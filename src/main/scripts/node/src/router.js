import React from "react";
import {Router, Route, IndexRoute, browserHistory} from "react-router";
import {Provider, connect} from "react-redux";

import store from "./store";
import actions from "./actions";

import SampleApp from "./components/sample-app";

const urls = {
  root() {
    return "/";
  }
};

const navigateTo = (key, args) => browserHistory.push(urls[key].apply(null, args));

const defaultConnector = (state) => state;
const connectComponent = (stateToProps) => connect(stateToProps, dispatch => actions(navigateTo, dispatch));

export default (
  <Provider store={store}>
    <Router history={browserHistory}>
      <Route path={urls.root()} component={connectComponent(defaultConnector)(SampleApp)}>
      </Route>
    </Router>
  </Provider>
);
