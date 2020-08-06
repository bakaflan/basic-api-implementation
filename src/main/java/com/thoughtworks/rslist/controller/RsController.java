package com.thoughtworks.rslist.controller;

import com.thoughtworks.rslist.Exception.IndexOutOfRange;
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
    public ResponseEntity getRsByIndex(@RequestParam Integer index){
        if(index>rsService.getRsListSize()){
            throw new IndexOutOfRange("invalid index");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rsService.getRsByIndex(index));
    }

    @PostMapping("/rs")
    public ResponseEntity createRs(@RequestBody@Valid AddRsRequest addRsRequest){
       rsService.createRs(addRsRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body("创建成功");
    }

    @PatchMapping("/rs/{rsId}")
    public ResponseEntity updateRs(@PathVariable Integer rsId,@RequestBody UpdateRsRequest updateRsRequest){
        rsService.updateRs(rsId,updateRsRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("成功更新");

    }

    @DeleteMapping("/rs")
    public ResponseEntity deleteRsWithIndex(@RequestParam int index){
        rsService.deleteRs(index);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("成功删除");
    }

    @PostMapping("/rs/vote/{rsId}")
    public ResponseEntity voteRs(@PathVariable Integer rsId,@RequestBody RsVoteRequest rsVoteRequest){
        rsService.voteRs(rsId,rsVoteRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("成功投票");
    }



}
