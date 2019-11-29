package com.tp.Utils;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class UploadUtils {

    @Value("${qiniu.accesskey}")
    private String accesskey;

    @Value("${qiniu.secretKey}")
    private String secretkey;

    @Value("${qiniu.bucket}")
    private String bucketname;

    public String upload(MultipartFile multipartFile){

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region1());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
/*        //...生成上传凭证，然后准备上传
        String accessKey = accesskey;
        String secretKey = secretkey;
        String bucket = bucketname;*/
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            byte[] bytes = multipartFile.getBytes();
            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(bytes);
            Auth auth = Auth.create(accesskey, secretkey);
            String upToken = auth.uploadToken(bucketname);
            try {
                Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return putRet.hash;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
