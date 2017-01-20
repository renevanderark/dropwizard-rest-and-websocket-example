import React from "react";
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
    const {
      sample: { sampleResponse, progressSample, progressSampleDisabled, chatMessages, connections },
      onSampleClick,
      onPostSampleClick
    } = this.props;

    return (
      <div>
        <div className="navbar navbar-default">
          <div className="container">
            <a className="navbar-brand" href="https://github.com/renevanderark/dropwizard-scaffold">
              <svg height="28" version="1.1" viewBox="0 0 16 16" width="28"><path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"/></svg>
            </a>
            <div className="navbar-brand">Dropwizard Scaffold</div>

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
              Chat via socket (connections: {connections})
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