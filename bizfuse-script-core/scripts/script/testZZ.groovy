def getTargetDir = {name ->
	
	def matcher = name =~ /.*(20[0|1]\d{3}).*/
	if(matcher){
		return matcher[0][1]
	}
//	new Date(file.lastModified()).format('yyyyMM')
}

def isMove = { file ->
	file ==~ /(?i).*\.mov/ || file ==~ /(?i).*\.avi/
}


def name = "090725A003.jpg"
def matcher2 = name =~ /^([0|1]\d[0|1]\d)\d{2}.*/
if(matcher2){
	println matcher2[0][1]
}



println isMove("IMG20201302325543.MOV")


def va = 1.2312312312
println va.toInteger()