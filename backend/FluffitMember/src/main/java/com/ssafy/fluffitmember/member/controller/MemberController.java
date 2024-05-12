package com.ssafy.fluffitmember.member.controller;

import com.ssafy.fluffitmember._common.error.ErrorResponse;
import com.ssafy.fluffitmember._common.error.ErrorType;
import com.ssafy.fluffitmember._common.exception.DuplicateNickname;
import com.ssafy.fluffitmember._common.exception.NotFoundUserException;
import com.ssafy.fluffitmember._common.exception.NotValidNickname;
import com.ssafy.fluffitmember._common.exception.NotValidSQL;
import com.ssafy.fluffitmember._common.success.SuccessResponse;
import com.ssafy.fluffitmember._common.success.SuccessType;
import com.ssafy.fluffitmember.member.dto.Response.GetCoinResDto;
import com.ssafy.fluffitmember.member.dto.Request.UpdateNicknameReqDto;
import com.ssafy.fluffitmember.member.dto.Response.GetNicknameResDto;
import com.ssafy.fluffitmember.member.dto.Response.GetRankResDto;
import com.ssafy.fluffitmember.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/update-nickname")
    public ResponseEntity<Object> updateNickname(@RequestHeader("memberId") String memberId, @RequestBody UpdateNicknameReqDto updateNicknameReqDto){
        try {
            memberService.updateNickname(memberId,updateNicknameReqDto);
            return ResponseEntity.ok().body(SuccessResponse.from(SuccessType.UPDATE_USER_NICKNAME_SUCCESSFULLY));
        } catch (NotFoundUserException e){
            return ResponseEntity.badRequest().body(ErrorResponse.from(ErrorType.NOT_FOUND_MEMBER));
        } catch (NotValidNickname e) {
            return ResponseEntity.badRequest().body(ErrorResponse.from(ErrorType.NOT_VALID_NICKNAME));
        } catch (DuplicateNickname e) {
            return ResponseEntity.badRequest().body(ErrorResponse.from(ErrorType.DUPLICATE_NICKNAME));
        }
    }

    @GetMapping("/nickname")
    public ResponseEntity<Object> getNickname(@RequestHeader("memberId") String memberId){
        try {
            GetNicknameResDto getNicknameRes = memberService.getNickname(memberId);
            return ResponseEntity.ok().body(getNicknameRes);
        }catch (NotFoundUserException e){
            return ResponseEntity.badRequest().body(ErrorResponse.from(ErrorType.NOT_FOUND_MEMBER));
        }
    }

    @GetMapping("/login")
    public ResponseEntity<Object> autoLogin(){
        return ResponseEntity.ok().body(SuccessResponse.from(SuccessType.LOGIN_SUCCESSFULLY));
    }

    @GetMapping("/battle-rank")
    public ResponseEntity<Object> getRank(@RequestHeader("memberId") String memberId){
        try {
            GetRankResDto getRankResDto = memberService.getRank(memberId);
            return ResponseEntity.ok().body(getRankResDto);
        }catch (NotFoundUserException e){
            return ResponseEntity.badRequest().body(ErrorResponse.from(ErrorType.NOT_FOUND_MEMBER));
        }catch (NotValidSQL e){
            return ResponseEntity.badRequest().body(ErrorResponse.from(ErrorType.NOT_VALID_SQL));
        }
    }

    @GetMapping("/get-coin")
    public ResponseEntity<Object> getUserCoin(@RequestHeader("memberId") String memberId){
        GetCoinResDto getRankResDto = memberService.getUserCoin(memberId);
        return ResponseEntity.ok().body(getRankResDto);
    }
}
