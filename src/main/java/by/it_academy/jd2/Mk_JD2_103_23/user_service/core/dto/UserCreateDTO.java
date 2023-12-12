package by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto;

public class UserCreateDTO {
    private String mail;
    private String fio;
    private Role role = Role.ADMIN;
    private Status status = Status.WAITING_ACTIVATION;
    private String password;
}
