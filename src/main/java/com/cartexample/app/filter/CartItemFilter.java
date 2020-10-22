package com.cartexample.app.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CartItemFilter implements Filter {
	
	Logger logger = LogManager.getLogger(CartItemFilter.class);

	// called whenever a client request is received
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestUrl = httpServletRequest.getRequestURL().toString();
        
		// Create a response wrapper to capture the original response into wrapper before it is written
		HtmlResponseWrapper wrapper = new HtmlResponseWrapper((HttpServletResponse) response);
		
		logger.info("RequestUrl: " + requestUrl);
		
		// Pass wrapper into doFilter to capture the response as a string
		chain.doFilter(request, wrapper);
		String content = wrapper.getCaptureAsString();
		
		if (content.toLowerCase().contains("error") && requestUrl.contains("http://localhost:8080/api/cartItem")) {
			// modify the response
		    Pattern pattern = Pattern.compile("(Error: )(.)+\"");
			Matcher matcher = pattern.matcher(content);
			while (matcher.find()) {
				content = matcher.group().substring(0, matcher.group().length()-1);
			}
			response.setContentLength(content.getBytes().length);
		}
		response.getWriter().write(content);
		response.getWriter().close();
	}
}
