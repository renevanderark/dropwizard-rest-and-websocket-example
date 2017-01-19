import xhr from "xhr"
import ActionTypes from "./action-types";

export default function actionsMaker(navigateTo, dispatch) {
  return {
    onSampleClick: () => {
      xhr.get("/sample", (err, resp, body) => dispatch({
        type: ActionTypes.SET_SAMPLE_RESPONSE, sampleResponse: JSON.parse(body)
      }))
    },

    onPostSampleClick: () => {
      const req = new XMLHttpRequest();

      req.open('POST', "/sample");

      dispatch({type: ActionTypes.BEFORE_PROGRESS_SAMPLE});

      req.onreadystatechange = function() {
        const start = req.responseText.lastIndexOf('\n');
        if (start > -1) {
          const payload = req.responseText.substr(start, req.responseText.length);
          try {
            dispatch({type: ActionTypes.UPDATE_PROGRESS_SAMPLE, payload: JSON.parse(payload)});
          } catch (e) {
            console.warn(e);
          }
        }
      };

      req.onload = function() {
        dispatch({type: ActionTypes.PROGRESS_SAMPLE_DONE});
      };

      req.send();
    }
  };
}