package com.pointwest.pastebook.pastebook_backend.exceptions;

public class EntityDuplicateException extends RuntimeException {
  public EntityDuplicateException(String message) {
    super(message);
  }

  public EntityDuplicateException(Class<?> obj, String param, String value) {
    super(String.format("%s with {%s=%s} already exists", obj.getSimpleName(), param, value));
  }
}