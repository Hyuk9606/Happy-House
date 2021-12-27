package com.ssafy.happyhouse.vue.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.vue.model.BoardDto;
import com.ssafy.happyhouse.vue.model.BoardParameterDto;
import com.ssafy.happyhouse.vue.model.User;
import com.ssafy.happyhouse.vue.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.vue.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/user")
@Api("유저 컨트롤러  API V1")
public class UserController {

	@Autowired
	UserService service;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
//	@GetMapping("/login")
//	public String goLoginPage() {
//		return "user/login";
//	}
//
//	@PostMapping("/login")
//	public String doLogin(User user, HttpSession session, Model model) throws SQLException {
//		User loginUser = service.login(user.getUserId(), user.getUserPass());
//		if (loginUser != null) {
//			session.setAttribute("loginuser", loginUser);
//			return "index";
//		}
//		model.addAttribute("msg","로그인 실패");
//		return "redirect:/";
//	}
	
	@ApiOperation(value = "아이디", notes = "아이디 중복 확인", response = BoardDto.class)
	@GetMapping("/check/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") @ApiParam(value = "유저 아이디", required = true) String userId) throws Exception {
		return new ResponseEntity<User>(service.userSelect(userId), HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.removeAttribute("loginuser");
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String goRegisterPage() {
		return "user/register";
	}
	
//	@PostMapping("/register")
//	@Transactional
//	public String doRegist(User user) {
//		service.userInsert(user);
//		return "redirect:/";
//	}
	
	@ApiOperation(value = "회원 가입", notes = "회원가입한다")
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @ApiParam(value = "회원 가입", required = true) User user) {
		if (service.userInsert(user) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/mypage")
	public String goMyPage() {
		return "user/mypage";
	}
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = service.login(user);
			if (loginUser != null) {
				String token = jwtService.create("userId", loginUser.getUserId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = service.userInfo(userId);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "유저정보 수정", notes = "유저정보 수정 성공시 success, 실패시 fail", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyUser(@RequestBody @ApiParam(value = "수정할 유저", required = true) User user) throws Exception {
		logger.info("updateuser - 호출");
		logger.info("user: {}", user);
		
		if (service.userUpdate(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "비밀번호 찾기", notes = "비밀번호 수정 성공시 success, 실패시 fail", response = String.class)
	@PutMapping("/pass")
	public ResponseEntity<String> FindPass(@RequestBody @ApiParam(value = "비밀번호 찾기", required = true) User user) throws Exception {
		logger.info("findPass - 호출");
		logger.info("user: {}", user);
		
		if (service.findPass(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저 삭제", notes = "유저 삭제 성공시  success, 실패시 fail ", response = String.class)
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") @ApiParam(value = "삭제할 유저.", required = true) String userId) throws Exception {
		logger.info("deleteuser - 호출");
		if (service.userDelete(userId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
