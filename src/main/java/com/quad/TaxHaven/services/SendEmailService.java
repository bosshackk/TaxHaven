package com.quad.TaxHaven.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

	private static final Logger logger = LogManager.getLogger(SendEmailService.class);

	@Autowired
	private SendOTPService sendOTPService;

	@Autowired
	private JavaMailSender mailSender;

	public Boolean sendOTPOnMail(String clientMailId, String clientName, String sendingPurpose) {

		logger.info("Inside sendOTPOnMail() of SendOTP ");
//		Properties prop = new Properties();
//		prop.put("mail.smtp.host", smtpHost);
//		prop.put("mail.smtp.port", smtpPort);
//		prop.put("mail.smtp.auth", smtpAuth);
//		prop.put("mail.smtp.socketFactory.port", smtpSocketFactoryPort);
//		prop.put("mail.smtp.socketFactory.class", smtpSocketFactoryClass);

//		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(emailId, emailPassword);
//			}
//		});

		try {

////			Message message = new MimeMessage(session);
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setFrom("rksr224488@gmail.com");
////			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(clientMailId));
//			message.setSubject("For Email Verification via otp");
			Integer otp = sendOTPService.generateOTP(clientMailId);
//			if (sendingPurpose.equals("OTP")) {
//				message.setText("Dear " + clientName + "," + "\n\n Your OTP is:- " + otp + "\n\n Regards, \n LessTax");
//			} else {
//				message.setText(
//						"Hello Dear " + "," + "\n\n Your New Password is:- " + otp + "\n\n Regards, \n LessTax");
//			}
//
////			Transport.send(message);
//
//			mailSender.send(message);
//			logger.info("Exiting from sendOTPOnMail() of SendOTP");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("iamrawalkumar@gmail.com");
			message.setTo(clientMailId);
			message.setText(sendingPurpose + " otp is "+ otp);
			message.setSubject(sendingPurpose);
			mailSender.send(message);
			System.out.println("Mail Send...");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Got the error in sendOTPOnMail() of SendOTP :- " + e.getMessage());
			return false;
		}

	}

}
