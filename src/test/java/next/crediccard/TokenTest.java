package next.crediccard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import next.security.JwtTokenUtil;

public class TokenTest {
	
	@Test
	public void testGenerateTokenAndGetUser() {
		
		String token = JwtTokenUtil.getInstance().generateToken("daniel");		
		
		String nombre = JwtTokenUtil.getInstance().getUsernameFromToken(token);		
		assertEquals(nombre, "daniel");
		
	}

}
