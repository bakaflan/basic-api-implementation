package com.thoughtworks.rslist.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RsVoteRequest {
    private Integer voteNum;
    private Integer userId;
}
