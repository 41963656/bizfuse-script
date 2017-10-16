def statusReq = [status:"success", data:[id:5116,  toWorkTime:null]];

if(statusReq.status=="success" && (statusReq.data==null || statusReq.data.toWorkTime==null)){
	println "yes"
}else{
	println "no"
}


def ct =  new Date(1500597751169)

println "预计将在${(new Date(System.currentTimeMillis()+1000*60)).format('yyyy-MM-dd HH:mm:ss')}打卡"

Thread.sleep(10000);

println "$user"
