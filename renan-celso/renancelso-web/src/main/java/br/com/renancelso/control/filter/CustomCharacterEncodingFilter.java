package br.com.renancelso.control.filter;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Renan Celso
 *
 */
public class CustomCharacterEncodingFilter implements Filter {

	protected Logger log = Logger.getLogger(CustomCharacterEncodingFilter.class.getName());
	
	@Override
    public void init(FilterConfig config) throws ServletException {
		log.info("init(FilterConfig config) CustomCharacterEncodingFilter");       
    }

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                           throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");         
        ((HttpServletResponse) response).addHeader("X-UA-Compatible", "IE=Edge");
        chain.doFilter(request, response);
    }

	@Override
    public void destroy() {
		log.info("destroy() CustomCharacterEncodingFilter");
    }
}
