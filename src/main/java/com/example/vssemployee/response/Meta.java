package com.example.vssemployee.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author manhdt14
 * created in 9/1/2021 4:36 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private Integer code;
    private String message;
}
