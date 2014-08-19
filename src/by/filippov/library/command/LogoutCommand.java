package by.filippov.library.command;

import by.filippov.library.controller.RequestContent;
import by.filippov.library.utils.JspManager;

public class LogoutCommand implements Command {

	@Override
	public String execute(RequestContent content) {
		content.getSession().invalidate();
		return JspManager.getProperty("main.login");
	}

}
