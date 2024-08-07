package com.kuit.agarang.domain.ai.utils;

import com.kuit.agarang.domain.ai.model.dto.MusicInfo;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTImageDescription;
import com.kuit.agarang.domain.baby.model.entity.Character;
import org.springframework.stereotype.Component;

@Component
public class GPTPromptUtil {

  public String createImageDescriptionPrompt() {
    return new StringBuilder("이미지를 한국어로 하나의 문장으로 감정적으로 묘사해줘. ")
      .append("text는 하나의 문장으로 묘사된 내용을 포함하고, ")
      .append("noun는 해당 이미지를 기반으로 인스타 태그로 사용할 수 있는 명사나 형용사를 의미가 중복되지 않게 최대 3개 포함해줘. ")
      .append("출력은 json 파일 형태로 해줘. ").toString();
  }

  public String createImageQuestionPrompt(GPTImageDescription imageDescription) {
    String imageDescriptionKeyword = imageDescription.getNoun().toString();
    return new StringBuilder(imageDescriptionKeyword)
      .append(", 키워드 중 하나를 선택해서 사용자에게 오늘의 주제에 대해서 말해주고, ")
      .append("사용자의 오늘 추억을 회상시킬 수 있는 질문을 한 문장으로 짧게 자연스럽게 작성해줘. ")
      .append("말투는 아이가 부모님에게 묻는 말투로 해줘. ").toString();
  }

  public String createMemoryTextPrompt(String babyName, String familyRole) {
    return new StringBuilder("위의 대화는 오늘 ").append(familyRole).append("에게 있었던 일이야. ")
      .append(familyRole).append("에게 있었던 일을 기반으로 ")
      .append(familyRole).append("가 태아인 ").append(babyName).append("에게 편지를 작성할거야. ")
      .append("위의 대화를 바탕으로 일상 태담을 5문장 정도로 감성적으로 작성해줘.").toString();
  }

  public String createCharacterBubble(Character character, String familyRole) {
    return new StringBuilder("귀여운 아기 ").append(character.getName()).append(" 캐릭터 특징이나 울음소리, 외모 등을 기반으로 맞춰서 ")
      .append(familyRole).append("에게 하고 싶은 말을 10자 내로 짧게 작성해줘. ")
      .append(character.getName()).append("의 특징은 다음과 같아. ").append(character.getDescription()).toString();
  }

  public String createMusicGenPrompt(GPTImageDescription imageDescription, MusicInfo musicInfo) {
    return new StringBuilder("text to music 이란, 장르, 악기, 무드, 속도를 포함한 prompt 를 입력으로 Music 을 생성하는 task를 의미해. ")
      .append("너는 지금부터, 아래의 내 요구사항을 반드시 지켜서 music 생성을 위한 prompt 를 만들어야돼. ")
      .append("Music Generation 을 위한 Text Prompt 예시는 다음과 같아.\n")
      .append("1. Pop dance track with catchy melodies, tropical percussion, and upbeat rhythms, perfect for the beach.\n")
      .append("2. A grand orchestral arrangement with thunderous percussion, epic brass fanfares, and soaring strings, creating a cinematic atmosphere fit for a heroic battle.\n")
      .append("3. earthy tones, environmentally conscious, ukulele-infused, harmonic, breezy, easygoing, organic instrumentation, gentle grooves.\n")
      .append(musicInfo.getGenreAsString()).append("와 ").append(imageDescription.getText()).append(" 에 어울리고, ")
      .append(musicInfo.getInstrumentAsString()).append("로 연주되며, ").append(musicInfo.getMoodAsString()).append("이 잘 표현되는 prompt를 만들어줘. ")
      .append("반드시 json 형식으로 알려줘. ").append("{\"prompt\": prompt}").toString();
  }

  public String createMusicTitlePrompt(String musicGenPrompt, MusicInfo musicInfo) {
    return new StringBuilder("산모들의 태교 노래 제목를 만들어줘. ").append("태교 노래 제목의 예시는 다음과 같아.\n")
      .append("1. 봄마중 꽃마중, ").append("2. 찬 바람이 불던 밤, ").append("3. 깊은 밤을 날아서\n")
      .append(musicGenPrompt).append(" 이미지가 떠오를 수 있으면서, ")
      .append(musicInfo.getMoodAsString()).append(" 분위기가 잘 표현되는 한국어로 된 태교 음악 제목을 만들어줘. ")
      .append("반드시 json 형식으로 알려줘. ").append("{\"music_name\": music_name}").toString();
  }
}
