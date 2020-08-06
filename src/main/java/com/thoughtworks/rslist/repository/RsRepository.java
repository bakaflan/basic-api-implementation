package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.RsDto;
import com.thoughtworks.rslist.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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
}
