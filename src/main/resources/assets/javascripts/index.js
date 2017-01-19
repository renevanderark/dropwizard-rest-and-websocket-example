document.addEventListener("DOMContentLoaded", function() {
  document.getElementById('app').innerHTML = "<pre>" + JSON.stringify(globals, null, 4) + "</pre>";
});