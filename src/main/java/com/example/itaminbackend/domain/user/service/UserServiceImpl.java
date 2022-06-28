package com.example.itaminbackend.domain.user.service;

import com.example.itaminbackend.domain.user.constant.UserConstants.EToken;
import com.example.itaminbackend.domain.user.dto.LoginRequest;
import com.example.itaminbackend.domain.user.dto.LoginResponse;
import com.example.itaminbackend.domain.user.dto.UserDto;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.domain.user.exception.NotFoundEmailException;
import com.example.itaminbackend.domain.user.repository.UserRepository;
import com.example.itaminbackend.global.config.security.jwt.TokenProvider;
import com.example.itaminbackend.global.dto.TokenInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        //System.out.println("로그인테스트1");
        //User user = this.validateEmail(loginRequest.getEmail());
        System.out.println("로그인테스트2");
        TokenInfoResponse tokenInfoResponse = this.validateLogin(loginRequest);
        return LoginResponse.from(tokenInfoResponse);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email){
//        log.info("loadUserByUsername");
//        this.userRepository.findByEmail(email);
//        return this.userRepository.findOneWithAuthoritiesByLoginId(loginId)
//                .map(user -> createUser(loginId, user))
//                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
//    }

//    private org.springframework.security.core.userdetails.User createUser(String username, User user) {
//        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
//                .collect(Collectors.toList());
//        return new org.springframework.security.core.userdetails.User(user.getLoginId(),
//                user.getPassword(),
//                grantedAuthorities);
//    }

    public TokenInfoResponse validateLogin(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenInfoResponse tokenInfoResponse = this.tokenProvider.createToken(authentication);
        this.redisTemplate.opsForValue()
                .set(EToken.eRefreshToken.getMessage() + authentication.getName(),
                        tokenInfoResponse.getRefreshToken(), tokenInfoResponse.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);
        return tokenInfoResponse;
    }

    /**
     * validate
     */

    public User validateEmail(String email){
        return this.userRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

    private final String key="ddfadaewfd";

    @Value("${spring.security.oauth2.client.registration.google.clientId}")
    public String googleClientId;

    @Override
    public User saveOrUpdate(User user) {
        User myuser = userRepository.findByEmail(user.getEmail())
                .map(entity -> entity.update(user.getName(), user.getImageUrl()))
                .orElse(user.toEntity());

        return userRepository.save(myuser);
    }


    @Override
    public UserDto.GoogleLoginRequest providegoogleJWTToken(User user){

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        //헤더에는 jwt의 암호화 방법 정보가 들어있다.

        //payload 부분 설정
        String email=user.getEmail();
        String imageUrl=user.getImageUrl();
        String name=user.getName();

        System.out.println("jwt providing email");
        log.info(email);
        log.info(imageUrl);
        log.info(name);

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", email);
        payloads.put("imageUrl", imageUrl);
        payloads.put("name", name);
        //실제적인 jwt의 데이터를 담당하는 부분이다.

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성
        log.info(jwt);

        UserDto.GoogleLoginRequest googleLoginRequest = new UserDto.GoogleLoginRequest();
        googleLoginRequest.setToken(jwt);
        return googleLoginRequest;
    }

    public UserDto.NaverLoginRequest providenaverJWTToken(User user){

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");
        //헤더에는 jwt의 암호화 방법 정보가 들어있다.

        //payload 부분 설정
        String email=user.getEmail();
        String imageUrl=user.getImageUrl();
        String name=user.getName();
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", email);
        payloads.put("imageUrl", imageUrl);
        payloads.put("name", name);
        //실제적인 jwt의 데이터를 담당하는 부분이다.

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성

        UserDto.NaverLoginRequest naverLoginRequest = new UserDto.NaverLoginRequest();
        naverLoginRequest.setToken(jwt);
        return naverLoginRequest;
    }

    @Override
    public UserDto.GoogleLoginRequest authGoogleUser(UserDto.GoogleLoginRequest googleLoginRequest){
        String tokenId = googleLoginRequest.getToken(); // test받는 식으로 받던가 tokenId받는 식으로 받던가임
        log.info(tokenId);
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        HttpTransport transport = new NetHttpTransport();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        //String idTokenString = req.getParameter("googleIdtoken"); //프론트엔드로부터 넘겨받은 id token

        try {
            GoogleIdToken idToken = verifier.verify(tokenId); //  verifies the JWT signature, the aud claim, the iss claim, and the exp claim.

            System.out.println("verify");

            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Print user identifier
                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                Boolean emailVerified=payload.getEmailVerified();

                log.info(email);
                log.info(name);
                log.info(pictureUrl);


                User user=new User(name,email,pictureUrl,emailVerified);
                System.out.println("setting");


                saveOrUpdate(user);

                return providegoogleJWTToken(user);

            } else {
                String result = "Invalid ID token.";
                googleLoginRequest.setToken(result);
                return googleLoginRequest;
            }
        }
        catch(Exception e){
            googleLoginRequest.setToken("invalid Id token");
            return googleLoginRequest;
        }
    }

    @Override
    public UserDto.NaverLoginRequest authNaverUser(UserDto.NaverLoginRequest naverLoginRequest) throws IOException {
        String token = naverLoginRequest.getToken();
        String header = "Bearer " + token; // Bearer 다음에 공백 추가


        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);

        ObjectMapper mapper=new ObjectMapper();
        // String json="{\"resultcode\": \"00\", \"message\": \"success\", \"response\": {\"email\": \"openapi@naver.com\", \"name\": \"OpenAPI\", \"profile_image\": \"https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif\"}}";
        Map<String, String> map= mapper.readValue(responseBody,Map.class);

        Map map2= mapper.readValue(responseBody,Map.class);

        Map map3= (Map) map2.get("response");


        String email=map3.get("email").toString();
        System.out.println(email);
        String name=map3.get("name").toString();
        String pictureUrl=map3.get("profile_image").toString();

        Boolean emailVerified=true;

        User user=new User(name,email,pictureUrl,emailVerified);
        System.out.println("setting");


        saveOrUpdate(user);
        return providenaverJWTToken(user);
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }




}
