package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends CrudRepository<UserDto,Integer> {
    @Override
    List<UserDto> findAll();

    @Override
    <S extends UserDto> S save(S entity);

    @Override
    Optional<UserDto> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void deleteAll();

    Optional<UserDto> findUserByUserName(String userName);
}
