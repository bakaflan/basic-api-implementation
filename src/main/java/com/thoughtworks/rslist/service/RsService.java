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

    public List<String> getRsList(){
        List<String> rsEventNameList = new ArrayList<>();
        rsList.forEach(i -> rsEventNameList.add(rsList.indexOf(i)+"."+i.getEventName()));
        return rsEventNameList;
    }

    public void addRs(Rs rs) {
        this.rsList.add(rs);
    }

    public String getRsByIndex(int index) {
        return rsList.get(index).getEventName();
    }

    public List<String> getRsByRange(int start, int end) {
        List<String> subList = new ArrayList<>();
        rsList.subList(start,end).forEach(i -> subList.add(i.getEventName()));
        return subList;
    }

    public void updateRs(UpdateRsRequest updateRsRequest) {
        rsList.set(updateRsRequest.getIndex(),updateRsRequest.getRs());
    }

    public void deleteRs(int index) {
        rsList.remove(index);
    }
}
