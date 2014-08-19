package by.filippov.library.commands;

import by.filippov.library.controller.RequestContent;

public interface Command {
	String execute(RequestContent content);
}
