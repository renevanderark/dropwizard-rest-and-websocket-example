import ActionTypes from "../action-types";

const initialState = {
  sampleResponse: null,
  progressSample: null,
  progressSampleDisabled: false,
  chatMessages: []
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
      return {
        ...state,
        chatMessages: state.chatMessages.concat(action.data)
      };
    default:
  }

  return state;
}