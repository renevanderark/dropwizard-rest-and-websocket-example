import React from "react";
import { Link } from "react-router";

import ChatMessages from "./chat-messages";

class SampleApp extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      msg: ""
    }
  }
  onMsgChange(ev) {
    this.setState({msg: ev.target.value});
  }

  onMsgKeypress(ev) {
    if (ev.key === 'Enter') {
      this.props.onSubmitChatMessage(this.state.msg);
      this.setState({msg: ""})
    }
  }

  render() {
    const { sample: { sampleResponse, progressSample, progressSampleDisabled, chatMessages  }, onSampleClick, onPostSampleClick} = this.props;

    return (
      <div>
        <div className="navbar navbar-default">
          <div className="container">
            <Link className="navbar-brand" to="/">
              Dropwizard Scaffold
            </Link>
          </div>
        </div>
        <div className="container">
          <div className="panel panel-default">
            <div className="panel-heading">
              A simple xhr request
            </div>
            <div className="panel-body">
              <p>This button will trigger a simple GET request to /sample</p>
              <p><button className="btn btn-default" onClick={onSampleClick}>Click</button></p>

               <pre>
                {sampleResponse ? JSON.stringify(sampleResponse, null, 4) : "Click the button to see the response" }
              </pre>
            </div>
          </div>

          <div className="panel panel-default">
            <div className="panel-heading">
              A complex xhr request
            </div>
            <div className="panel-body">
              <p>This button will trigger a worker process via a POST request to /sample</p>
              <p>It will repond with chunks</p>
              <p>
                <button className="btn btn-default" onClick={onPostSampleClick} disabled={progressSampleDisabled}>
                  Click
                </button>
              </p>

              <pre>
                {progressSample ? JSON.stringify(progressSample, null, 4) : "Click the button to see the response" }
              </pre>
            </div>
          </div>

          <div className="panel panel-default">
            <div className="panel-heading">
              Chat via socket
            </div>
            <div className="panel-body">
              <p>This chat uses websockets over /socket-sample</p>
              <p>
                <input type="text" className="form-control" placeholder="message" value={this.state.msg}
                       onChange={this.onMsgChange.bind(this)} onKeyPress={this.onMsgKeypress.bind(this)}/>
              </p>

              <ChatMessages chatMessages={chatMessages} />
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default SampleApp;