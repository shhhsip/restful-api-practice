package kr.co.shhhsip.restfulservice.repository;

import kr.co.shhhsip.restfulservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
