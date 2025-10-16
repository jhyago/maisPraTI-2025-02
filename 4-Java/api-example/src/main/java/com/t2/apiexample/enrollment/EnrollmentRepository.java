package com.t2.apiexample.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByCourseIdAndUserId(Long userId, Long courseId);

    List<Enrollment> findByUserId(Long userId);
    List<Enrollment> findByCourseId(Long courseId);

    @Query(value = "SELECT e.user_id FROM enrollments e WHERE e.course_id = :courseId ORDER BY e.enrolled_at ASC", nativeQuery = true)
    List<Long> findUserIdsByCourseNative(@Param("courseId") Long courseId);

    @Query(value = "SELECT COUNT(*) FROM enrollments e WHERE e.course_id = :courseId", nativeQuery = true)
    long countByCourseIdNative(@Param("courseId") Long courseId);
}
