package com.newperson.springbootsecurity.demo.user.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/25 17:03
 */
@Data
public class User implements Serializable {

    @NotNull(message = "id不能为空")
    private Long  id;

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须为过去的时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Email(message = "邮箱格式不正确")
    private String email;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public User(@NotNull(message = "id不能为空") Long id, @NotBlank(message = "用户名不能为空") String username, @NotBlank(message = "密码不能为空") String password, @Past(message = "生日必须为过去的时间") LocalDate birthday, @Email(message = "邮箱格式不正确") String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
    }
}
