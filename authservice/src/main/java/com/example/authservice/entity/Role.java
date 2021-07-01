package com.example.authservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    public final static Role USER=new Role("USER");
    public final static Role SERVICE=new Role("Service");

    private String name;
}
