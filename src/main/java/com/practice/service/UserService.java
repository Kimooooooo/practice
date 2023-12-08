package com.practice.service;


import com.practice.entity.User;
import com.practice.entity.UserDto;
import com.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> findAll(Sort sort) {  //유저 전체 조회
        return userRepository.findAll(sort);
    }

    public Optional<User> findbyId(Long id) {  //유저 아이디 조회
        Optional<User> finduser = userRepository.findById(id);
        if (finduser.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다");
        }
        return finduser;
    }
    @Transactional
    public User createUser(User user) {    //유저 생성
        Duplicatememberverification(user);
        return userRepository.save(user);
    }

    public void Duplicatememberverification(User user) { //중복회원 확인
        userRepository.findByUserId(user.getUserId())
                .ifPresent(existingUser -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }
    @Transactional
    public User updateUser(UserDto userDto) { //유저 수정
        Optional<User> finduser = userRepository.findById(userDto.getId());
        if (finduser.isEmpty()) {
            throw new IllegalStateException("수정할 회원이 없습니다");
        }
        User updateUser = finduser.get();
        updateUser.updateFields(userDto.getPassword(), userDto.getName(), userDto.getAge());
        return userRepository.save(updateUser);
    }
    @Transactional
    public void deleteUser(Long userId) {//유저삭제
        userRepository.findById(userId)
                .ifPresentOrElse(
                        user -> userRepository.deleteById(userId),
                        () -> {
                            throw new IllegalStateException("삭제할 회원이 없습니다");
                        }
                );
    }
}