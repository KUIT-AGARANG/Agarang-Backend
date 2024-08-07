package com.kuit.agarang.global.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Set;

public class CodeUtil {

  private static final Set<String> usedCodes = new HashSet<>();

  public static String generateUniqueCode() {
    String code;
    do {
      code = generateRandomCode();
    } while (usedCodes.contains(code));
    usedCodes.add(code);
    return code;
  }

  private static String generateRandomCode() {
    return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
  }

}
