package com.sp.file.type;

public enum Status {
 INSERT,UPDATE,DELETE
}
/*Enum은 static final 하다.
계승(상속)이 불가능하다. 
컴파일 타임, 타입 세이프하다.
그룹핑이 가능하다(Enum의 Enum을 하여 조합 가능).
Object를 계상받아 Object에서 제공하는 메소드를 활용할 수 있다. 또는 디폴트 메소드를 사용할 수 있다.
serializable, comparable이 가능하다.
메소드를 사용하여 기능 확장이 무궁무진하다(상수 + 관련 데이터의 연계 및 연산을 사용할 수 있다.).
비교 연산은 Int 상수와 성능이 비슷하다.*/