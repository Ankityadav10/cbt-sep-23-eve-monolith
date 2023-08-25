package com.cbteve.cbtsep23evemonolith;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyNum {

    int num;

    public MyNum increment()
    {
        num = num+1;
       return this;
    }


}
