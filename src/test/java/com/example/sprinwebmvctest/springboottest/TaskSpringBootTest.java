package com.example.sprinwebmvctest.springboottest;

import com.example.sprinwebmvctest.model.Task;
import com.example.sprinwebmvctest.repository.RepositoryTask;
import com.example.sprinwebmvctest.service.ServiceTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskSpringBootTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ServiceTask serviceTask;
    @Autowired
    private RepositoryTask repositoryTask;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ShoudreturnFindAll() throws Exception {
        Task task = new Task("oleg", 4);
        Task task1 = new Task("ivan", 2);
        repositoryTask.save(task);
        repositoryTask.save(task1);
        mockMvc.perform(get("/api/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[4].name").value("ivan"));
    }

    @Test
    void ShoudReturnFindById() throws Exception {
        int id = 2;
        mockMvc.perform(get("/api/findById/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Victor"));
    }

    @Test
    void ShoudUpdate() throws Exception {
        int id = 2;
        Task task = new Task("44", 4);
        mockMvc.perform(put("/api/update/" + id)
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(task)))
                .andExpect(content().string("пользователь обновлен")).andExpect(status().isAccepted());


    }
    @Test
    void ShoudDelete() throws Exception {
        int id = 2;
        mockMvc.perform(delete("/api/delete/"+id)).andExpect(status().isAccepted());
    }
    @Test
    void shoudMaxGrade() throws Exception {
        mockMvc.perform(get("/api/getGrade"))
                .andExpect(jsonPath("$.grade").value(7))
                .andExpect(jsonPath("$.name").value("44"))
                .andExpect(status().isOk());
    }
    @Test
    void shoudGetAVGGrade() throws  Exception{
        mockMvc.perform(get("/api/getAVGGrade"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("4.25"));
    }
    @Test
    void shoudGetSumGrade() throws  Exception{
        serviceTask.save(new Task("44",100));
        mockMvc.perform(get("/api/getSum")).andExpect(status().isAccepted()).andExpect(content().string("134"));
    }
    @Test
    void shoudGetTaskByParametr() throws  Exception{
        int grade =5;
        mockMvc.perform(get("/api/getSumByParametr?grade="+grade))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("sSa"))
                .andExpect(jsonPath("$[0].grade").value("4"));
    }
    @Test
    void shoudGetMinOrMaxGrade() throws  Exception{
        String m = "min";
        mockMvc.perform(get("/api/getMinOrMaxGrade?m="+m)).andExpect(status().isOk()).andExpect(content().string("2"));
    }
}
