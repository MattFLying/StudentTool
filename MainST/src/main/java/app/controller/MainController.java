package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * Main class controller for basic oeprations on web application.
 * 
 * @author Mateusz Mucha
 *
 */
@Controller
public class MainController {
	/***
	 * Method allows to enter index page.
	 * 
	 * @return index page url
	 */
	@RequestMapping(value = { "/", "index" })
	public String home() {
		return "index";
	}

	/***
	 * Method allows to enter login page.
	 * 
	 * @return login page url
	 */
	@RequestMapping(value = { "/login" })
	public String login() {
		return "login";
	}

	/***
	 * Method allows to enter logout page.
	 * 
	 * @param httpSession
	 *            - http session
	 * @return logout page url
	 */
	@RequestMapping(value = { "/logout" })
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();

		return "logout";
	}

	/***
	 * Method allows to enter failure page after no permissions.
	 * 
	 * @return failure page url
	 */
	@RequestMapping(value = "/failure")
	public String failure() {
		return "failure";
	}
}