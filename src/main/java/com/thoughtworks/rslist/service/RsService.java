package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.Exception.LocalDateTimeException;
import com.thoughtworks.rslist.Exception.RsException;
import com.thoughtworks.rslist.Exception.UserNotExistedException;
import com.thoughtworks.rslist.dto.RsDto;
import com.thoughtworks.rslist.dto.UserDto;
import com.thoughtworks.rslist.dto.VoteDto;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.pojo.Vote;
import com.thoughtworks.rslist.repository.RsRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.repository.VoteRepository;
import com.thoughtworks.rslist.util.AddRsRequest;
import com.thoughtworks.rslist.util.RsVoteRequest;
import com.thoughtworks.rslist.util.UpdateRsRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RsService {

    private RsRepository rsRepository;

    private UserRepository userRepository;

    private VoteRepository voteRepository;

    public RsService(RsRepository rsRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.rsRepository = rsRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public RsDto updateRs(Integer rsId, UpdateRsRequest updateRsRequest) {
        Optional<RsDto> optionalRsDto = rsRepository.findById(rsId);
        if (!optionalRsDto.isPresent()) {
            throw new RsException("rs is not exist");
        }
        RsDto rsDto = optionalRsDto.get();
        if (!rsDto.getUserId().equals(updateRsRequest.getUserId())) {
            throw new RsException("rs is not belong to this user");
        }
        rsDto.update(updateRsRequest.getEventName(), updateRsRequest.getKeyword());
        return rsRepository.save(rsDto);
    }

    @Transactional
    public RsDto createRs(AddRsRequest addRsRequest) {
        if (!userRepository.existsById(addRsRequest.getUserId())) {
            throw new UserNotExistedException("user not exist");
        }
        RsDto dto = rsRepository.save(RsDto.builder()
                .eventName(addRsRequest.getEventName())
                .keyword(addRsRequest.getKeyword())
                .userId(addRsRequest.getUserId()).build());
        return dto;
    }

    public List<Rs> findAll() {
        return rsRepository.findAll().stream().map(RsDto::parse).collect(Collectors.toList());
    }


    @Transactional
    public void voteRs(Integer rsId, RsVoteRequest rsVoteRequest) {
        Optional<RsDto> optionalRs = rsRepository.findById(rsId);
        if (!optionalRs.isPresent()) {
            throw new RsException("rs is not exist");
        }
        RsDto rsDto = optionalRs.get();
        Optional<UserDto> optionalUserDto = userRepository.findById(rsVoteRequest.getUserId());
        if (!optionalUserDto.isPresent()) {
            throw new UserNotExistedException("user not exist");
        }
        UserDto userDto = optionalUserDto.get();
        if (userDto.getVoteNum() < rsVoteRequest.getVoteNum()) {
            throw new RsException("has not enough num of vote");
        }
        userDto.vote(rsVoteRequest.getVoteNum());
        rsDto.vote(rsVoteRequest.getVoteNum());
        voteRepository.save(VoteDto.builder()
                .rsId(rsId)
                .userId(rsVoteRequest.getUserId())
                .time(LocalDateTime.now())
                .voteNum(rsVoteRequest.getVoteNum()).build());
        userRepository.save(userDto);
        rsRepository.save(rsDto);
    }

    public RsDto findRsById(Integer rsId){
        Optional<RsDto> optionalRsDto = rsRepository.findById(rsId);
        if(!optionalRsDto.isPresent()){
            throw new RsException("Rs is not existed");
        }
        return optionalRsDto.get();
    }

    public void deleteRsById(Integer rsId) {
        if(!rsRepository.existsById(rsId)){
            throw new RsException("rs id is not existed");
        }
        rsRepository.deleteById(rsId);
    }

    public List<VoteDto> findVoteAll(String start, String end, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<VoteDto> pageResult;
        List<VoteDto> voteDtoList;
        if(start !=null && end !=null){
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);
            if(startDate.isAfter(endDate)){
                throw new LocalDateTimeException("startDate is after endDate");
            }
            pageResult = voteRepository.findAll(startDate,endDate,pageable);
            voteDtoList = pageResult.getContent();
        }else {
            pageResult = voteRepository.findAll(pageable);
            voteDtoList = pageResult.getContent();
        }
        return voteDtoList;
    }
}
