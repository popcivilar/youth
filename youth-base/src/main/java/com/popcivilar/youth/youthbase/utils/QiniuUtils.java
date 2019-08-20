package com.popcivilar.youth.youthbase.utils;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class QiniuUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "0p2ST0QM-_d_xwbrwg2HP2fDv0LsYRwrvacpCsLQ";
    private static final String SECRET_KEY = "FHikAqr89hwIGqAPNj-gehWAISqBUyJWFP5QMdJT";

    // 要上传的空间名称
    private static final String BUCKETNAME = "blogshe";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    // 外链默认域名 测试域名，只有30天有效期
    private static final String QINIU_IMAGE_DOMAIN = "http://pveui46qk.bkt.clouddn.com/";

    // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
    Configuration cfg = new Configuration(Zone.zone2());
    // 其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(BUCKETNAME);
    }


    /**
     *
     * @param file
     * @param picFlag 系统缓存图片表示 1:是
     * @return
     * @throws IOException
     */
    public String uploadImg(MultipartFile file,String picFlag) throws IOException {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
            if("1".equals(picFlag)) {
                fileName = "PIC_" + fileName;
            }
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                logger.error("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
            logger.error("七牛异常:" + e.getMessage());
            return null;
        }
    }
}
