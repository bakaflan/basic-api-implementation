package com.thoughtworks.rslist.controller;

import com.thoughtworks.rslist.Exception.IndexOutOfRange;
import com.thoughtworks.rslist.dto.RsDto;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.pojo.Vote;
import com.thoughtworks.rslist.service.UserService;
import com.thoughtworks.rslist.util.AddRsRequest;
import com.thoughtworks.rslist.util.RsVoteRequest;
import com.thoughtworks.rslist.util.UpdateRsRequest;
import com.thoughtworks.rslist.service.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RsController {

    @Autowired
    private RsService rsService;
    @Autowired
    private UserService userService;

    @GetMapping("/rs/list")
    public ResponseEntity getRsList(){
        return ResponseEntity.status(HttpStatus.OK).body(rsService.findAll());
    }

    @GetMapping("/rs")
    public ResponseEntity findRsById(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(rsService.findRsById(id));

    }

    @PostMapping("/rs")
    public ResponseEntity createRs(@RequestBody@Valid AddRsRequest addRsRequest){
       RsDto rsDto = rsService.createRs(addRsRequest);
       return ResponseEntity.status(HttpStatus.CREATED)
               .header("index", String.valueOf(rsDto.getId()))
               .body(rsDto);
    }

    @PatchMapping("/rs/{rsId}")
    public ResponseEntity<RsDto> updateRs(@PathVariable Integer rsId,@RequestBody UpdateRsRequest updateRsRequest){
        RsDto rsDto = rsService.updateRs(rsId,updateRsRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rsDto);

    }

    @PostMapping("/rs/vote/{rsId}")
    public ResponseEntity voteRs(@PathVariable Integer rsId,@RequestBody RsVoteRequest rsVoteRequest){
        rsService.voteRs(rsId,rsVoteRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("成功投票");
    }

    @DeleteMapping("/rs")
    public ResponseEntity deleteRsById(@RequestParam Integer id){
        rsService.deleteRsById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("成功删除");
    }


}
