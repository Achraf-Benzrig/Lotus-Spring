package tn.esprit.spring.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import tn.esprit.spring.registration.token.ConfirmationTokenRepository;
import tn.esprit.spring.registration.token.ConfirmationTokenService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@AllArgsConstructor
@Component
@EnableAsync
@Aspect
public class LoggingAspect {
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	private final ConfirmationTokenService confirmationTokenRepository;
	@Async
	@Scheduled(cron="0 0 0 * * ?")
	public void fixedRateMethod() {
		confirmationTokenRepository.deleteAllTokens();
	}
}
