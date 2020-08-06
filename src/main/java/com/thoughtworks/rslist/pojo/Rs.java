package com.thoughtworks.rslist.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Rs {

    @NotNull
    private String keyword;
    @NotNull
    private String eventName;
    @JsonIgnore
    private Integer userId;

    public Rs(String keyword, String eventName) {
        this.keyword = keyword;
        this.eventName = eventName;
    }
}
