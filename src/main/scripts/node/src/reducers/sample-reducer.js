import ActionTypes from "../action-types";

const initialState = {
  sampleResponse: null,
  progressSample: null,
  progressSampleDisabled: false,
  chatMessages: [],
  connections: 0
};

export default function(state=initialState, action) {
  switch (action.type) {
    case ActionTypes.SET_SAMPLE_RESPONSE:
      return {
        ...state,
        sampleResponse: action.sampleResponse
      };
    case ActionTypes.BEFORE_PROGRESS_SAMPLE:
      return {
        ...state,
        progressSampleDisabled: true
      };
    case ActionTypes.UPDATE_PROGRESS_SAMPLE:
      return {
        ...state,
        progressSample: action.payload
      };
    case ActionTypes.PROGRESS_SAMPLE_DONE:
      return {
        ...state,
        progressSampleDisabled: false
      };
    case ActionTypes.ON_CHAT_MSG:
      if (action.data.msg === "* ping! *") {
        return {...state, connections: action.data.count };
      }
      return {
        ...state,
        chatMessages: state.chatMessages.concat(action.data.source + ": " + action.data.msg),
        connections: action.data.count
      };
    default:
  }

  return state;
}