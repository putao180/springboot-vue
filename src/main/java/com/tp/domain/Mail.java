package com.tp.domain;

import lombok.Data;

@Data
public class Mail {
    private String to;
    private String subject;
    private String content;
}
