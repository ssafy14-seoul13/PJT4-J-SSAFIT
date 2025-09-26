package com.ssafy.ssafit.util;

import java.util.UUID;

/*
 * 고유 id 생성기.
 * 현재 버전은 중복id가 발생할 수 있다.
 * 추후 파라미터로 (users, videos) 같은것을 함께 넘겨 충돌 검사 로직을 추가 할 것
 */
public class IdGenerator {
	private IdGenerator() {}
	
	 /** 32자리 소문자 hex (URL/file-safe). 예) "3fa85f64b3d14f1fb1c27e6d2a88d1a2" */
    public static String newId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
