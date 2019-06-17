

二、部署
1、修改tomcat/conf/context.xml中
<Context allowLinking="true">
2、创建/home/www/upload
3、创建软链接 ln -s /home/www/upload ${tomcat_home}/webapps/fileupload/upload