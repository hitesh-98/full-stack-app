package com.nagarro.backendapi.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

	/**
	 *
	 * @param req It takes the request param for checking the authorization.
	 * @param res If the url is authorized then it sends back the response or
	 *            otherwise throw the exception of not authorized.
	 * @param e   This is the authentication exception param.
	 * @throws IOException Throws IO related exception.
	 */

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

	}

}
