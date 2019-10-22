package com.tison.basic.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author tison
 * @date 2019/10/22
 * @description
 */
@Data
@Builder(toBuilder = true)
public class User {

    private String userId;

    private String username;

    private int age;

}
