package liga.shop.shop.aop;

import liga.shop.shop.core.model.LogEntity;
import liga.shop.shop.core.model.PersonDataEntity;
import liga.shop.shop.core.repository.LogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAdvice {

    LogRepository logRepository;

    public LoggingAdvice(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within (liga.shop.shop.core.controller.*)")
    public void controllers() {
    }

    @Pointcut("execution(* org.springframework.security.core.userdetails.UserDetailsService.loadUserByUsername(String))")
    public void userDetailsService() {
    }

    @Around("controllers()")
    public Object controllersLogging(ProceedingJoinPoint proceedingJoinPoint) {

        LocalDateTime localDateTime = LocalDateTime.now();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();

        PersonDataEntity personDataEntity = (PersonDataEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        LogEntity logEntity = LogEntity.builder()
                .time(localDateTime)
                .methodName(methodName)
                .className(className)
                .args(personDataEntity.getEmail())
                .build();

        logRepository.insert(logEntity);
        Long id = logRepository.findLogId(localDateTime);
        log.info("Log#{} {} Вызван метод: {}: {} пользователем {}.", id, localDateTime, className, methodName, personDataEntity.getEmail());

        Object object = null;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return object;
    }

    @Around("userDetailsService()")
    public Object authenticationLogging(ProceedingJoinPoint proceedingJoinPoint) {

        LocalDateTime localDateTime = LocalDateTime.now();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        String email = array[0].toString();

        LogEntity logEntity = LogEntity.builder()
                .time(localDateTime)
                .className(className)
                .methodName(methodName)
                .args(email)
                .build();

        logRepository.insert(logEntity);
        Long id = logRepository.findLogId(logEntity.getTime());

        Object object = null;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (object != null) {
            log.info("Log#{} {} Аутентификация с email: {} завершена успешно", id, logEntity.getTime(), logEntity.getArgs());
        } else
            log.info("Log#{} {} Ошибка аутентификации. Аккаунт с email: {} не зарегистрирован", id, logEntity.getTime(), logEntity.getArgs());

        return object;
    }
}
