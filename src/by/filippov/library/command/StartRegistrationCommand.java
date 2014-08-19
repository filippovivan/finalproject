package by.filippov.library.command;

import by.filippov.library.controller.RequestContent;

public class StartRegistrationCommand implements Command {

	@Override
	public String execute(RequestContent content) {
		return "/jsp/main/registration.jsp";
	}

}
