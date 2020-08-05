package com.thoughtworks.rslist.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rs {

    private String keyword;
    private String eventName;
    private User user;

    public Rs(String keyword, String eventName) {
        this.keyword = keyword;
        this.eventName = eventName;
    }
}
