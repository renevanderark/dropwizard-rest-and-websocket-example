import React from "react";
import ReactDOM from "react-dom";

class ChatMessages extends React.Component {

  shouldComponentUpdate(nextProps) {
    return this.props.chatMessages.length !== nextProps.chatMessages.length;
  }

  componentDidUpdate() {
    const domNode = ReactDOM.findDOMNode(this);
    domNode.scrollTop = domNode.scrollHeight;
  }

  render() {
    return (
      <ul className="list-group" style={{overflowY: "auto", height: "125px"}}>
        {this.props.chatMessages.map((msg, i) => <li className="list-group-item" key={i}>{msg}</li>)}
      </ul>
    );
  }
}

export default ChatMessages;