package phoenix.AM_PM.global.config.jwt;

public interface JwtProperties {
	String SECRET = "Poenix"; // 우리 서버만 알고 있는 비밀값
	int EXPIRATION_TIME = 1 * 60 * 1000; // 3분
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";

	String REFRESH_TOKEN_HEADER = "Refresh-Token";
	String REFRESH_TOKEN_PREFIX = "Bearer ";
	long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24시간 (예시)
}
