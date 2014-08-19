package by.filippov.library.command;

import by.filippov.library.controller.RequestContent;

public interface Command {
	String execute(RequestContent content);
}
