package com.sp.file.common.provider;

import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sp.file.vo.UserInfoVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTProvider {

	private final String jwtSecret;
	private final int jwtExpire;

	public JWTProvider(@Value("${jwt.secret}") String jwtSecret, @Value("${jwt.expire}") int jwtExpire) {
		this.jwtExpire = jwtExpire;
		this.jwtSecret = jwtSecret;
	}

	public String generateToken(UserInfoVO login) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND, jwtExpire);

		Map<String, Object> claim = new HashMap<>();
		claim.put("uiId", login.getUiId());
		claim.put("uiName", login.getUiName());

		byte[] secrets = DatatypeConverter.parseBase64Binary(jwtSecret);
		Key key = new SecretKeySpec(secrets, "HmacSHA256");
		JwtBuilder jb = Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS256, key)
				.setExpiration(cal.getTime());
		return jb.compact();
	}

	public UserInfoVO decodeToken(String jwt) {
		try {
			Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
			UserInfoVO login = new UserInfoVO();
			login.setUiId(claims.get("uiId").toString());
			login.setUiName(claims.get("uiName").toString());
			return login;
		} catch (ExpiredJwtException eje) {
			log.error(eje.getMessage());
		} catch (JwtException je) {
			log.error(je.getMessage());
		}
		return null;
	}

	public boolean validateJWT(String jwt) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}