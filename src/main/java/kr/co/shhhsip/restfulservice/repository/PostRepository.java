package kr.co.shhhsip.restfulservice.repository;

import kr.co.shhhsip.restfulservice.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
