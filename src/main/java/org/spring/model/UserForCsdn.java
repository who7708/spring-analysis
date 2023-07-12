package org.spring.model;

import lombok.Data;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-11
 */
@Data
public class UserForCsdn {
    private int id;
    private String loginId;
    private String passwd;
    private String email;
}
