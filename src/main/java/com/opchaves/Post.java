package com.opchaves;

public record Post(String title, String content) {
  Post() {
    this(null, null);
  }
}
