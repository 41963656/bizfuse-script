import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.MultiPartEmail

//EmailAttachment attachment = new EmailAttachment();
//attachment.setPath("shengbo_stat_zhifu_result.txt");
//attachment.setDisposition(EmailAttachment.ATTACHMENT);
//attachment.setDescription("txt file");
//attachment.setName("result.txt");

MultiPartEmail email = new MultiPartEmail();
email.setHostName("smtp.qq.com");
email.setSmtpPort(465);
email.setAuthenticator(new DefaultAuthenticator("41963656@qq.com",
		"ftshavqizlxxcahj"));
email.setSSLOnConnect(true);
//email.setCharset("UTF-8");
email.setFrom("41963656@qq.com");
email.setSubject("中文测试");
email.setContent("中言内容","text/plain;charset=UTF-8");
email.addTo("yuheng.chen@hotmail.com");
email.send();
println "ok"