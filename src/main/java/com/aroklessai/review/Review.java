package com.aroklessai.review;

import com.aroklessai.core.BaseEntity;
import com.aroklessai.course.Course;
import com.aroklessai.user.User;

import javax.persistence.*;

@Entity
public class Review extends BaseEntity{

    private int rating;
    private String description;

    @ManyToOne
    private Course course;

    @ManyToOne
    private User reviewer;


    // TODO : csd - We are duplicating code here for every single entity. Share it?
    protected Review() {
        super();
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Review(int rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
