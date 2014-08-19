function removeLibrarist(login) {
	var form = createForm();

	setCommand("RemoveLibrarist", form);
	addHiddenField("librarist", login, form);

	document.body.appendChild(form);
	form.submit();
}