function removePublication(publication, catalogue) {
	var form = createForm();
	setCommand("RemovePublication");
	addHiddenField("publication", publication, form);
	addHiddenField("catalogue", catalogue, form);
	document.body.appendChild(form);
	form.submit();
}