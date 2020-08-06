package com.thoughtworks.rslist.util;

import com.thoughtworks.rslist.pojo.Rs;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UpdateRsRequest {
    private String keyword;
    private String eventName;
    private Integer userId;

    public Rs getRs(){
        return new Rs(keyword,eventName);
    }
}
