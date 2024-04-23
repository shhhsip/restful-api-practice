package kr.co.shhhsip.restfulservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Table(name = "users")
@Entity
public class User {

    @Schema(title = "사용자 ID", description = "사용자 ID는 자동 생성 값 입니다.")
    @Id
    @GeneratedValue
    private Long id;

    
    @Schema(title = "사용자 이름", description = "사용자 이름을 입력합니다.")
    @Size(min = 2, message = "이름은 2글자 이상만 입력 가능합니다.")
    private String name;

    @Schema(title = "사용자 등록일", description = "사용자 등록일을 입력합니다. 입력하지 않으면 현재 날짜가 지정됩니다.")
    @Past(message = "현재보다 지난 날짜는 입력할 수 없습니다.")
    private Date joinDate;

    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호를 입력합니다.")
    //@JsonIgnore
    private String password;

    @Schema(title = "사용자 주민번호", description = "사용자 주민번호를 입력합니다.")
    //@JsonIgnore
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Long id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
