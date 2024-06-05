package com.example.sprinwebmvctest.service;

import com.example.sprinwebmvctest.model.Task;
import com.example.sprinwebmvctest.repository.RepositoryTask;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceTask {
    private final RepositoryTask repositoryTask;

    public List<Task> findAll() {
        return repositoryTask.findAll();

    }

    public Task findById(int id) {
        Optional<Task> byId = repositoryTask.findById(id);
        if (byId.isPresent()) {
            return repositoryTask.findById(id).get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Task update(int id, Task task) {
        Optional<Task> byId = repositoryTask.findById(id);

        if (byId.isPresent()) {
            byId.get().setGrade(task.getGrade());
            byId.get().setName(task.getName());
            return repositoryTask.save(byId.get());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void delete(int id) {
        if (repositoryTask.existsById(id)) {
            try {
                repositoryTask.deleteById(id);
            } catch (Exception exception) {

            }
        } else { throw new  IllegalArgumentException();
    }}


    public Task save(Task task) {
        return repositoryTask.save(task);

    }

    public Task getGrade() {
        return repositoryTask.getGrade();
    }

    public Double getAVG() {
        return repositoryTask.getAVG();
    }

    public Integer getSum() {
        return repositoryTask.getSum();
    }

    public List<Task> getSumByParametr(int grade) {
        return repositoryTask.getSumByParametr(grade);
    }

    public Integer getMinOrMax(String m) {
        if (m.equalsIgnoreCase("max")){
            return repositoryTask.getMax();
        }else if( m.equalsIgnoreCase("min")){
            return repositoryTask.getMin();
        } else throw new IllegalArgumentException();
    }


}
