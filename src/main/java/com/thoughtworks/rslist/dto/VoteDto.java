package com.thoughtworks.rslist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private UserDto userDto;
    @Column(name = "user_id")
    private Integer userId;

    @OneToOne
    @JoinColumn(name ="rs_id",insertable = false,updatable = false)
    private RsDto rsDto;
    @Column(name = "rs_id")
    private Integer rsId;

    private int voteNum;
    private LocalDateTime time;
}
