package com.kuit.agarang.global.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeUtilTest {

  @Test
  public void 랜덤코드생성() throws Exception {

    // given, when
    String uniqueCode = CodeUtil.generateUniqueCode();

    // then
    System.out.println("uniqueCode = " + uniqueCode);
    assertNotNull(uniqueCode);
    assertEquals(6, uniqueCode.length());
    assertTrue(uniqueCode.matches("[A-Z0-9]+"));

  }
}