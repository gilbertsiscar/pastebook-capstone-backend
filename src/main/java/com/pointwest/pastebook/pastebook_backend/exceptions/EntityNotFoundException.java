package com.pointwest.pastebook.pastebook_backend.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(Class<?> obj, String param, String value) {
    super(String.format("%s with {%s=%s} not found", obj.getSimpleName(), param, value));
  }

  public EntityNotFoundException(Class<?> obj, Long id) {
    super(String.format("%s with {id=%s} not found", obj.getSimpleName(), id));
  }
}