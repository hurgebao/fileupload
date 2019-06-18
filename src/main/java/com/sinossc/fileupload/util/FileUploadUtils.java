package com.sinossc.fileupload.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.util.WebUtils;

public class FileUploadUtils {
	private static Logger logger=LoggerFactory.getLogger(FileUploadUtils.class);
    /**
     * 方法说明：上传保存附件(图片)
     * 创建人：范兴乾
     * 返回类型：void
     * 创建时间：2015-10-19 上午11:26:55
     */
    public static String uploadPicture(List<MultipartFile> files, String vNo, String appFilePath)   throws Exception {
        String fileName = vNo;
        /** 判断创建路径 */
        //判断目录是否存在(音频存放根目录)
        File fileAudioRootDir =new File(appFilePath);
        if(!fileAudioRootDir.exists()){
        	fileAudioRootDir.mkdirs();
        }
        /** 上传图片 */
        if (files != null && files.size() > 0) {
            //保存附件
            for(MultipartFile file : files) {
                if(file.getSize() > 0) {
                    String zh_file_name = file.getOriginalFilename();
                    fileName = StringUtil.getSeqNoPk() + zh_file_name.substring(zh_file_name.lastIndexOf("."), zh_file_name.length());
                    logger.info("原始文件名:{},自动生产的文件名:{}",zh_file_name,fileName);
                    //上传文件
                    uploadFile(file, appFilePath+"/"+fileName);
                }
            }
        }
        return fileName;
    }

    /**
     * 方法说明：上传文件
     * 创建人：范兴乾
     * 返回类型：void
     * 创建时间：2015-10-19 上午10:21:16
     */
    public static void uploadFile(MultipartFile file, String fileName)  throws Exception {
        /** 读取流文件 */
        //tempfile 对象指向临时文件
        File tempFile = new File(fileName);
        //outputfile 文件输出流指向这个临时文件
        FileOutputStream outputStream = new FileOutputStream(tempFile);
        //得到客服端提交的所有数据
        InputStream fileSourcel = file.getInputStream();
        //将得到的客服端数据写入临时文件
        byte b[] = new byte[1000];
        int n ;
        while ((n=fileSourcel.read(b))!=-1){
            outputStream.write(b,0,n);
        }
        //关闭输出流和输入流
        outputStream.close();
        fileSourcel.close();
    }


    public static List<MultipartFile> getFiles(HttpServletRequest request, String paramName) {
        MultiValueMap<String, MultipartFile> multiValueMap = getAllMultipartFiles(request);
//        if (null != multiValueMap) {
            return (List)multiValueMap.get(paramName);
//        } else {
//            return null;
//        }
    }
    public static MultiValueMap<String, MultipartFile> getAllMultipartFiles(HttpServletRequest request) {
        MultipartRequest multipartRequest = (MultipartRequest) WebUtils.getNativeRequest(request, MultipartRequest.class);
        return multipartRequest != null ? multipartRequest.getMultiFileMap() : null;
    }


}
