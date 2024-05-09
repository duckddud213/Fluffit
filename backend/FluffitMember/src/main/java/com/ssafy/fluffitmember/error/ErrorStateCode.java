package com.ssafy.fluffitmember.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStateCode {

    UNAUTHORIXED("401", "권한이 없습니다"),
    ACCESS_TOKEN_EXPIRE("403", "ACCESS 토큰 만료"),
    REFRESH_TOKEN_EXPIRE("404", "REFRESH 토큰 만료"),
    REDIS("408", "캐시 서버에 문제가 있습니다"),
    RUNTIME("500", "서버 에러"),
    TOKEN_INVALID("400", "토큰 형식이 잘못되었습니다"),
    TOKEN_UNAUTH("402", "토큰이 일치하지 않습니다"),
    GATEWAY("503", "게이트 웨이 요청에 문제가 있습니다"),
    SUCCESS("200", "성공"),
    NO_SUCH_ALGORITHM("4001","요청된 암호화 알고리즘이 시스템에서 지원되지 않거나 존재하지 않습니다."),
    INVALID_HASH_KEY("4002","제공된 키가 암호화 알고리즘에 부적합하거나 규격에 맞지 않습니다"),
    ENCRYPTION_MISMATCH("4003","암호화된 데이터가 서로 다릅니다"),

    ;
    private final String code;
    private final String message;


}