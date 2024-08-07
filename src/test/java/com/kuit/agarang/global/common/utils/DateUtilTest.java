package com.kuit.agarang.global.common.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

  @Test
  public void convertStringToLocalDate_테스트() throws Exception {
      //given
    String date = "20240701";
      //when
    LocalDate localDate = DateUtil.convertStringToLocalDate(date);

    //then
    Assertions.assertThat(localDate)
            .isNotNull();
    Assertions.assertThat(localDate.getYear())
            .isEqualTo(2024);
    Assertions.assertThat(localDate.getMonthValue())
            .isEqualTo(7);
  }
}