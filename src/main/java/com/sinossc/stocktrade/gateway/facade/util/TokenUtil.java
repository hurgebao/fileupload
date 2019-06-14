package com.sinossc.stocktrade.gateway.facade.util;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinossc.stocktrade.common.utils.StringUtils;

public class TokenUtil {
	private static ConcurrentHashMap<String,Long> map = new ConcurrentHashMap<String,Long>();
	private static Logger logger=LoggerFactory.getLogger(TokenUtil.class);
	private static final String TOKEN_SALT = "!@#$%QWERTasdfgzxcvb";
	private static final Long expireTime= 9000000L;//150L*60L*1000L;
	public static String getToken(Long accountNo,String sourceSysCode) throws Exception {
		try {
			String token= MD5Util.MD5(TOKEN_SALT+sourceSysCode+accountNo+System.currentTimeMillis());
			map.put(token,System.currentTimeMillis()+expireTime);
			return token;
		} catch (Exception e) {
			logger.error("获取登陆令牌异常{}",e);
			throw new Exception("获取登陆令牌异常");
		}
	}
	public static boolean checkToken(String token){
		if( StringUtils.trim(token)==null || !map.keySet().contains(token) ){
			return false;
		}
		if(System.currentTimeMillis()<=map.get(token)){
			map.put(token,System.currentTimeMillis()+expireTime);
			return true;
		}else{
			map.remove(token);
			return false;
		}
	}
	public static void removeToken(String token){
		if( StringUtils.trim(token)==null || !map.keySet().contains(token)){
			return;
		}
		map.remove(token);
	}
}
