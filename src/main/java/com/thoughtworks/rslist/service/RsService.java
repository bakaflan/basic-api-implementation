package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.util.UpdateRsRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RsService {

    private List<Rs> rsList;

    public RsService() {
        this.rsList = new ArrayList<>();
        this.initRsList();
    }

    public void initRsList() {
        List<Rs> initRsList =  new ArrayList<>();
        initRsList.add(new Rs("事件一","第一条事件"));
        initRsList.add(new Rs("事件二","第二条事件"));
        initRsList.add(new Rs("事件三","第三条事件"));
        rsList = initRsList;
    }

    public List<Rs> getRsList(){
        return rsList;
    }

    public void addRs(Rs rs) {
        this.rsList.add(rs);
    }

    public String getRsByIndex(int index) {
        return rsList.get(index).getEventName();
    }

    public List<Rs> getRsByRange(int start, int end) {
        return rsList.subList(start, end);
    }

    public void updateRs(UpdateRsRequest updateRsRequest) {
        Rs temp = rsList.get(updateRsRequest.getIndex());
        if(updateRsRequest.getEventName()!=null){
            temp.setEventName(updateRsRequest.getEventName());
        }
        if(updateRsRequest.getKeyword()!=null){
            temp.setKeyword(updateRsRequest.getKeyword());
        }
        rsList.set(updateRsRequest.getIndex(),temp);
    }

    public void deleteRs(int index) {
        rsList.remove(index);
    }
}
