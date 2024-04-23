package kr.co.shhhsip.restfulservice.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfo")
public class AdminUser {
    private Long id;

    @Size(min = 2, message = "이름은 2글자 이상만 입력 가능합니다.")
    private String name;

    @Past(message = "현재보다 지난 날짜는 입력할 수 없습니다.")
    private Date joinDate;

    private String password;
    private String ssn;
}
