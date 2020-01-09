package com.example.demo.test;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    public List<Todo> findById(int id);

    @Query("select t from Todo t where t.content like ?1%")
    public List<Todo> findByLikeName(String name);

}