package com.sinossc.fileupload.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sinossc.fileupload.util.FileUploadUtils;
import com.sinossc.stocktrade.common.utils.StringUtils;


/**
 * 充值管理
 */
@Controller
public class UploadController {
    private static Logger logger= LoggerFactory.getLogger(UploadController.class);
    @Value("${image_base_url}")
    private String image_base_url;

    /**  方法说明：App充值上传文件 
     * 
     * uploadFile?a=(model~user)
     * model 功能概述
     * org 图片所属用户
     * 
     * 
     * */
    @ResponseBody
    @RequestMapping(value = "uploadFile")
    public String uploadFile(HttpServletRequest request) {
        try {
            // 获取参数
            Map<String, Object> params = new HashMap<String, Object>();
            String fileName = (String)request.getParameter("fileName"); //文件名称
            String a=request.getParameter("a");
            if(StringUtils.trim(a)==null){
            	return "validate failed(0)";
            }
            String [] aa=a.split("~");
            if(aa==null || aa.length<2){
            	return "validate failed(1)";
            }
            String model=aa[0];
            String user=aa[1];

            //上传视频
            List<MultipartFile> fileLists = FileUploadUtils.getFiles(request, "filename");
            if(fileLists==null || fileLists.size()<=0){
            	return "unsupport image type";
            }
            //项目webapp根目录
            String webappRootPath = image_base_url+"/"+model+"/"+user;

            //上传文件
            String fNo = ""+System.currentTimeMillis();
            String fileNameSys = FileUploadUtils.uploadPicture(fileLists, fNo, webappRootPath);
            logger.info("上传文件:"+fileNameSys);

        } catch(Exception e) {
        	logger.error("上传文件异常{}",e);
        	return "fail";
        }
        return "success";
    }
}
