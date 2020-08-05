package com.thoughtworks.rslist.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AddRsRequest {
    @JsonProperty
    @NotNull
    private String eventName;
    @JsonProperty
    @NotNull
    private String keyword;
    @JsonProperty
    @Valid
    private User user;
}
