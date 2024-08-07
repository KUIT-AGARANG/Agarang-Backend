package com.kuit.agarang.domain.ai.model.dto.gpt;

import com.kuit.agarang.domain.ai.model.enums.GPTRole;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GPTDtoTest {

  @Test
  void createDefaultRequest() {
    // given
    GPTMessage message1 = GPTMessage.builder()
      .role(GPTRole.SYSTEM)
      .content("You are a helpful assistant.")
      .build();
    GPTMessage message2 = GPTMessage.builder()
      .role(GPTRole.USER)
      .content("Hello!")
      .build();

    // when
    GPTRequest request = new GPTRequest(List.of(message1, message2));
    request.setRequiredJson(true);

    // then
    assertEquals("gpt-4o", request.getModel());
    assertEquals(0L, request.getTemperature());
    assertEquals("json_object", request.getResponseFormat().getType());

    assertEquals(message1.getRole(), request.getMessages().get(0).getRole());
    assertEquals(message1.getContent(), request.getMessages().get(0).getContent());
    assertEquals(message2.getRole(), request.getMessages().get(1).getRole());
    assertEquals(message2.getContent(), request.getMessages().get(1).getContent());
  }

  @Test
  void createImageRequest() {
    // given
    GPTContent content1 = GPTContent.createTextContent("이미지에 대해 설명해줘");
    GPTContent content2 = GPTContent.createImageContent("https://image.jpg");

    GPTMessage message = GPTMessage.builder()
      .role(GPTRole.USER)
      .content(List.of(content1, content2))
      .build();

    // when
    GPTRequest request = new GPTRequest(List.of(message));

    // then
    assertEquals("gpt-4o", request.getModel());
    assertEquals(0L, request.getTemperature());
    assertNull(request.getResponseFormat());

    assertEquals(message.getRole(), request.getMessages().get(0).getRole());

    List<GPTContent> requestContent = (List<GPTContent>) request.getMessages().get(0).getContent();
    assertEquals(content1.getType(), requestContent.get(0).getType());
    assertEquals(content1.getText(), requestContent.get(0).getText());
    assertNull(requestContent.get(0).getImageUrl());
    assertEquals(content2.getType(), requestContent.get(1).getType());
    assertEquals(content2.getImageUrl(), requestContent.get(1).getImageUrl());
    assertNull(requestContent.get(1).getText());
  }

  @Test
  void createCombinationRequest() {
    // given
    GPTMessage message1 = GPTMessage.builder()
      .role(GPTRole.SYSTEM)
      .content("You are a helpful assistant.")
      .build();

    GPTContent content1 = GPTContent.createTextContent("이미지에 대해 설명해줘");
    GPTContent content2 = GPTContent.createImageContent("https://image.jpg");
    GPTMessage message2 = GPTMessage.builder()
      .role(GPTRole.USER)
      .content(List.of(content1, content2))
      .build();

    // when
    GPTRequest request = new GPTRequest(List.of(message1, message2));
    request.setRequiredJson(true);

    // then
    assertEquals("gpt-4o", request.getModel());
    assertEquals(0L, request.getTemperature());
    assertEquals("json_object", request.getResponseFormat().getType());

    assertEquals(message1.getRole(), request.getMessages().get(0).getRole());
    assertEquals(message1.getContent(), request.getMessages().get(0).getContent());

    assertEquals(message2.getRole(), request.getMessages().get(1).getRole());

    List<GPTContent> requestContent = (List<GPTContent>) request.getMessages().get(1).getContent();
    assertEquals(content1.getType(), requestContent.get(0).getType());
    assertEquals(content1.getText(), requestContent.get(0).getText());
    assertNull(requestContent.get(0).getImageUrl());
    assertEquals(content2.getType(), requestContent.get(1).getType());
    assertEquals(content2.getImageUrl(), requestContent.get(1).getImageUrl());
    assertNull(requestContent.get(1).getText());
  }
}