package com.thoughtworks.rslist.controller;

import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.service.RsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class RsController {

    @Autowired
    RsService rsService;

  @GetMapping("/rs/list")
  public String getRsList(){
      return rsService.getRsList().toString();
  }

  @GetMapping("/rs/index")
  public String getRsByIndex(@RequestParam int index){
      return rsService.getRsList().get(index);

  }

    @GetMapping("/rs/range")
    public String getRsByRange(@RequestParam int start,@RequestParam int end){
        return rsService.getRsList().subList(start,end).toString();
    }

    @PostMapping("/rs/add")
    public String adddRsWithKeywordAndEventName(@RequestBody Rs rs){
      rsService.addRs(rs);
      return "成功添加";
    }






}
