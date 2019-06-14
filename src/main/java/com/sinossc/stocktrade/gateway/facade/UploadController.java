package com.sinossc.stocktrade.gateway.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sinossc.stocktrade.gateway.facade.util.FileUploadUtils;


/**
 * 充值管理
 */
@Controller
public class UploadController {
    private static Logger logger= LoggerFactory.getLogger(UploadController.class);



    /**  方法说明：App充值上传文件 */
    @ResponseBody
    @RequestMapping(value = "uploadFile")
    public String uploadFile(HttpServletRequest request) {
        try {
            // 获取参数
            Map<String, Object> params = new HashMap<String, Object>();
            String fileName = (String)request.getParameter("fileName"); //文件名称
            request.getParameter("username");
            request.getParameter("password");
            String model=request.getParameter("model");

            //上传视频
            List<MultipartFile> fileLists = FileUploadUtils.getFiles(request, "mf");
            System.out.println("uploadFile: fileLists:"+fileLists);

            //项目webapp根目录
            String webappRootPath = "/home/upload-file/"+model;

            //上传文件
            String fNo = ""+System.currentTimeMillis();
            String fileNameSys = FileUploadUtils.uploadPicture(fileLists, fNo, webappRootPath);


        } catch(Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    /**  方法说明：App充值上传文件 */
    @ResponseBody
    @RequestMapping(value = "image/{filename}")
    public String image(HttpServletRequest request,HttpServletRequest response) {
        try {
            // 获取参数
            Map<String, Object> params = new HashMap<String, Object>();
            String fileName = (String)request.getParameter("fileName"); //文件名称
            request.getParameter("username");
            request.getParameter("password");
            String model=request.getParameter("model");

            //上传视频
            List<MultipartFile> fileLists = FileUploadUtils.getFiles(request, "mf");
            System.out.println("uploadFile: fileLists:"+fileLists);

            //项目webapp根目录
            String webappRootPath = "/home/upload-file/"+model+"/"+fileName;

            //上传文件
            String fNo = ""+System.currentTimeMillis();
            String fileNameSys = FileUploadUtils.uploadPicture(fileLists, fNo, webappRootPath);


        } catch(Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
