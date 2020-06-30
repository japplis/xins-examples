var req;

function loadXMLDoc(url) {
      // branch for native XMLHttpRequest object
      if (window.XMLHttpRequest) {
              req = new XMLHttpRequest();
              req.onreadystatechange = processStateChange;
              try {
                      req.open("GET", url, true);
              } catch (e) {
                      alert(e);
              }
              req.send(null);
      // branch for IE/Windows ActiveX version
      } else if (window.ActiveXObject) {
              req = new ActiveXObject("Microsoft.XMLHTTP");
              if (req) {
                      req.onreadystatechange = processStateChange;
                      req.open("GET", url, true);
                      req.send();
              }
      }
}

function processStateChange() {
      if (req.readyState == 4) { // Complete
              if (req.status == 200) { // OK response
                      applyResult(req.responseXML.documentElement);
              } else {
                      alert("Problem: " + req.statusText);
              }
      }
}

function petSuggest() {
	if (document.form1.petName.value.length > 1) {
		var url = "?_function=SearchPetOkay&_convention=_xins-std&petName=" + document.form1.petName.value;
		loadXMLDoc(url);
	}
}

function applyResult(result) {
	var errorCode = result.attributes.getNamedItem('errorcode');

	var dataSection = result.childNodes[0];
        
        var value = '';
        for (i = 0; i < dataSection.childNodes.length; i++) {
           value += dataSection.childNodes[i].getAttribute('petName');
           if (i != dataSection.childNodes.length - 1) value += '; ';
	}
        document.getElementById('suggestion').innerHTML = "Suggestions: " + value;
}
