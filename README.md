# 一、项目说明

## 1、文件上传

请求： http://10.1.20.105:8080/fileupload/uploadFile?a=org~101
```
	<form method="post" action="${ctx}/uploadFile?a=org~101" enctype="multipart/form-data">
		<input type="file" name="mf" >
		<input type="submit" value="上传" />
	</form>
```	
响应：{"result":"success","filename":"201906180901985423.jpg"}

## 2、文件访问

 http://10.1.20.105:8080/fileupload/images/201906180901985423.jpg?a=org~101

## 3、说明：

```
文件上传时，会根据a=org~101将文件保存到图片服务器 /home/www/upload/org/101 目录下
文件下载时,会根据a=org~101将  /images/201906180901985423.jpg 重定向到 /upload/org/101/201906180901985423.jpg
```

# 二、部署

## 1、修改tomcat/conf/context.xml中
<Context allowLinking="true">
	
## 2、创建/home/www/upload

## 3、创建软链接 

`ln -s /home/www/upload ${tomcat_home}/webapps/fileupload/upload`

`ln -s /home/www/upload webapps/fileupload/upload`
