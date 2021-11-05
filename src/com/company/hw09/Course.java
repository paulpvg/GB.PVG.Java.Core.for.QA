package com.company.hw09;

import java.util.Objects;

public class Course {

    private String courseTitle;
    public Course(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return "курс " + courseTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseTitle, course.courseTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseTitle);
    }

    public String getCourseTitle() {
        return courseTitle;
    }
}
