package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.pojo.Rs;
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

    private void initRsList() {
        rsList.add(new Rs("事件一","第一条事件"));
        rsList.add(new Rs("事件二","第二条事件"));
        rsList.add(new Rs("事件三","第三条事件"));
    }

    public List<String> getRsList(){
        List<String> rsEventNameList = new ArrayList<>();
        rsList.forEach(i -> rsEventNameList.add(i.getEventName()));
        return rsEventNameList;
    }

    public void addRs(Rs rs) {
        this.rsList.add(rs);
    }
}
