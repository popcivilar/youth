package com.popcivilar.youth.admin.article.web;


import com.popcivilar.youth.admin.article.entity.ArticleInfo;
import com.popcivilar.youth.admin.article.entity.ArticleInfoDto;
import com.popcivilar.youth.admin.article.service.ArticleInfoService;
import com.popcivilar.youth.admin.type.entity.TypeInfo;
import com.popcivilar.youth.admin.userInfo.entity.UserInfo;
import com.popcivilar.youth.admin.userInfo.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/articleInfo")
public class ArticleInfoController extends BaseController<ArticleInfo,ArticleInfoDto, ArticleInfoService> {



    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ArticleInfoService articleInfoService;


    @GetMapping(value = "/getArticleInfo")
    public ModuleReturn<ArticleInfoDto> getArticleInfo(@RequestParam("id") Integer id){

        ModuleReturn<ArticleInfoDto> moduleReturn = this.getOne(StringUtil.objToString(id));

        if(moduleReturn.isSuccess() && moduleReturn.getData() != null){
            ArticleInfoDto articleInfoDto = moduleReturn.getData();
            UserInfo userInfo = userInfoService.selectByPrimaryKey(articleInfoDto.getUserId());
            if(userInfo != null){
                moduleReturn.getData().setUserInfo(userInfo);
            }
        }
        return moduleReturn;
    }

    /**
     * 文章保存
     * @param articleInfoDto
     * @return
     */
    @PostMapping(value = "/save")
    public ModuleReturn<ArticleInfoDto> save(ArticleInfoDto articleInfoDto){
        ModuleReturn<ArticleInfoDto> moduleReturn = ModuleReturn.success();
        //处理换行符 \n ==> <br>
        if(StringUtil.isNotNullOrEmpty(articleInfoDto.getMarkdownContent())){
            String replaceStr = articleInfoDto.getMarkdownContent().replaceAll("(\n)", "<br>");
            articleInfoDto.setMarkdownContent(replaceStr);
        }
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleInfoDto,articleInfo);
        if(articleInfo.getId() == null || articleInfo.getId() == -1){
            //新增
            this.initalBean(articleInfo);
            articleInfoService.insertSelective(articleInfo);
        }else{
            //更新
            articleInfoService.updateByPrimaryKeySelective(articleInfo);
        }
        return moduleReturn;
    }

    @GetMapping(value = "/listByParam")
    public UniPage listByParam(HttpServletRequest request,ArticleInfoDto articleInfoDto){
        UniParam<ArticleInfoDto> uniParam = super.initUniParam(request, articleInfoDto);
        return articleInfoService.listByParam(uniParam);

    }

}
