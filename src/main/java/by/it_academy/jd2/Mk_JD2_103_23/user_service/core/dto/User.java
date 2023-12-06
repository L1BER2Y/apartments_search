package by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID id;
    private LocalDateTime dt_create;
    private LocalDateTime dt_update;
    private String mail;
    private String fio;
    private Role ADMIN;
    private Status WAITING_ACTIVATION;

}
