

function doLogout() {
	var form = document.getElementById("logoutForm");
	form.method = "POST";
	form.submit();
}