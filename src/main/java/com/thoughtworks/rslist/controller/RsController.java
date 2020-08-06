package com.thoughtworks.rslist.controller;

import com.thoughtworks.rslist.Error.IndexOutOfRange;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.service.UserService;
import com.thoughtworks.rslist.util.AddRsRequest;
import com.thoughtworks.rslist.util.UpdateRsRequest;
import com.thoughtworks.rslist.service.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RsController {

    @Autowired
    private RsService rsService;
    @Autowired
    private UserService userService;

    @GetMapping("/rs/list")
    public ResponseEntity getRsList(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer end){
        if(start!=null&&end!=null){
            if(start<0 || end>rsService.getRsListSize()){
                throw new IndexOutOfRange("invalid request param");
            }
            return ResponseEntity.status(HttpStatus.OK).body(rsService.getRsByRange(start,end));
        }
        return ResponseEntity.status(HttpStatus.OK).body(rsService.getRsList());
    }

    @GetMapping("/rs")
    public ResponseEntity getRsByIndex(@RequestParam Integer index){
        if(index>rsService.getRsListSize()){
            throw new IndexOutOfRange("invalid index");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rsService.getRsByIndex(index));
    }

    @PostMapping("/rs")
    public ResponseEntity adddRs(@RequestBody@Valid AddRsRequest addRsRequest){
      int index = rsService.addRsReturnIndex(addRsRequest);
      userService.addUser(addRsRequest.getUser());
      return ResponseEntity.status(HttpStatus.CREATED).header("index",String.valueOf(index)).body("");
    }

    @PatchMapping("/rs")
    public ResponseEntity updateRsWithKeywordAndEventName(@RequestBody UpdateRsRequest updateRsRequest){
        rsService.updateRs(updateRsRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("成功更新");

    }

    @DeleteMapping("/rs")
    public ResponseEntity deleteRsWithIndex(@RequestParam int index){
        rsService.deleteRs(index);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("成功删除");
    }





}
