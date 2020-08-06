package com.thoughtworks.rslist.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vote {
    private int userId;
    private int voteNum;
    private LocalDateTime time;
}
