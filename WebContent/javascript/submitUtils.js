function setCommand(command, form) {
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "command");
	hiddenField.setAttribute("value", command);
	form.appendChild(hiddenField);
}

function addHiddenField(name, value, form) {
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", name);
	hiddenField.setAttribute("value", value);
	form.appendChild(hiddenField);
}

function createForm() {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "controller");
	return form;
}