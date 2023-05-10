package com.kh.learnovation.domain.cart.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kh.learnovation.domain.course.entity.Course;
import com.kh.learnovation.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class CartPk implements Serializable {

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "course_id")
    @ManyToOne
    @JsonIgnore
    private Course course;

    @Builder
    public CartPk(User user, Course course) {
        this.user = user;
        this.course = course;
    }
}
