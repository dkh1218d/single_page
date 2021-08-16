package action.member;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import KISA.SHA256;
import action.Action;
import model.member.memberDAO;
import java.util.Base64;

public class MemberPwResetProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		HttpSession savetemp = request.getSession();
		
		SHA256 sha = new SHA256(request.getParameter("securitynumber").getBytes());
		String Securitynumber = Base64.getEncoder().encodeToString(sha.GetHashCode());
		String userid = request.getParameter("userid");
		
		memberDAO dao = memberDAO.getinstance();
		String email = dao.Email_Search(Securitynumber, userid);
		
		if(email.equals("Empty email")) {
			response.getWriter().write(email+"");
			return;
		}
		
		String host = "smtp.naver.com";
		String user = "jsl_40_skh@naver.com";		// 메일을 보낼 계정
		String password = "sdj0116a";	// 계정의 비밀번호
		String usermail = email;	// 메일 받을 주소
		
		Properties props = new Properties();	// 서버
		StringBuffer temp = new StringBuffer();	// 난수를 저장할 버퍼 생성
		Random random = new Random();	// 난수 생성
		//SMTP서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);		//pop3 설정
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		for(int x=0; x<6; x++) {
			int randIndex = random.nextInt(3);
			switch (randIndex) {
			case 0:
				// a-z
				temp.append((char)((int)(random.nextInt(26))+97));
				break;
			case 1:
				// A-Z
				temp.append((char)((int)(random.nextInt(26))+65));
			case 2:
				// 0-9
				temp.append(random.nextInt(10));
			}
		}
		String tempKey = temp.toString();	// 임시번호 난수
		System.out.println(tempKey);
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,password);
			}
		});
		
		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user, "JSL"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usermail));
			String body = " 안녕하세요. \n 회원님의 비밀번호를 변경하기 위해 인증번호를 발급해 드립니다. \n\n 발급한 인증번호를 인증창에 입력해 주세요 \n 인증번호 : " + temp + "\n\n 이용해 주셔서 감사합니다.\n\n\n © JANGSU. All    Rights Reserved.";
			// mail header
			msg.setSubject("KH비밀번호 변경 메일입니다");
			// mail text
			msg.setText(body);
			Transport.send(msg);
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		savetemp.setMaxInactiveInterval(1200);
		savetemp.setAttribute("tempKey", tempKey);
		savetemp.setAttribute("saveid", userid);
		savetemp.setAttribute("Securitynumber", Securitynumber);
		response.getWriter().write(email+"");
	}

}
