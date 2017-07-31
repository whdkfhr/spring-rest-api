package com.aroklessai.core;


import com.aroklessai.course.Course;
import com.aroklessai.course.CourseRepository;
import com.aroklessai.review.Review;
import com.aroklessai.user.User;
import com.aroklessai.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner{
    private final CourseRepository courses;
    private final UserRepository users;


    @Autowired
    public DatabaseLoader(CourseRepository courses, UserRepository users) {
        this.courses = courses;
        this.users = users;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basis", "http://teamtreehouse.com/library/java-basis");
        course.addReview(new Review(3, "You are a dork!!!"));
        courses.save(course);
        String[] templates = {
                "Up and Running with %s",
                "%s Bsics",
                "%s for Beginners",
                "Under the hood : %s"
        };
        String[] buzzwords = {
                "Spring REST Data",
                "Java 9",
                "Scala",
                "Groovy",
                "Hibernate",
                "Spring HATEOAS"
        };

        List<User> students = Arrays.asList(
                new User("arok", "jo", "student", "Password", new String[] {"ROLE_USER"}),
                new User("tw", "k", "chuijoon", "Password", new String[] {"ROLE_USER"}),
                new User("js", "k", "backsoo", "Password", new String[] {"ROLE_USER"})
        );

        users.save(students);
        users.save(new User("craigsdennis","raig", "Dennis", "12345", new String[] {"ROLE_USER", "ROLE_ADMIN"}));

        List<Course> bunchOfCourses = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i-> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www.example.com");
                    Review review = new Review((i % 5) + 1, String.format("More %s please!!", buzzword));
                    review.setReviewer(students.get(i % students.size()));
                    c.addReview(review);
                    bunchOfCourses.add(c);
                });
        courses.save(bunchOfCourses);
    }
}
