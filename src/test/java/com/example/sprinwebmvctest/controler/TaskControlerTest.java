package com.example.sprinwebmvctest.controler;

import com.example.sprinwebmvctest.model.Task;
import com.example.sprinwebmvctest.repository.RepositoryTask;
import com.example.sprinwebmvctest.service.ServiceTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskControler.class)
//@SpringBootTest
//@AutoConfigureMockMvc
class TaskControlerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServiceTask serviceTask;
    @MockBean
    RepositoryTask repositoryTask;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void ShoudCreateTask() throws Exception {
        Task task = new Task("22",2);
       when(repositoryTask.save(task)).thenReturn(task);
        mockMvc.perform(post("/api/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(content().string("Успешно добавлен"));
    }
    @Test
    void ShoudfindAllTask() throws  Exception{
        List<Task> list = new ArrayList<>();
        list.add(new Task("22",2));
        list.add(new Task("tas",4));
        list.add(new Task("tas",4));
        when(serviceTask.findAll()).thenReturn(list);
        mockMvc.perform(get("/api/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].name").value("tass"));

    }
    @Test
    void ShoudFindById() throws Exception {
        Task task = new Task("44",44);
        int id = 2;
        when(serviceTask.findById(id)).thenReturn(task);
        mockMvc.perform(get("/api/findById/"+id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(task)))
                .andExpect(jsonPath("$.grade").value(44));
    }
    @Test
    void ShoudUpdate() throws Exception {
        int id = 2;
        Task task = new Task("44",44);
        Task task1 = new Task("441",441);
        when(serviceTask.update(id,task)).thenReturn(task1);
        mockMvc.perform(put("/api/update/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("пользователь обновлен"))
                .andDo(print());
    }
    @Test
    void ShoudDelete() throws Exception {
        int id = 1;
        Mockito.doNothing().when(serviceTask).delete(id);
        mockMvc.perform(delete("/api/delete/"+id))
                .andExpect(status().isAccepted());
        Mockito.verify(serviceTask).delete(1);
    }
    @Test
    void ShoudGrade() throws Exception {
        Task task = new Task("44",4);
        when(serviceTask.getGrade()).thenReturn(task);
        mockMvc.perform(get("/api/getGrade")).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(task)))
                .andExpect(jsonPath("$.grade").value(4))
                .andExpect(jsonPath("$.name").value("44"));
    }

}