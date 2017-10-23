package edu.mumsched.task;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    private void init() {
        taskRepository.save(new Task("System User", "Manage system user- add, remove, update, delete", "2017.10.18 13:22:42"));
        taskRepository.save(new Task("Entry", "Manage entry-add, remove, update, delete", "2017.10.18 15:22:42"));
    }

    Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findOne(Long id) {
        return taskRepository.findOne(id);
    }
}
