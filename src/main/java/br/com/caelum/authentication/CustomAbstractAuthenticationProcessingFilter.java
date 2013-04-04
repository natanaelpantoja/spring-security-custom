package br.com.caelum.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import br.com.caelum.estoque.model.Usuario;

@Component
public class CustomAbstractAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	protected CustomAbstractAuthenticationProcessingFilter() {
		super("/j_spring_security_filter");
	}
		
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String login = request.getParameter("j_username"); 
		String senha = request.getParameter("j_password");
		String cnpj = request.getParameter("cnpj");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setCnpj(cnpj);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login, usuario);
		//authenticationToken.setAuthenticated(false);
		
		return this.getAuthenticationManager().authenticate(authenticationToken);
	}
	
	
}
