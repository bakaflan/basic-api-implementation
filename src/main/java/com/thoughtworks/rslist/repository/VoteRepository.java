package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.UserDto;
import com.thoughtworks.rslist.dto.VoteDto;
import org.hibernate.annotations.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<VoteDto,Integer> {
    @Override
    <S extends VoteDto> S save(S entity);

    @Override
    Optional<VoteDto> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Query(value = "select * from vote order by time desc",nativeQuery = true)
    Page<VoteDto> findAll(Pageable pageable);

    @Override
    void deleteById(Integer integer);

    @Override
    void deleteAll();

    @Transactional
    @Modifying
    @Query(value = "alter table vote auto_increment = 1",nativeQuery = true)
    void initAutoIncrement();

    @Query(value = "select v from VoteDto v where v.time > ?1 and v.time < ?2")
    Page<VoteDto> findAll(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
