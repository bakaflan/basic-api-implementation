package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String eventName;
    private String keyword;
    private int voteNum = 0;

    @JsonIgnore
    @ManyToOne(targetEntity = UserDto.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    private UserDto user;

    @Column(name = "user_id")
    @JsonIgnore
    private Integer userId;


    public static RsDto bind(Rs rs){
        return RsDto.builder()
                .keyword(rs.getKeyword())
                .eventName(rs.getEventName())
                .userId(rs.getUserId()).build();
    }

    public static Rs parse(RsDto rsDto){
        return Rs.builder()
                .keyword(rsDto.getKeyword())
                .eventName(rsDto.getEventName())
                .userId(rsDto.getUserId()).build();
    }

    public void update(String eventName,String keyword){
        if(eventName!=null){
            this.eventName = eventName;
        }
        if(keyword!=null){
            this.keyword = keyword;
        }
    }

    public void vote(Integer voteNum){
        this.voteNum += voteNum;
    }
}
