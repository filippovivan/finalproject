package by.filippov.library.command;

import by.filippov.library.controller.RequestContent;
import by.filippov.library.utils.JspManager;

public class WrongCommand implements Command {

	@Override
	public String execute(RequestContent content) {
		// TODO create not found возвращает на стартовую в зависимости от типа
		// пользователя
		return JspManager.getProperty("error.error");
	}

}
