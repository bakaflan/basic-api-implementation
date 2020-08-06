package com.thoughtworks.rslist.dto;

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
    @GeneratedValue
    private int id;
    private String eventName;
    private String keyword;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private UserDto userDto;
    @Column(name = "user_id")
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
}
