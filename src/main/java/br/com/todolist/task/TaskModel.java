package br.com.todolist.task;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;


@Data
@Entity(name = "tasks")
public class TaskModel {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  
  @Column(length = 50)
  private String title; 

  @Column(length = 10)
  private String priority; 

  @CreationTimestamp
  private LocalDateTime createdAt;
}
