// package com.flamingo.JWT;


// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;


// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTCreationException;
// import com.auth0.jwt.exceptions.JWTVerificationException;
// import com.auth0.jwt.interfaces.Claim;
// import com.auth0.jwt.interfaces.DecodedJWT;
// import com.auth0.jwt.interfaces.JWTVerifier;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// @Component
// public class JWTUtil {

// 	private static final int TOKEN_VALIDITY = 3600 * 5;


// 	@Value("${jwt_secret}")
// 	private String secret;

// 	public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
// 		String role = "USER";
// 		if(email.equals("admin@flamingo")){
// 			role="ADMIN";
// 		}

// 		return JWT.create()
	
// 				.withSubject(email)
// 				.withClaim("email", email)
// 				.withClaim("Role", role)
// 				.withIssuedAt(new Date())
// 				.withIssuer("Event Scheduler")
// 				.sign(Algorithm.HMAC256(secret));
// 	}
	
// 	public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
// 		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
// 									.withSubject(getSubject( token))
// 									.withIssuer("Event Scheduler").build();
		
// 		DecodedJWT jwt = verifier.verify(token);
		
// 		return jwt.getClaim("email").asString();
// 	}
//     public String getSubject(String token) {
//         return parseClaims(token).getSubject();
//     }
     
//     private Claims parseClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secret)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }




// }