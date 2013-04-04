package br.com.caelum.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.caelum.estoque.dao.UsuarioDAO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	public Authentication authenticate(Authentication authentication)throws AuthenticationException {
		UserDetails userDetails = usuarioDAO.loadUserByUsername(authentication.getName());
		if(userDetails != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(userDetails.getAuthorities());
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			return authenticationToken;
		}
		return authentication;
	}

}
