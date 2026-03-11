package br.com.todolist.shared;

import lombok.Data;

@Data
public class ApiNoDataResponse {
  String message; 

  public ApiNoDataResponse(String message) {
    this.message = message;
  }
}
  
