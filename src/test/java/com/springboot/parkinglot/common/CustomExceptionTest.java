package com.springboot.parkinglot.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomExceptionTest {

    @Test
    void ExceptionTest (){
        try{
            throw new CustomException("exception test");
        }
        catch (CustomException e){
            System.out.println(e.getMessage());
            e.printStackTrace();;
        }

    }

}
