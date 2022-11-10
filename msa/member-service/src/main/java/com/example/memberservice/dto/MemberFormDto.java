package com.example.memberservice.dto;

import com.example.memberservice.vo.ResponseOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MemberFormDto {
    private String userId;
    private String name;
    private String pwd;

    private List<ResponseOrder> orders;
}
