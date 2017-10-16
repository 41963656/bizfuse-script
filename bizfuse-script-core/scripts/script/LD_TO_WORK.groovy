import static groovyx.net.http.ContentType.JSON
import groovyx.net.http.RESTClient

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.MultiPartEmail

def MAIL_SERVER_HOST = "smtp.qq.com"
def MAIL_SERVER_PORT = 465
def MAIL_USERNAME = "41963656@qq.com"
def MAIL_PWD = "ftshavqizlxxcahj"
def MAIL_TO_ACC = "yuheng.chen@hotmail.com"
def MAIL_TITLE = "上班打卡通知"
def CHK_SUCCESS = "上班打卡成功"
def CHK_FAILD = "上班打卡失败"
def LOGIN_FAILD = "登录失败"
def CHK_RECHK = "上班记录已打卡"

def client = new RESTClient('http://123.207.164.144:8787')
def chkMsg

def randomMin = {
	int t= Math.round(Math.random()*25*60)+1
	int s= Math.round(Math.random()*1000)+1
	t * 1000 + s
}

def doPost = {path,json ->
	def request = client.post(path:path,body:json,requestContentType:JSON)
	println request.statusLine
	println request.responseData
	request.responseData
}

def doGet ={path,json ->
	def request = client.get(path:path,query:json,contentType:JSON)
	println request.statusLine
	println request.responseData
	request.responseData
}

def sendMsg = { msg ->
	println msg
	def email = new MultiPartEmail()
	email.setHostName(MAIL_SERVER_HOST)
	email.setSmtpPort(MAIL_SERVER_PORT)
	email.setAuthenticator(new DefaultAuthenticator(MAIL_USERNAME,MAIL_PWD))
	email.setSSLOnConnect(true)
	email.setFrom(MAIL_USERNAME)
	email.setSubject(MAIL_TITLE)
	email.setContent(msg.toString(),"text/plain;charset=UTF-8")
	email.addTo(MAIL_TO_ACC)
	email.send()
	println "打卡通知邮件已发送"
}

//等待时间
def setUpTime = randomMin()
sendMsg "准备将在${(new Date(System.currentTimeMillis()+setUpTime)).format('yyyy-MM-dd HH:mm:ss')}打卡"
Thread.sleep(setUpTime)

//认证
def authReq = doPost("/uap/login",["loginId":"chenyh","osVersion":"6.0.1","osType":2,"password":"123456","deviceType":1,"deviceId":"867148010187937"])
if(authReq.status=="success"){
	//获取状态
	def statusReq = doGet("/oa/m/attendances/today",null);
	if(statusReq.status=="success" && (statusReq.data==null || statusReq.data.toWorkTime==null)){
		//打卡
		def time  = System.currentTimeMillis();
		def offWorkReq = doPost("/oa/m/attendanceLogs",["locationDTO":["locationName":"河北省环保厅","locationDetail":"106","latitude":38.040978,"longitude":114.470548],"equipment":"MX6","commutingType":"TOWORK","baseLocationSettingId":11,"attDate":time])
		if(offWorkReq.status=="success"){
			chkMsg = CHK_SUCCESS
		}else{
			chkMsg = CHK_FAILD
		}
	}else{
		chkMsg = CHK_RECHK
	}
}else{
	chkMsg = LOGIN_FAILD
}
sendMsg chkMsg