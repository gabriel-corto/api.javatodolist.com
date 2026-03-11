package br.com.todolist.shared;

import lombok.Data;

@Data
public class ApiResponse {
  Object data; 
  String message;
  boolean success;

  public ApiResponse(Object data, String message, boolean success) {
    this.data = data;
    this.message = message;
    this.success = success;
  }
}