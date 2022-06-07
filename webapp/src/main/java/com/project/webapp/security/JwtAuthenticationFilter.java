package com.project.webapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;
import com.project.webapp.services.UserDetailsServiceImpl;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
//bu bir filter clası.
	//frontendden bazckende bir request geldiğinde bizim için extra jwt filter aşaması koyucaz.
	//bu request otorize olmuş mu diye kontrol edicez değilse geri çevirecek
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//
		try {
			String jwtToken = extractJwtFromRequest(request);//headerdan extract ediyoruz tokenı.requesti alıp token ı dönsün
			if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
				Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);//token dolu ve validse içinden id yi alcaz
				UserDetails user = userDetailsService.loadUserById(id);//keyin içinden userı alıyoruz doğrummu diye kontrol edicez. id den user ı getiriyoruz
				if(user != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());//requesti otantikate etmemiz için gerekli.
					//bu objeyi oluyoruz details ekliyoruz.requesti ekliyoruz.
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);//user için gerekli securityleriyle bilgileri içinde tutan localthread
				//contexti rquesti koyduğumuzda istek otantiket edilmiş olcak ve servise erişebilecek.
				}
			}
		} catch(Exception e) {
			return;
		}
		filterChain.doFilter(request, response);//securitydeki filterlara gidip devam ediyor
	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");//headrelerda istek atarken otorization headeraı altında göndericazbearer yazısı oluyr.
		if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))//bearerımız dolu mu gelmiş içindeki doğru mu yazılmış kontrol
			return bearer.substring("Bearer".length() + 1);//doluysa key i return ediyoruz.başındaki bearer kısmını atıp jwt token kısmını döndürüyoruz
		return null;
	}
	

}
