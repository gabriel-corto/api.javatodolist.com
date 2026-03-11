package br.com.todolist.shared;

import lombok.Data;

@Data
public class ApiDataResponse {
  Object data; 

  public ApiDataResponse(Object data) {
    this.data = data;
  }
}
