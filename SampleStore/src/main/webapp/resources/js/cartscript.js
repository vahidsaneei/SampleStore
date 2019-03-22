function sendNewQuantity(id) {
	var val = document.getElementById("quantity" + id).value;
	var appUrl = "http://localhost:8080/SampleStore/store/completesale/" + id
			+ "/" + val;
	var url = "http://localhost:8080/SampleStore/addtocartlist";
	sendData(null, appUrl);
	window.location = url;
}

function sendData(data, targetUrl) {

	var http;
	if (window.XMLHttpRequest) {
		http = new XMLHttpRequest();
	} else {
		http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = appUrl + targetUrl;

	http.open('POST', url, true);
	http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			alert('items updated Sedsuccesfully');
		}
	}
	http.send(data);
}