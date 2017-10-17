bizfuse-script
==============
## 项目说明
此项目使用springboot框架，主要用于执行groovy脚本。

## 项目启动说明
此项目支持rest请求调用及定时调用

## 项目结构
* bizfuse-script-parent
  * bizfuse-script-core
  * bizfuse-script-checkin
  * bizfuse-scheduler-checkin
  * bizfuse-rest-checkin
    
## 启动方式
* REST服务启动
  * 创建start_rest.sh<br/>
  nohup java -jar ./lib/bizfuse-rest-checkin-0.0.1-SNAPSHOT.jar --encoding=UTF-8 > rest.log&
  
* APP启动
  * 创建start_scheduler.sh<br/>
  nohup java -jar ./lib/bizfuse-scheduler-checkin-0.0.1-SNAPSHOT.jar --encoding=UTF-8 > scheduler.log&
  
* 测试groovy启动
  * 创建  test.properties和test.sh<br/>
 java -jar ./lib/bizfuse-script-checkin-0.0.1-SNAPSHOT.jar $1 $2 --encoding=UTF-8
  * 参数说明: <br/>
  	* $1为脚本名称<br/>
  	* $2为test.properties<br/>
  
