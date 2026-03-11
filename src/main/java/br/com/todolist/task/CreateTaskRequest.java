package br.com.todolist.task;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CreateTaskRequest {
  @NotNull(message = "O Título é obrigatório")
  @NotBlank(message = "O Título não pode ser vazio")
  String title;

  @NotNull(message = "A Prioridade é obrigatória")
  @NotBlank(message = "A Prioridade não pode ser vazia")
  String priority;
}
