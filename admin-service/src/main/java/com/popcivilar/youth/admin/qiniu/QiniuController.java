package com.popcivilar.youth.admin.qiniu;

import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.common.pic.PicService;
import com.popcivilar.youth.youthbase.utils.QiniuUtils;
import com.popcivilar.youth.youthbase.utils.ResUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("/file")
public class QiniuController {

    @Autowired
    private PicService picService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModuleReturn<String> upload(@RequestParam("file") MultipartFile file,
                                            HttpServletRequest request,
                                            @RequestParam("picFlag") String picFlag) {
        ModuleReturn<String> moduleReturn = ModuleReturn.success();
        if(file.isEmpty()) {
            moduleReturn.setCode(ResUtils.ERROR);
            moduleReturn.setReturnMsg("文件为空");
            return moduleReturn;
        }
        try {
            String filePath = new QiniuUtils().uploadImg(file,picFlag);
            moduleReturn.setData(filePath);
            return moduleReturn;
        } catch (IOException e) {
            moduleReturn.setCode(ResUtils.ERROR);
            moduleReturn.setReturnMsg(e.getMessage());
            return moduleReturn;
        }
    }

    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public ModuleReturn<String> uploadPic(@RequestParam("file") MultipartFile file,
                                            @RequestParam("picFlag") String picFlag) {
        ModuleReturn<String> moduleReturn = this.upload(file,null,picFlag);
        if(moduleReturn.isSuccess() && moduleReturn.getData() != null ){
            picService.setPicList(moduleReturn.getData());
        }
        return moduleReturn;
    }
}
