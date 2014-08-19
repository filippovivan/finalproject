package by.filippov.library.controller;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Class RequestContent.
 */
public class RequestContent {

	private HttpServletRequest request;

	public RequestContent(HttpServletRequest request) {
		this.request = request;
	}

	public Object getAttribute(String arg0) {
		return request.getAttribute(arg0);
	}

	public Enumeration<String> getAttributeNames() {
		return request.getAttributeNames();
	}

	public String getContentType() {
		return request.getContentType();
	}

	public String getContextPath() {
		return request.getContextPath();
	}

	public DispatcherType getDispatcherType() {
		return request.getDispatcherType();
	}

	public String getLocalName() {
		return request.getLocalName();
	}

	public Locale getLocale() {
		return request.getLocale();
	}

	public Enumeration<Locale> getLocales() {
		return request.getLocales();
	}

	public String getParameter(String arg0) {
		return request.getParameter(arg0);
	}

	public Map<String, String[]> getParameterMap() {
		return request.getParameterMap();
	}

	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}

	public String[] getParameterValues(String arg0) {
		return request.getParameterValues(arg0);
	}

	public StringBuffer getRequestURL() {
		return request.getRequestURL();
	}

	public String getServerName() {
		return request.getServerName();
	}

	public String getServletPath() {
		return request.getServletPath();
	}

	public HttpSession getSession() {
		return request.getSession();
	}

	public HttpSession getSession(boolean arg0) {
		return request.getSession(arg0);
	}

	public void setAttribute(String arg0, Object arg1) {
		request.setAttribute(arg0, arg1);
	}

	public void removeAttribute(String arg0) {
		request.removeAttribute(arg0);
	}

}
