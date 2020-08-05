package com.thoughtworks.rslist.controller;

import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.service.UserService;
import com.thoughtworks.rslist.util.AddRsRequest;
import com.thoughtworks.rslist.util.UpdateRsRequest;
import com.thoughtworks.rslist.service.RsService;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<Rs> getRsList(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer end){
      if(start!=null&&end!=null){
          return rsService.getRsByRange(start,end);
      }
      return rsService.getRsList();
  }

  @GetMapping("/rs")
  public String getRsByIndex(@RequestParam Integer index){
      return rsService.getRsByIndex(index);

  }

    @PostMapping("/rs")
    public String adddRs(@RequestBody@Valid AddRsRequest addRsRequest){
      rsService.addRs(addRsRequest);
      userService.addUser(addRsRequest.getUser());
      return "成功添加";
    }

    @PatchMapping("/rs")
    public String updateRsWithKeywordAndEventName(@RequestBody UpdateRsRequest UpdateRsRequest){
        rsService.updateRs(UpdateRsRequest);
        return "成功更新";
    }

    @DeleteMapping("/rs")
    public String deleteRsWithIndex(@RequestParam int index){
        rsService.deleteRs(index);
        return "成功删除";
    }





}
