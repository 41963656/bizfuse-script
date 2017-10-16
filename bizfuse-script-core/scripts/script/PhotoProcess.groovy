import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.Metadata
import com.drew.metadata.exif.ExifSubIFDDescriptor
import com.drew.metadata.exif.ExifSubIFDDirectory

import groovy.io.FileType;

def copyFile = {srcFile , targDir ->
	targDir.mkdir()
	new File("${targDir}${File.separator}${srcFile.name}").withOutputStream{ writer ->
		srcFile.eachByte{b ->
			writer.write(b)
		}
	}
}
/*
 def isMove = { file ->
 file.name ==~ /(?i).*\.mov/ || file.name ==~ /(?i).*\.avi/
 }
 def isDb = { file ->
 file.name ==~ /(?i).*\.db/
 }
 def isCfg = { file ->
 file.name ==~ /(?i).*\.ini/ || file.name ==~ /(?i).*\.thm/
 }
 def isZoom = { file ->
 file.name ==~ /(?i).*\.\d+x\d+/
 }
 */
def isPhoto = { file ->
	file.name ==~ /(?i).*\.jpg/ || file.name ==~ /(?i).*\.bmp/
}

def getTargetDir = {file ->
	def name = file.name
	println "src:$name";
	def matcher = name =~ /.*?(20[0|1]\d{3}).+\..+/
	if(matcher){
		return matcher[0][1]
	}
	matcher = name =~ /^([0|1]\d[0|1]\d)\d{2}.*/
	if(matcher){
		return "20${matcher[0][1]}"
	}
	Metadata metadata = ImageMetadataReader.readMetadata(file)
	ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class)
	Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL ,TimeZone.getDefault())
	if(date){
		println "TimeStamp:${date.format('yyyy-MM-dd HH:mm:ss')}"
		return date.format('yyyyMM')
	}
	new Date(file.lastModified()).format('yyyyMM')
}

def doPhoto = { photoFiles ->
	def count=1
	def photoCount = photoFiles.size()
	photoFiles.each{ file ->
		try{
			def targetPath = getTargetDir(file)
			def targetDir = new File("d:\\photo\\${targetPath}");
			copyFile(file,targetDir)
			println "${file.name} copy success.  >> ${targetPath}"
		}catch(Exception e){
			println "文件${file.name}无效"
		}
		println "当前进度${(++count/photoCount*100).toInteger()}%"
	}
	println "[Fininsh]共计复制${count}个文件"
}

def sanDir ={ path ->
	def photoFiles =[]
	def dir  = new File(path)
	dir.eachFileRecurse(FileType.FILES){ file ->
		if(isPhoto(file)){
			photoFiles << file
		}
	}
	photoFiles
}

def photoFiles = sanDir "E:\\相片集"
doPhoto photoFiles