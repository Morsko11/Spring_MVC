package com.example.sprinwebmvctest.controler;

import com.example.sprinwebmvctest.dto.DtoTask;
import com.example.sprinwebmvctest.model.Task;
import com.example.sprinwebmvctest.repository.RepositoryTask;
import com.example.sprinwebmvctest.service.ServiceTask;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskControler {
    private final ServiceTask serviceTask;

    @GetMapping("/findAll")
    public ResponseEntity<List<Task>> findAll() {

        List<Task> all = serviceTask.findAll();
        return  ResponseEntity.ok(all);
    }

    @RequestMapping("/findById/{id}")
    public ResponseEntity<Task> findById(@PathVariable int id) {

        Task byId = serviceTask.findById(id);
        return ResponseEntity.ok(byId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Task task) {

        Task update = serviceTask.update(id, task);
        return ResponseEntity.accepted().body("пользователь обновлен");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
         serviceTask.delete(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Task task) {
        Task save = serviceTask.save(task);
        return ResponseEntity.ok("Успешно добавлен");
    }
    @GetMapping("/getGrade")
    public ResponseEntity<Task> getGrade(){
     Task task =  serviceTask.getGrade();
        return ResponseEntity.ok(task);
    }
    @GetMapping("/getAVGGrade")
    public ResponseEntity<Double> getAVG(){
        return new ResponseEntity<>(serviceTask.getAVG(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getSum")
    public ResponseEntity<Integer> getSum(){
        return new ResponseEntity<>(serviceTask.getSum(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getSumByParametr")
    public ResponseEntity<List<Task>> getSumByP(@RequestParam int grade){
        return ResponseEntity.ok(serviceTask.getSumByParametr(grade));
    }
    @GetMapping("/getMinOrMaxGrade")
    public ResponseEntity<Integer> getMinOrMax(@RequestParam String m){
        return ResponseEntity.ok(serviceTask.getMinOrMax(m));
    }


}
