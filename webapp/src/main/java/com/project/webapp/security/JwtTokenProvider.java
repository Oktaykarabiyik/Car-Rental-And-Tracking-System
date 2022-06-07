package com.project.webapp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



@Component
public class JwtTokenProvider {
	@Value("${webapp.app.secret}")
	private String APP_SECRET;//bu application özel key i oluşturucaz buna göre bir token oluşturucaz
	
	@Value("${webapp.expires.in}")
	private long EXPIRES_IN;//kaç saniyede bir token geçerliliğini yitiriyor.
//değerlerini direkt burda ermiyoruz application propertieste vericez
	
	//token ın generete olacağı metod:
	public String generateJwtToken(Authentication auth) {
		JwtUserDetails userDetails=(JwtUserDetails) auth.getPrincipal();
	//principal:otantiket edeceğimiz user demek objenin içinde. onu alıyoruz ve jwtuserdetails e cast yapıyor.
	Date expireDate=new Date(new Date().getTime()+EXPIRES_IN);//ne zaman expire olaağını hesaplıyor
	
	//şimdi elimizdekilerle jwttoken oluşturalım
	return Jwts.builder()//token ı bulild ediyor
			.setSubject(Long.toString(userDetails.getId()))//userımız
			.setIssuedAt(new Date()).setExpiration(expireDate)//key ne zaman oluştu
			.signWith(SignatureAlgorithm.HS512,APP_SECRET).compact();//key oluşturmak için kullanılan algoritmalar. compactle oluşturuyoruz
			//jwts->dependency içinde gelen hazır bir şey
	}
	//oluşturduğumuz keyden çözüp user ıd yi bulucaz
	Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(APP_SECRET)//parse ederken bu key i kullanıyoruz
				.parseClaimsJws(token).getBody();//token ı verdik.key e göre bu token ı çöz. elinde body bulunuyor
		return Long.parseLong(claims.getSubject());
	}
	
	boolean validateToken(String token) {//token geçerli mi diye kontrol edicez
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);//jwt yi çözümlüyoruz
			return  !isTokenExpired(token);//expire olduysa false dönsün olmadıysa true dönsün hala geçerli anlamında
		} catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
	}
	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
	
}
