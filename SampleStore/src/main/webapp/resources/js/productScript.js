var appUrl = "http://localhost:8080/springsecurity";

function logoutPerform() {
	sessionStorage.removeItem('products');
}
function sendData(data, targetUrl, method) {
	var http;
	if (window.XMLHttpRequest) {
		http = new XMLHttpRequest();
	} else {
		http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = appUrl + targetUrl;

	http.open(method, url, true);
	http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			window.location = url;
			alert('itemsSedsuccesfully');
		}
	}
	http.send();
}

function getToPayment() {
	alert('Some implementation will insert here for payment mechanism');
}

function commentProcess(id) {
	var loginvar = checkLoggedUser();
	console.log("from commentprocess " + loginvar);
	if (loginvar == '1') {
		var http;
		var comment = document.getElementById("commenttext").value;
		if (window.XMLHttpRequest) {
			http = new XMLHttpRequest();
		} else {
			http = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = appUrl + "/comment/" + id;

		http.open('POST', url, true);
		http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
		http.onreadystatechange = function() {
			if (http.readyState == 4 && http.status == 200) {
				alert(responseText);
			}
		}
		http.send(comment);
	} else if (loginvar == '-1') {
		alert('please login at first!');
		alert(responseText);
		window.location = appUrl + "/login";
	}
}

function goToCart() {
	var data = sessionStorage.getItem("products");
	sendData(data, "/store/completesale?p=" + data, 'GET');
}
function checkLoggedUser() {
	var http;
	var result = {};
	if (window.XMLHttpRequest) {
		http = new XMLHttpRequest();
	} else {
		http = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var url = appUrl + "/checklogin";

	http.open('GET', url, true);
	http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
	http.send();
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			result["value"] = http.responseText;
			console.log(result["value"]);
			return result;
		}
	}
}
function setlike(id) {

	var v = checkLoggedUser();
	console.log("from like setter " + v);
	if (v === "1") {
		var http;
		var likelink = document.getElementById("likelink" + id);
		if (window.XMLHttpRequest) {
			http = new XMLHttpRequest();
		} else {
			http = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = appUrl + "/setlike/" + id;

		http.open('POST', url, true);
		http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
		http.onreadystatechange = function() {
			if (http.readyState == 4 && http.status == 200) {
				var value = http.responseText;
				likelink.href = "javascript:setUnlike(" + id + ")";
				toggleLikeStyle(id, value);
			}
		}
		http.send(id);
	} else {
		alert('please login at first!');
		window.location = appUrl + "/login";
	}
}

function setUnlike(id) {
	var http;
	var likelink = document.getElementById("likelink" + id);

	if (window.XMLHttpRequest) {
		http = new XMLHttpRequest();
	} else {
		http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = appUrl + "/setunlike/" + id;

	http.open('POST', url, true);
	http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			var value = http.responseText;
			likelink.href = "javascript:setlike(" + id + ")";
			toggleLikeStyle(id, value);
		}
	}
	http.send(id);
}
function completeSaling() {
	var login = checkLoggedUser();

	if (login == '1') {

	} else {
		window.location = appUrl + "/login";
	}
}
function addToCart(value, q) {

	var cartlink = document.getElementById("cartlink" + value);

	var param = sessionStorage.getItem("products");

	if (param) {
		if (param.split(',').indexOf(value) === -1) {
			param += value + '-' + q + ',';
			sessionStorage.setItem("products", param);
		}
	} else {
		sessionStorage.setItem("products", value + '-' + q + ',');
	}

}

function toggleLikeStyle(id, value) {

	var likelink = document.getElementById("likelink" + id);
	var likecount = document.getElementById("likecount" + id);
	likelink.classList.toggle("btn-danger");

}