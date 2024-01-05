package org.luvx.boot.cloud.service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User {
    private Long          id;
    private String        userName;
    private String        password;
    private Integer       age;
    private LocalDateTime birthday;
    private LocalDateTime updateTime;
}
