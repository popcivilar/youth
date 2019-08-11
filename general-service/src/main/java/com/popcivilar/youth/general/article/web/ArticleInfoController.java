package com.popcivilar.youth.general.article.web;


import com.popcivilar.youth.general.article.entity.ArticleInfo;
import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.general.article.service.ArticleInfoService;
import com.popcivilar.youth.general.comment.entity.CommentInfoDto;
import com.popcivilar.youth.general.comment.service.CommentInfoService;
import com.popcivilar.youth.general.user.entity.UserInfo;
import com.popcivilar.youth.general.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/articleInfo")
public class ArticleInfoController extends BaseController<ArticleInfo,ArticleInfoDto, ArticleInfoService> {



    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ArticleInfoService articleInfoService;

    @Autowired
    private CommentInfoService commentInfoService;


    @GetMapping(value = "/getArticleInfo/{id}")
    public ModuleReturn<ArticleInfoDto> getArticleInfo(@PathVariable("id") Integer id){

        ModuleReturn<ArticleInfoDto> moduleReturn = this.getOne(StringUtil.objToString(id));

        if(moduleReturn.isSuccess() && moduleReturn.getData() != null){
            ArticleInfoDto articleInfoDto = moduleReturn.getData();
            UserInfo userInfo = userInfoService.selectByPrimaryKey(articleInfoDto.getUserId());
            if(userInfo != null){
                articleInfoDto.setUserInfo(userInfo);
            }

            //查询评论信息
            CommentInfoDto commentInfoDto = new CommentInfoDto();
            commentInfoDto.setArticleId(id);
            ModuleReturn<List<CommentInfoDto>> commentModule = commentInfoService.getComment(commentInfoDto);
            if (commentModule.isSuccess() && commentModule.getData() != null) {
                articleInfoDto.setCommentInfoList(commentModule.getData());
            }

            //查询上下篇文章
            articleInfoDto = articleInfoService.preNextInfo(articleInfoDto);
            moduleReturn.setData(articleInfoDto);
        }

        return moduleReturn;
    }


    @GetMapping(value = "/listView")
    public UniPage listView(HttpServletRequest request, ArticleInfoDto articleInfoDto){
        UniParam<ArticleInfoDto> uniParam = super.initUniParam(request, articleInfoDto);
        return articleInfoService.listView(uniParam);

    }
}
