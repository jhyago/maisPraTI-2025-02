package com.t2.apiexample.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where lower(c.name) like lower(concat('%', :name, '%')) order by c.name asc")
    List<Course> findByNameContainingIgnoreCase(@Param("name") String name);
}
