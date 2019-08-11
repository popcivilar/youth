package com.popcivilar.youth.general.comment.service;

import com.popcivilar.youth.general.comment.entity.CommentInfo;
import com.popcivilar.youth.general.comment.entity.CommentInfoDto;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseService;

import java.util.List;

public interface CommentInfoService extends BaseService<CommentInfo> {


    UniPage listByParam(UniParam<CommentInfoDto> uniParam);

    ModuleReturn<List<CommentInfoDto>> getComment(CommentInfoDto articleInfoDto);
}
