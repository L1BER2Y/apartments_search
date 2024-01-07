package by.it_academy.jd2.user_service.aop;

import by.it_academy.jd2.user_service.clients.AuditFeignClient;
import by.it_academy.jd2.user_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.user_service.core.dto.*;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.core.exceptions.ValidationException;
import by.it_academy.jd2.user_service.repository.UserRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
public class AuditedAspect {
    private final UserRepository userRepository;
    private final AuditFeignClient auditFeignClient;
    private final JwtTokenHandler jwtTokenHandler;

    public AuditedAspect(UserRepository userRepository, AuditFeignClient auditFeignClient, JwtTokenHandler jwtTokenHandler) {
        this.userRepository = userRepository;
        this.auditFeignClient = auditFeignClient;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @Around("@annotation(Audited)")
    public Object checkActivation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Audited annotation = signature.getMethod().getAnnotation(Audited.class);
        Object result = joinPoint.proceed();
        AuditDTO auditDto = buildAuditDto(joinPoint, annotation, result);
        String token = "Bearer " + jwtTokenHandler.generateAccessToken(new UserLoginDTO());
        auditFeignClient.sendRequestToCreateLog(token, auditDto);
        return result;
    }

    private AuditDTO buildAuditDto(ProceedingJoinPoint joinPoint, Audited annotation, Object result) {
        switch (annotation.auditedAction()) {
            case REGISTRATION, UPDATE_PASSWORD -> {
                return createAuditDto(annotation, (UserDTO) result);
            }
            case VERIFICATION, LOGIN -> {
                return getAuditDtoByEmail(joinPoint, annotation);
            }
            case INFO_ABOUT_ALL_USERS -> {
                return getAuditDtoForInfoAboutAllUsers(annotation);
            }
            case INFO_ABOUT_USER_BY_ID, INFO_ABOUT_ME -> {
                return getAuditDtoForUserInfo(annotation, result);
            }
            case CREATE_USER, UPDATE_USER -> {
                return getAuditDtoForUser(annotation, result);
            }
            default -> throw new RuntimeException("Unrecognized action: " + annotation.auditedAction());
        }
    }

    private AuditDTO getAuditDtoForInfoAboutAllUsers(Audited annotation) {
        UserDTO userDetailsDto = (UserDTO) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return createAuditDto(annotation, userDetailsDto, userDetailsDto.getId());
    }

    private AuditDTO getAuditDtoForUserInfo(Audited annotation, Object result) {
        return createAuditDto(annotation, getUserDetailFromSecurityContext(), ((UserDTO) result).getId());
    }

    private AuditDTO getAuditDtoForUser(Audited annotation, Object result) {
        return createAuditDto(annotation, getUserDetailFromSecurityContext(), ((UserDTO) result).getId());
    }

    private UserDTO getUserDetailFromSecurityContext() {
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private UserEntity findByEmail(String email) {
        return userRepository.findByMail(email).orElseThrow(ValidationException::new);
    }

    private AuditDTO createAuditDto(Audited annotation, UserDTO userDTO) {
        return new AuditDTO().setUserAuditDTO(buildUserAuditDto(userDTO))
                .setAction(annotation.auditedAction())
                .setEssenceType(annotation.essenceType())
                .setEssenceTypeId(userable.getId().toString());
    }

    private AuditDTO createAuditDto(Audited annotation, UserDTO userDTO, UUID id) {
        return new AuditDTO().setUserAuditDTO(buildUserAuditDto(userDTO))
                .setAction(annotation.auditedAction())
                .setEssenceType(annotation.essenceType())
                .setId(id.toString());
    }

    private UserAuditDTO buildUserAuditDto(UserDTO userDTO) {
        return new UserAuditDTO().setUserId(userDTO.getId())
                .setMail(userDTO.getMail())
                .setFio(userDTO.getFio())
                .setUserRole(userDTO.getRole());
    }

    private AuditDTO getAuditDtoByEmail(ProceedingJoinPoint joinPoint, Audited annotation) {
        Emailable dto = (Emailable) Arrays.stream(joinPoint.getArgs()).toList().get(0);
        return createAuditDto(annotation);
    }
}
