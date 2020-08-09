package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.Exception.RsException;
import com.thoughtworks.rslist.Exception.UserNotExistedException;
import com.thoughtworks.rslist.dto.RsDto;
import com.thoughtworks.rslist.dto.UserDto;
import com.thoughtworks.rslist.dto.VoteDto;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.repository.RsRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.repository.VoteRepository;
import com.thoughtworks.rslist.util.AddRsRequest;
import com.thoughtworks.rslist.util.RsVoteRequest;
import com.thoughtworks.rslist.util.UpdateRsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RsService {

    private List<Rs> rsList;

    @Autowired
    private RsRepository rsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    public RsService() {
        this.rsList = new ArrayList<>();
        this.initRsList();
    }

    public void initRsList() {
        List<Rs> initRsList = new ArrayList<>();
        initRsList.add(new Rs("事件一", "第一条事件"));
        initRsList.add(new Rs("事件二", "第二条事件"));
        initRsList.add(new Rs("事件三", "第三条事件"));
        rsList = initRsList;
    }

    public List<Rs> getRsList() {
        return rsList;
    }

    public String getRsByIndex(int index) {
        return rsList.get(index).getEventName();
    }

    public List<Rs> getRsByRange(int start, int end) {
        return rsList.subList(start, end);
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

    public void deleteRs(int index) {
        rsList.remove(index);
    }

    public int getRsListSize() {
        return rsList.size();
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
        if (!rsRepository.existsById(rsId)) {
            throw new RsException("rs is not exist");
        }
        Optional<UserDto> optionalUserDto = userRepository.findById(rsVoteRequest.getUserId());
        if (!optionalUserDto.isPresent()) {
            throw new UserNotExistedException("user not exist");
        }
        UserDto userDto = optionalUserDto.get();
        if (userDto.getVoteNum() < rsVoteRequest.getVoteNum()) {
            throw new RsException("has not enough vote num");
        }
        userDto.vote(rsVoteRequest.getVoteNum());
        voteRepository.save(VoteDto.builder()
                .rsId(rsId)
                .userId(rsVoteRequest.getUserId())
                .time(LocalDateTime.now())
                .voteNum(rsVoteRequest.getVoteNum()).build());

        userRepository.save(userDto);

    }
}
