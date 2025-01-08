package com.example.userservice.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmaiDTO {
    private String toEmail;
    private String fromEmail;
    private String subject;
    private String body;
}
