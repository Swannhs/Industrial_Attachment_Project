package com.swann.industrialattachment.pyaload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtLoginSuccess {
    private boolean success;
    private String token;

    @Override
    public String toString() {
        return "JwtLoginSuccess{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}

