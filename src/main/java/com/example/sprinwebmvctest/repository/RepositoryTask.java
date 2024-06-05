package com.example.sprinwebmvctest.repository;

import com.example.sprinwebmvctest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryTask extends JpaRepository<Task,Integer> {

    @Query(value = "SELECT * from task order by grade desc limit 1",nativeQuery = true)
    Task getGrade();
    @Query(value = "SELECT avg (grade) from  task",nativeQuery = true)
    Double getAVG();
    @Query(value = "select sum (grade) from  task",nativeQuery = true)
    Integer getSum();
    @Query(value = "select * from  task where grade <:grade",nativeQuery = true)
    List<Task> getSumByParametr(@Param("grade") int grade);

    @Query(value = "SELECT max (grade) from  task",nativeQuery = true)
    Integer getMax();
    @Query(value = "SELECT min (grade) from  task",nativeQuery = true)
    Integer getMin();


}
