package Base.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	String requestTokenHeader = request.getHeader("Authorization");
	
		String username=null;
		String jwtToken=null;
		
		//null and format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
		{
			requestTokenHeader.substring(7);
			try {
				
			username=	this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.fillInStackTrace();
			}
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new 
			UsernamePasswordAuthenticationToken(userDetailsService,null,
					userDetails.getAuthorities());
		 
	 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else
			{
				
				System.out.println("Token is not Valid");
			}
		
	}
		filterChain.doFilter(request, response);
	}

}
