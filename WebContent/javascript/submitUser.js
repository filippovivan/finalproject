function chooseCatalogue(value) {
	var form = createForm();

	setCommand("CatalogueChoosen", form);
	addHiddenField("catalogue", value, form);

	document.body.appendChild(form);
	form.submit();
}

function choosePublication(value) {
	var form = createForm();

	setCommand("PublicationChoosen", form);
	addHiddenField("publication", value, form)

	document.body.appendChild(form);
	form.submit();
}

