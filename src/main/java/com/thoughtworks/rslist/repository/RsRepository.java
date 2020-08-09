package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.RsDto;
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
public interface RsRepository extends CrudRepository<RsDto,Integer> {

    @Override
    <S extends RsDto> S save(S entity);

    @Override
    Optional<RsDto> findById(Integer integer);

    @Override
    List<RsDto> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    void deleteAll();

    @Transactional
    @Modifying
    @Query(value = "alter table rs auto_increment = 1",nativeQuery = true)
    void initAutoIncrement();

}
