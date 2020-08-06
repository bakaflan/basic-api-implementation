package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.UserDto;
import com.thoughtworks.rslist.dto.VoteDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface VoteRepository extends CrudRepository<VoteDto,Integer> {
    @Override
    <S extends VoteDto> S save(S entity);

    @Override
    Optional<VoteDto> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<VoteDto> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    void deleteAll();
}
