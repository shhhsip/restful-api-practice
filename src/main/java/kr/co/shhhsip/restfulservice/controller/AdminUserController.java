package kr.co.shhhsip.restfulservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import kr.co.shhhsip.restfulservice.bean.AdminUser;
import kr.co.shhhsip.restfulservice.bean.AdminUserV2;
import kr.co.shhhsip.restfulservice.bean.User;
import kr.co.shhhsip.restfulservice.dao.UserDaoService;
import kr.co.shhhsip.restfulservice.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }


    // /admin/users/{id}
    // @GetMapping("/v1/users/{id}") // URI로 버전 분리
    //@GetMapping(value = "/users/{id}", params = "version=1") // 파라미터로 버전 분리
    //@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1") // header 값으로 버전 분리
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json") // mime-type으로 버전 분리
    public MappingJacksonValue retrieveUser4Admin(@PathVariable Long id) {
        User user = service.findOne(id);

        AdminUser adminUser = new AdminUser();
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not fount", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
        }

        FilterProvider filters = createAndAddFilter("UserInfo","id", "name", "joinDate", "ssn");

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUser4Admin() {
        List<User> users = service.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;

        for (User user : users) {
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);

            adminUsers.add(adminUser);
        }

        FilterProvider filters = createAndAddFilter("UserInfo","id", "name", "joinDate", "ssn");

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);

        return mapping;
    }

    //@GetMapping("/v2/users/{id}") // URI로 버전 분리
    // @GetMapping(value = "/users/{id}", params = "version=2") // 파라미터로 버전 분리
    //@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2") // header 값으로 버전 분리
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json") // mime-type으로 버전 분리
    public MappingJacksonValue retrieveUser4AdminV2(@PathVariable Long id) {
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not fount", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        FilterProvider filters = createAndAddFilter("UserInfoV2","id", "name", "joinDate", "grade");

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    private static FilterProvider createAndAddFilter(String filterId, String... acceptColumn) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(acceptColumn);
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, filter);
        return filters;
    }


}
