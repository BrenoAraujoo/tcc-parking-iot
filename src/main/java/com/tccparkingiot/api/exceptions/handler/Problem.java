package com.tccparkingiot.api.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class Problem {

    private Integer status;
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime timeStamp;

    private List<Field> fields;




    @Builder
    @Getter
    public static class  Field{
        private String name;
        private String userMessage;
    }

}
