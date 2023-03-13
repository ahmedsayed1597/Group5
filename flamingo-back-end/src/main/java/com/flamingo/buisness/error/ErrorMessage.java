package com.flamingo.buisness.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ErrorMessage {
    
    private int status;
    private String message;
}
