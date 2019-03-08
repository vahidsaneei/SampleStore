function sendData(data) {
	var http;
	var data = sessionStorage.getItem("products");

	if (window.XMLHttpRequest) {
		http = new XMLHttpRequest();
	} else {
		http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "http://localhost:8080/springsecurity/addtocartlist";
	http.open('POST', url, true);
	http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			window.location = "http://localhost:8080/springsecurity/showsalelist";
		}
	}
	http.send(data);
}

function calcPrice(id) {
	var quantity = document.getElementById("quantity" + id).value;
	var price = document.getElementById("price" + id).innerHTML;
	var total = document.getElementById("total" + id);
	removeFromCart(id);
	addToCart(id, quantity);
	total.innerHTML = price * parseInt(quantity);
	totalCalc();
}
function checkLoggedUser() {
	var http;
	if (window.XMLHttpRequest) {
		http = new XMLHttpRequest();
	} else {
		http = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var url = "http://localhost:8080/springsecurity/checklogin";

	http.open('GET', url, true);
	http.setRequestHeader('Content-type', 'application/json;charset=UTF-8');
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			getOrder();
		} else if (http.readyState == 4 && http.status == 401) {
			alert('you must login at first');
			window.location = "http://localhost:8080/springsecurity/login";
		}
	}
	http.send();
}
function removeproduct(id) {
	var objTable = document.getElementById("salelist");
	var row = document.getElementById("row" + id);
	removeFromCart(id);
	row.parentNode.removeChild(row);
	totalCalc();

}
function arrayRemove(arr, value) {
	return arr.filter(function(elem) {
		var elems = elem.split('-');
		return elems[0] != value;
	});
}
function removeFromCart(value) {
	var prds = sessionStorage.getItem("products").split(',');
	var res = arrayRemove(prds, value);
	sessionStorage.setItem("products", res);
}
function getOrder() {
	var tbl = document.getElementById("salelist");
	rowcount = tbl.rows.length;
	var totalprice = tbl.rows[rowcount - 1].cells[3].innerHTML;

	alert('total price is :' + totalprice);
}
function totalCalc() {
	var tbl = document.getElementById("salelist");
	var rowcount = tbl.rows.length;
	var tblrows = tbl.rows;
	var sumprice = 0, sumproducts = 0;

	for (var i = 1; i <= rowcount - 2; i++) {
		sumproducts += parseInt(tblrows[i].cells[3]
				.getElementsByTagName("input")[0].value);
		sumprice += parseFloat(tblrows[i].cells[4].innerHTML);
	}
	tblrows[rowcount - 1].cells[1].innerHTML = sumproducts;
	tblrows[rowcount - 1].cells[3].innerHTML = sumprice;
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