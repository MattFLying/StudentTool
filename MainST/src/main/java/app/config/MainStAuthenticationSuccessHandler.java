package app.config;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import core.user.User;

/***
 * Class for handling authentication after login and redirect to specific pages.
 * 
 * @author Mateusz Mucha
 *
 */
@Component("mainStAuthenticationSuccessHandler")
public class MainStAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	protected Log logger = LogFactory.getLog(this.getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/***
	 * Method is called when user has been successfully authenticated.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	/***
	 * Method to handle url
	 * 
	 * @param request
	 *            - request
	 * @param response
	 *            - response
	 * @param authentication
	 *            - authentication
	 * @throws IOException
	 */
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/***
	 * Method to choose url page to redirect when user has specific role type.
	 * 
	 * @param authentication
	 *            - authentication
	 * @return url to target
	 */
	protected String determineTargetUrl(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals(User.Role.STUDENT.getName())) {
				return "/student/index.html";
			} else if (grantedAuthority.getAuthority().equals(User.Role.ADMIN.getName())) {
				return "/admin/index.html";
			} else if (grantedAuthority.getAuthority().equals(User.Role.TEACHER.getName())) {
				return "/teacher/index.html";
			}
		}

		return null;
	}

	/**
	 * Method clears authentication attributes.
	 * 
	 * @param request
	 *            - request
	 */
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	/***
	 * Method to gets redirect strategy
	 * 
	 * @return redirect to supplied url
	 */
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	/***
	 * Method to sets redirect strategy
	 * 
	 * @param redirectStrategy
	 *            - redirect to supplied url
	 */
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
}