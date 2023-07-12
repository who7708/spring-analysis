package org.spring.model;

import lombok.Data;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-11
 */
@Data
public class UserFor12306 {
    private int userId;
    private String loginId;
    private String realName;
    private String idNumber;
    private String passwd;
    private String mobile;
    private String email;
}
