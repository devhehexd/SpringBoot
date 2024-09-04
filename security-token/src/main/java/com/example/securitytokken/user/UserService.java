package com.example.securitytokken.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //추가, 수정
    public void saveUser(UserDto userDto) {
        userDao.save(new User(userDto.getId(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getType()));
    }

    //pk 검색
    public UserDto getUser(String id) {
        User entity = userDao.findById(id).orElse(null);
        if (entity != null) {
            return new UserDto(entity.getId(), entity.getPassword(), entity.getType());
        }

        return null;
    }

    //삭제
    public void deleteUser(String id) {
        userDao.deleteById(id);
    }
}
