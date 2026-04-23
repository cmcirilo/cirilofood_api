const config = {
  clientId: "cirilo-analytics",
  clientSecret: "analytics123",
  authorizeUrl: "http://api.cirilofood.local:8080/oauth/authorize",
  tokenUrl: "http://api.cirilofood.local:8080/oauth/token",
  callbackUrl: "http://www.ciriloanalytics.local:8082",
  cozinhasUrl: "http://api.cirilofood.local:8080/v1/cuisines"
};

let accessToken = "";

function get() {
  alert("Get resource with new access token " + accessToken);

  $.ajax({
    url: config.cozinhasUrl,
    type: "get",

    beforeSend: function(request) {
      request.setRequestHeader("Authorization", "Bearer " + accessToken);
    },

    success: function(response) {
      var json = JSON.stringify(response);
      $("#result").text(json);
    },

    error: function(error) {
      alert("error get resource");
    }
  });
}

function newAccessToken(code) {
  alert("New access token with code " + code);

  let clientAuth = btoa(config.clientId + ":" + config.clientSecret);
  
  let params = new URLSearchParams();
  params.append("grant_type", "authorization_code");
  params.append("code", code);
  params.append("redirect_uri", config.callbackUrl);

  $.ajax({
    url: config.tokenUrl,
    type: "post",
    data: params.toString(),
    contentType: "application/x-www-form-urlencoded",

    beforeSend: function(request) {
      request.setRequestHeader("Authorization", "Basic " + clientAuth);
    },

    success: function(response) {
      accessToken = response.access_token;

      alert("Access token: " + accessToken);
    },

    error: function(error) {
      alert("erro new access token");
    }
  });
}

function login() {
  // https://auth0.com/docs/protocols/oauth2/oauth-state
  let state = btoa(Math.random());
  localStorage.setItem("clientState", state);

  window.location.href = `${config.authorizeUrl}?response_type=code&client_id=${config.clientId}&state=${state}&redirect_uri=${config.callbackUrl}`;
}

$(document).ready(function() {
  let params = new URLSearchParams(window.location.search);

  let code = params.get("code");
  let state = params.get("state");
  let currentState = localStorage.getItem("clientState");

  if (code) {
    // window.history.replaceState(null, null, "/");

    if (currentState == state) {
      newAccessToken(code);
    } else {
      alert("State invalid");
    }
  }
});

$("#btn-get").click(get);
$("#btn-login").click(login);
