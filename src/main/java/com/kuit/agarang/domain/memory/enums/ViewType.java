package com.kuit.agarang.domain.memory.enums;

public enum ViewType {
  CARD("card"),
  BOOKMARK("bookmark"),
  MONTHLY("monthly"),
  DAILY("daily");

  private String value;

  ViewType(String value) {
    this.value = value;
  }

  public static ViewType findBy(String viewType) {
    for (ViewType view : ViewType.values()) {
      if (view.value.equals(viewType)) {
        return view;
      }
    }
    return null;
  }
}
