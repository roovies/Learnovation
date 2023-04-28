package com.kh.learnovation.domain.user.controller;

import com.kh.learnovation.domain.user.dto.UserDTO;
import com.kh.learnovation.domain.user.entity.User;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.learnovation.domain.user.config.auth.PrincipalDetail;
import com.kh.learnovation.domain.user.model.KakaoProfile;
import com.kh.learnovation.domain.user.model.OAuthToken;
import com.kh.learnovation.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {

    private final UserService userService;

	//HttpURLConnection con = (HttpURLConnection) url.openConnection();
	private String cosKey = "cos1234";

	@Autowired
	private AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 목록 조회
    @RequestMapping(value = "/admin/userList", method = RequestMethod.GET)
    public String userList(
            Model model,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum
    ) {

        List<UserDTO> userList = userService.getUserList(pageNum);
        Integer[] pageList = userService.getPageList(pageNum);

        model.addAttribute("userList", userList);
        model.addAttribute("pageList", pageList);

        return "admin/userList";
    }

    // 회원 이름으로 검색
    @RequestMapping(value = "/admin/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<UserDTO> userDTOList = userService.searchUsers(keyword);
        model.addAttribute("userList", userDTOList);

        return "admin/userList";
    }

    // 회원 상세정보 조회
    @RequestMapping(value = "/admin/detail/{no}", method = RequestMethod.GET)
    public String detail(@PathVariable("no") Long id, Model model) {
        UserDTO userDTO = userService.getPost(id);

        model.addAttribute("user", userDTO);
        return "admin/userDetail";
    }

    @PutMapping("/admin/update/{id}")
    public String userUpdate(UserDTO userDTO) {
        userService.savePost(userDTO);
        return "redirect:/admin/userList";
    }

    // 회원 탈퇴(삭제)
    @PostMapping("/admin/delete")
    public String userDelete(@RequestParam("selectedIds") List<Long> selectedIds) {
        userService.deleteByIdIn(selectedIds);
        return "redirect:/admin/userList";
    }
	
	/**
	 * 승현파트
	 * */
	@GetMapping("/")
	public String indexForm() {
		return "index";
	}

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { // Data를 리턴해주는 컨트롤러 함수

		// POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
		// Retrofit2
		// OkHttp
		// RestTemplate

		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "0c7a67f1f4fcb8912b7d917baa9f87ee");
		params.add("redirect_uri", "http://localhost:9999/auth/kakao/callback");
		params.add("code", code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("카카오 엑세스 토큰 : "+oauthToken.getAccess_token());

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
				new HttpEntity<>(headers2);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
		);
		System.out.println(response2.getBody());

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// User 오브젝트 : username, password, email
		System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
		System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());

		System.out.println("블로그서버 유저네임 : "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("블로그서버 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		UUID garbagePassword = UUID.randomUUID();
		// UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
		System.out.println("블로그서버 패스워드 : "+cosKey);

		User kakaoUser = User.builder()
				.email(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();

		// 가입자 혹은 비가입자 체크 해서 처리
		User originUser = userService.회원찾기(kakaoUser.getEmail());

		if(originUser.getEmail() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
			userService.회원가입(kakaoUser);
		}

		System.out.println("자동 로그인을 진행합니다.");
		// 로그인 처리
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getEmail(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";
	}

	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		return "user/updateForm";
	}

	/*
	 * @RequestMapping(value="/logout1" ) public String access(HttpSession session)
	 * throws IOException {
	 *
	 * String access_token = (String)session.getAttribute("access_token");
	 * System.out.println("access_token:"+access_token); Map<String, String> map =
	 * new HashMap<String, String>(); map.put("Authorization", "Bearer "+
	 * access_token);
	 *
	 * //String result = conn.HttpURLConnection
	 * ("https://kapi.kakao.com/v1/user/logout", map).toString();
	 * //System.out.println(result);
	 *
	 * return "redirect:/"; }
	 */

}
