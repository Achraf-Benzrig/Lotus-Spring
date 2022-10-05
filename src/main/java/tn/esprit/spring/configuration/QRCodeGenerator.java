package tn.esprit.spring.configuration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.AllArgsConstructor;
import tn.esprit.spring.controller.IQRCodeGenerator;
import tn.esprit.spring.registration.email.EmailService;
@Service
@AllArgsConstructor
public class QRCodeGenerator implements IQRCodeGenerator{
	
	
		
		private final static int width = 350;
		private final static int height = 350;
	    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	    private final JavaMailSender mailSender;
	    
		public static String token = UUID.randomUUID().toString();
		public void generateQRCodeImage(String text, String filePath)
	            throws WriterException, IOException {
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        
	        BitMatrix bitMatrix = qrCodeWriter.encode(text+token, BarcodeFormat.QR_CODE, width, height);

	        Path path = FileSystems.getDefault().getPath(filePath);
	        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	        
	        final File attachment =new File("C:/Users/farou/Desktop/New folder (2)/QRCode.png");
	        
	        MatrixToImageWriter.writeToFile(bitMatrix, "PNG", attachment);
	        try {
	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	            helper.setText(text, true);
	            helper.setTo(text);
	            helper.setSubject("QR CODE");
	            helper.setFrom("faroukgasaraa@gmail.com");
	            FileSystemResource file = new FileSystemResource(attachment);
				helper.addInline(attachment.getName(), file);
	            mailSender.send(mimeMessage);
	        } catch (MessagingException e) {
	            LOGGER.error("failed to send email", e);
	            throw new IllegalStateException("failed to send email");
	        }
	         
	    }
		
	

}