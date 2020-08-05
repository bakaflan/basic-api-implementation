package com.thoughtworks.rslist.util;

import com.thoughtworks.rslist.pojo.Rs;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UpdateRsRequest {
    private int index;
    private String keyword;
    private String eventName;

    public Rs getRs(){
        return new Rs(keyword,eventName);
    }
}
