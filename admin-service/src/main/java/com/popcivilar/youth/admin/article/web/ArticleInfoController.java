package com.popcivilar.youth.admin.article.web;


import com.popcivilar.youth.admin.article.entity.ArticleInfo;
import com.popcivilar.youth.admin.article.entity.ArticleInfoDto;
import com.popcivilar.youth.admin.article.service.ArticleInfoService;
import com.popcivilar.youth.admin.user.entity.UserInfo;
import com.popcivilar.youth.admin.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleInfo")
public class ArticleInfoController extends BaseController<ArticleInfo,ArticleInfoDto, ArticleInfoService> {



    @Autowired
    private UserInfoService userInfoService;


    @GetMapping(value = "/getArticleInfo")
    public ModuleReturn<ArticleInfoDto> getArticleInfo(@RequestParam("id") Integer id){

        ModuleReturn<ArticleInfoDto> moduleReturn = this.getOne(StringUtil.objToString(id));

        if(moduleReturn.isSuccess() && moduleReturn.getData() != null){
            ArticleInfoDto articleInfoDto = moduleReturn.getData();
            UserInfo userInfo = userInfoService.selectByPrimaryKey(articleInfoDto.getId());
            if(userInfo != null){
                moduleReturn.getData().setUserInfo(userInfo);
            }
        }
        return moduleReturn;
    }

}
