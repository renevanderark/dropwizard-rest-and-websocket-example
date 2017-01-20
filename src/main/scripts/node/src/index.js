import React from "react";
import ReactDOM from "react-dom";
import router from "./router";

const webSocket = new WebSocket("ws://localhost:8182/socket-sample");

webSocket.onmessage = ({ data }) => console.log(data);
webSocket.onopen = () => webSocket.send("test message 213");

document.addEventListener("DOMContentLoaded", () => ReactDOM.render(router, document.getElementById("app")));