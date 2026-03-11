package br.com.todolist.task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolist.shared.ApiDataResponse;
import br.com.todolist.shared.ApiNoDataResponse;
import br.com.todolist.shared.ApiResponse;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/tasks")
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("")
  public ResponseEntity<Object> findAll() {
    var tasks = taskRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(new ApiDataResponse(tasks));
  }  

  @GetMapping("/{id}")
  public ResponseEntity<Object> findById(@PathVariable UUID id) {
    var task = taskRepository.findById(id);

    if(task.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiNoDataResponse("Task não encontrada."));
    }
    return ResponseEntity.status(HttpStatus.OK).body(new ApiDataResponse(task));
  }
  
  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody CreateTaskRequest body) {
    var newTask = new TaskModel();

    newTask.setTitle(body.getTitle());
    newTask.setPriority(body.getPriority());

    var task = taskRepository.save(newTask);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(task, "Task criada com sucesso.", true));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody CreateTaskRequest body) {
    var task = taskRepository.findById(id);

    if(task.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiNoDataResponse("Task não encontrada."));
    }

    task.get().setTitle(body.getTitle());
    task.get().setPriority(body.getPriority());

    var updatedTask = taskRepository.save(task.get());
    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(updatedTask, "Task atualizada com sucesso.", true));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) {
    var task = taskRepository.findById(id); 

    if(task.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiNoDataResponse("Task não encontrada."));
    }

    taskRepository.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(new ApiNoDataResponse("Task excluída com sucesso."));
  }
  
}
