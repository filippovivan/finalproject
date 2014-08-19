function logout() {
	var form = createForm();
	setCommand("Logout", form);
	document.body.appendChild(form);
	form.submit();
}