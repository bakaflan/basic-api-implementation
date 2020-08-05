package com.thoughtworks.rslist.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRsRequest {
    @JsonProperty
    private String eventName;
    @JsonProperty
    private String keyword;
    @JsonProperty
    private User user;
}
