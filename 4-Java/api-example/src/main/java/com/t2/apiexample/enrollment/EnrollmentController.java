package com.t2.apiexample.enrollment;

import com.t2.apiexample.course.Course;
import com.t2.apiexample.course.CourseRepository;
import com.t2.apiexample.enrollment.dto.EnrollmentRequest;
import com.t2.apiexample.user.User;
import com.t2.apiexample.user.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Enrollments")
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentController(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@Valid @RequestBody EnrollmentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(null);
        if(user == null) return ResponseEntity.badRequest().body((Enrollment) Map.of("error", "Usuário não encontrado"));
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(null);
        if(course == null) return ResponseEntity.badRequest().body((Enrollment) Map.of("error", "Curso não encontrado"));

        if(enrollmentRepository.existsByCourseIdAndUserId(user.getId(), course.getId())) {
            return ResponseEntity.status(409).body((Enrollment) Map.of("error", "Aluno já está matriculadi"));
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setUser(user);
        Enrollment saved = enrollmentRepository.save(enrollment);

        return ResponseEntity.created(URI.create("/enrollments/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Enrollment> listAll() { return enrollmentRepository.findAll();}

    @GetMapping("/by-course/{courseId}/users")
    public Map<String, Object> listUserIdsByCourseNative(@PathVariable Long courseId) {
        List<Long> userIds = enrollmentRepository.findUserIdsByCourseNative(courseId);
        Long count = enrollmentRepository.countByCourseIdNative(courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("courseId", courseId);
        response.put("userIds", userIds);
        response.put("count", count);

        return response;
    }
}
