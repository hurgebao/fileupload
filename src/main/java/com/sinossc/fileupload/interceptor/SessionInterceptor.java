package com.sinossc.fileupload.interceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sinossc.stocktrade.common.utils.StringUtils;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	private Logger logger=LoggerFactory.getLogger("img");
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String a=request.getParameter("a");
        if(StringUtils.trim(a)==null){
        	ServletOutputStream outputStream = response.getOutputStream();
        	String msg="validate failed(0)";
        	outputStream.write(msg.getBytes());
        	outputStream.close();
        	return false;
        }
        String [] aa=a.split("~");
        if(aa==null || aa.length<2){
        	ServletOutputStream outputStream = response.getOutputStream();
        	String msg="validate failed(1)";
        	outputStream.write(msg.getBytes());
        	outputStream.close();
        	return false;
        }
		String uri=request.getRequestURI();
		String contextPath=request.getContextPath();

        if(uri.startsWith(contextPath+"/images/")){
        	String imgName=uri.substring(uri.lastIndexOf("/"), uri.length());
        	if(StringUtils.trim(imgName)==null){
        		ServletOutputStream outputStream = response.getOutputStream();
            	String msg="invalidate image";
            	outputStream.write(msg.getBytes());
            	outputStream.close();
            	return false;
        	}
        	imgName="/"+aa[0]+"/"+aa[1]+imgName;
        	logger.info("imgName:"+imgName);
        	request.getRequestDispatcher("/upload"+imgName).forward(request, response);
        	return false;
        }
		return true;
	}
}
