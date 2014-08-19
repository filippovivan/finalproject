package by.filippov.library.command;

import org.apache.log4j.Logger;

import by.filippov.library.controller.RequestContent;

public class CommandFactory {

	private static final Logger LOG = Logger.getLogger(CommandFactory.class);

	public Command defineCommand(RequestContent content) {
		String command = content.getParameter("command");
		if (command == null) {
			return new WrongCommand();
		}
		try {
			return (Command) Class.forName(
					"by.filippov.library.command." + command + "Command")
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			LOG.error("Unknown command occured", e);
			return new WrongCommand();
		}
		/*
		 * switch (command) { case ("Login"): return new LoginCommand(); case
		 * ("StartRegistration"): return new StartRegisterationCommand(); case
		 * ("Register"): return new RegisterCommand(); case
		 * ("CatalogueChoosen"): return new CatalogueChoosenCommand(); case
		 * ("PublicationChoosen"): return new PublicationChoosenCommand(); case
		 * ("RemovePublication"): return new RemovePublicationCommand(); case
		 * ("RemoveLibrarist"): return new RemoveLibraristCommand(); case
		 * ("CreateLibrarist"): return new CreateLibraristCommand(); case
		 * ("Logout"): return new LogoutCommand(); default: return new
		 * WrongCommand(); }
		 */
	}

}
