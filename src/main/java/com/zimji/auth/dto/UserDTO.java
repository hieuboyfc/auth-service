package com.zimji.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zimji.auth.dto.custom.BaseDTO;
import com.zimji.auth.validation.EmptyValid;
import com.zimji.auth.validation.LengthValid;
import com.zimji.auth.validation.PatternValid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends BaseDTO {

    @EmptyValid(message = "Username không được để trống!", errorCode = "4953")
    String userId;

    @EmptyValid(message = "Username không được để trống!", errorCode = "4953")
    @LengthValid(
            message = "Username độ dài phải nhỏ hơn {0} ký tự và lớn hơn {1} ký tự",
            errorCode = "4953", min = 6, max = 50
    )
    String username;

    @EmptyValid(message = "Password không được để trống!", errorCode = "4953")
    @LengthValid(
            message = "Password độ dài phải nhỏ hơn {0} ký tự và lớn hơn {1} ký tự",
            errorCode = "4953", min = 10, max = 100
    )
    String password;

    String confirmPassword;

    String fullName;

    @PatternValid(
            message = "Địa chỉ Email không đúng định dạng!",
            errorCode = "4953", regexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$"
    )
    @LengthValid(message = "Địa chỉ Email độ dài phải nhỏ hơn {0} ký tự", errorCode = "4953", max = 100)
    String email;

    @PatternValid(
            message = "Số điện thoại không đúng định dạng!",
            errorCode = "4953", regexPattern = "^(\\\\+?84|0)([3|5|7|8|9])+([0-9]{8})\\\\b"
    )
    @LengthValid(message = "Số điện thoại độ dài phải nhỏ hơn {0} ký tự", errorCode = "4953", max = 20)
    String mobile;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
            timezone = "Asia/Ho_Chi_Minh"
    )
    Date dateOfBirth;

}
