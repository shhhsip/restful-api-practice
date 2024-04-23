package kr.co.shhhsip.restfulservice.dao;

import kr.co.shhhsip.restfulservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static Long usersCount = 3L;

    static {
        users.add(new User(1L, "혁종", new Date(), "1111", "111111-1111111"));
        users.add(new User(2L, "동희", new Date(), "2222", "222222-1111111"));
        users.add(new User(3L, "민호", new Date(), "3333", "333333-1111111"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        if (user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);

        return user;
    }

    public User findOne(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(Long id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(id)) {
                iterator.remove();
                return user;
            }

        }
        return null;
    }
}
