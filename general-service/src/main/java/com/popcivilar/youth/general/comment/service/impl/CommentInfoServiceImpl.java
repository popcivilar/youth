package com.popcivilar.youth.general.comment.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.popcivilar.youth.general.comment.dao.CommentInfoMapper;
import com.popcivilar.youth.general.comment.entity.CommentInfo;
import com.popcivilar.youth.general.comment.entity.CommentInfoDto;
import com.popcivilar.youth.general.comment.service.CommentInfoService;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentInfoServiceImpl extends BaseServiceImpl<CommentInfo> implements CommentInfoService {

    @Autowired
    private CommentInfoMapper commentInfoMapper;


    @Override
    public UniPage listByParam(UniParam<CommentInfoDto> uniParam) {
        Page page = PageHelper.startPage(uniParam.getPage(), uniParam.getPageSize());
        commentInfoMapper.listByParam(uniParam);
        return new UniPage(page);
    }


    @Override
    public ModuleReturn<List<CommentInfoDto>> getComment(CommentInfoDto commentInfoDto) {
        ModuleReturn<List<CommentInfoDto>> moduleReturn = ModuleReturn.success();
        //1.查询该文章下所有评论
        CommentInfo commentInfo = new CommentInfo();
        BeanUtils.copyProperties(commentInfoDto,commentInfo);
        commentInfo.setDeletedFlag("0");
        List<CommentInfo> commentInfoList = this.select(commentInfo);
        //2.处理返回数据结构
        List<CommentInfoDto> parentList = new ArrayList<>();//父评论集合
        List<CommentInfoDto> childList = new ArrayList<>();//子评论集合
        if(commentInfoList != null && commentInfoList.size() > 0){
            for (CommentInfo comment : commentInfoList) {
               commentInfoDto = new CommentInfoDto();
               BeanUtils.copyProperties(comment,commentInfoDto);
               if(comment == null || comment.getPartentId() == -1){
                   parentList.add(commentInfoDto);
               }else{
                   childList.add(commentInfoDto);
               }
            }
        }

        for (CommentInfoDto childComment : childList) {
            for(CommentInfoDto parentComment : parentList){
                if(parentComment.getId() == childComment.getPartentId()){
                    if (parentComment.getChildComment() == null) {
                        parentComment.setChildComment(new ArrayList<>());
                    }
                    parentComment.getChildComment().add(childComment);

                }
            }
        }
        moduleReturn.setData(parentList);
        return moduleReturn;
    }
}
