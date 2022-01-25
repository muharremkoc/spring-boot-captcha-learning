package com.spring.captchalearn.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue
    int id;

    String name;

    String email;

    @Transient
    String captcha;

    @Transient
    String hiddenCaptcha;

    @Transient
    String realCaptcha;

}
