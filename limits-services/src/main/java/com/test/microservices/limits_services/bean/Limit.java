package com.test.microservices.limits_services.bean;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Limit {
    private int min;
    private int max;

}
