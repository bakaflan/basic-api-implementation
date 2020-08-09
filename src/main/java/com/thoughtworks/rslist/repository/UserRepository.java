package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.UserDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
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

    @Override
    boolean existsById(Integer integer);

    Optional<UserDto> findUserByUserName(String userName);

    @Transactional
    @Modifying
    @Query(value = "alter table user auto_increment = 1",nativeQuery = true)
    void initAutoIncrement();
}
