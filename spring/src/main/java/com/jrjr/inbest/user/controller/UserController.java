package com.jrjr.inbest.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jrjr.inbest.jwt.service.JwtProvider;
import com.jrjr.inbest.user.dto.JoinDto;
import com.jrjr.inbest.user.dto.UserDto;
import com.jrjr.inbest.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "회원가입/마이페이지", description = "회원 API")
public class UserController {

	private final UserService userService;
	private final JwtProvider jwtProvider;

	@Operation(summary = "회원가입",
		description = "필수 값: email, password, name, nickname 선택 값: birth, gender")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200")
	})
	@PostMapping("")
	ResponseEntity<Map<String, Object>> join(@RequestBody JoinDto joinDto) {
		log.info("UserController - join 실행: {}", joinDto.getEmail());
		Map<String, Object> resultMap = new HashMap<>();

		userService.join(joinDto);

		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Operation(summary = "이메일 존재 유무 확인")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "존재하는 이메일"),
		@ApiResponse(responseCode = "404", description = "존재하지 않는 이메일")
	})
	@GetMapping("/inquiry-email")
	ResponseEntity<Map<String, Object>> checkEmailExists(@RequestParam String email) {
		log.info("UserController - checkEmailExists 실행: {}", email);
		Map<String, Object> resultMap = new HashMap<>();

		userService.checkEmailExists(email);

		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Operation(summary = "닉네임 존재 유무 확인")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "존재하는 닉네임"),
		@ApiResponse(responseCode = "404", description = "존재하지 않는 닉네임")
	})
	@GetMapping("/inquiry-nickname")
	ResponseEntity<Map<String, Object>> checkNicknameExists(@RequestParam String nickname) {
		log.info("UserController - checkNicknameExists 실행: {}", nickname);
		Map<String, Object> resultMap = new HashMap<>();

		userService.checkNicknameExists(nickname);

		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Operation(summary = "비밀번호 변경", description = "필수 값: password")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "401", description = "회원 정보 없음, 토큰의 이메일과 비밀번호를 변경하려는 계정의 이메일 불일치")
	})
	@PutMapping("/{seq}/password")
	ResponseEntity<Map<String, Object>> updatePassword(@PathVariable(value = "seq") Long seq,
		@RequestBody Map<String, String> passwordMap,
		HttpServletRequest request) {
		log.info("UserController - updatePassword 실행: {}", seq);
		Map<String, Object> resultMap = new HashMap<>();

		Optional<String> accessToken = jwtProvider.resolveAccessToken(request);
		String email = jwtProvider.getUserInfoFromToken(accessToken.orElse("accessToken")).getEmail();
		userService.updatePassword(seq, email, passwordMap.get("password"));

		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Operation(summary = "회원 탈퇴")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "401", description = "회원 정보 없음, 토큰의 이메일과 탈퇴하려는 계정의 이메일 불일치")
	})
	@DeleteMapping("/{seq}")
	ResponseEntity<Map<String, Object>> withdraw(@PathVariable(value = "seq") Long seq,
		HttpServletRequest request) {
		log.info("UserController - withdraw 실행: {}", seq);
		Map<String, Object> resultMap = new HashMap<>();

		Optional<String> accessToken = jwtProvider.resolveAccessToken(request);
		String email = jwtProvider.getUserInfoFromToken(accessToken.orElse("accessToken")).getEmail();
		userService.withdraw(seq, email);

		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Operation(summary = "회원 정보 조회")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "반환: seq, email, name, nickname, birth, gender, profileImgSearchName"),
		@ApiResponse(responseCode = "404", description = "조회 회원 정보 없음")
	})
	@GetMapping("/{seq}")
	ResponseEntity<Map<String, Object>> getProfile(@PathVariable(value = "seq") Long seq) {
		log.info("UserController - getProfile 실행: {}", seq);
		Map<String, Object> resultMap = new HashMap<>();

		UserDto userInfo = userService.getUserInfo(seq);

		resultMap.put("UserInfo", userInfo);
		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Operation(summary = "프로필 이미지: 기본 이미지로 변경")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "401", description = "회원 정보 없음, 토큰의 이메일과 정보를 변경하려는 계정의 이메일 불일치")
	})
	@PutMapping("/{seq}/img")
	ResponseEntity<Map<String, Object>> updateProfileDefaultImg(@PathVariable(value = "seq") Long seq,
		HttpServletRequest request) {
		log.info("UserController - updateProfileDefaultImg 실행: {}", seq);
		Map<String, Object> resultMap = new HashMap<>();

		Optional<String> accessToken = jwtProvider.resolveAccessToken(request);
		String email = jwtProvider.getUserInfoFromToken(accessToken.orElse("accessToken")).getEmail();
		userService.updateDefaultImg(seq, email);

		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Operation(summary = "프로필 정보 업데이트",
		description = "필수 값: name, nickname 선택 값: birth, gender, MultipartFile")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "반환: seq, email, name, nickname, birth, gender, profileImgSearchName"),
		@ApiResponse(responseCode = "401", description = "회원 정보 없음, 토큰의 이메일과 정보를 변경하려는 계정의 이메일 불일치")
	})
	@PutMapping("/{seq}")
	ResponseEntity<Map<String, Object>> updateProfile(@PathVariable(value = "seq") Long seq,
		@RequestParam(value = "file", required = false) MultipartFile file,
		@ModelAttribute UserDto userDto,
		HttpServletRequest request) throws IOException {
		log.info("UserController - updateProfile 실행: {}", seq);
		Map<String, Object> resultMap = new HashMap<>();

		Optional<String> accessToken = jwtProvider.resolveAccessToken(request);
		String email = jwtProvider.getUserInfoFromToken(accessToken.orElse("accessToken")).getEmail();
		UserDto userInfo = userService.updateUserInfo(seq, file, userDto, email);

		resultMap.put("UserInfo", userInfo);
		resultMap.put("success", true);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}