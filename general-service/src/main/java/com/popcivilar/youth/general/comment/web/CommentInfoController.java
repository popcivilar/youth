package com.popcivilar.youth.general.comment.web;


import com.popcivilar.youth.general.comment.entity.CommentInfo;
import com.popcivilar.youth.general.comment.entity.CommentInfoDto;
import com.popcivilar.youth.general.comment.service.CommentInfoService;
import com.popcivilar.youth.general.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/commentInfo")
public class CommentInfoController extends BaseController<CommentInfo, CommentInfoDto, CommentInfoService> {

    @Autowired
    private CommentInfoService commentInfoService;



    @GetMapping(value = "/listByParam")
    public UniPage listByParam(HttpServletRequest request, CommentInfoDto articleInfoDto){
        UniParam<CommentInfoDto> uniParam = super.initUniParam(request, articleInfoDto);
        return commentInfoService.listByParam(uniParam);

    }


    @GetMapping(value = "/getComment")
    public ModuleReturn<List<CommentInfoDto>> getComment(CommentInfoDto commentInfoDto){
        return commentInfoService.getComment(commentInfoDto);
    }

    @PostMapping("/saveComment")
    public ModuleReturn<List<CommentInfoDto>> saveComment(CommentInfoDto commentInfoDto){
        ModuleReturn<List<CommentInfoDto>> moduleReturn = ModuleReturn.success();
        //1.新增
        commentInfoDto.setCommentDate(new Date());
        ModuleReturn<CommentInfoDto> saveModule = super.save(commentInfoDto);
        if(saveModule.isSuccess()){
            //2.查询评论信息
            commentInfoDto = new CommentInfoDto();
            commentInfoDto.setArticleId(commentInfoDto.getArticleId());
            ModuleReturn<List<CommentInfoDto>> commentModule = this.getComment(commentInfoDto);
            if (commentModule.isSuccess() && commentModule.getData() != null) {
                moduleReturn.setData(commentModule.getData());
            }
        }
        return moduleReturn;
    }

}
