package com.popcivilar.youth.general.article.entity;

import com.popcivilar.youth.general.type.entity.TypeInfoDto;

import java.util.List;

/**
 * 公共文章相关
 * 
 * @author zhangch
 * 
 * @date 2019-07-07
 */
public class CommonArticleRel {

    /**
     * 点击排行
     */
    private List<ArticleInfoDto> rankingList;


    /**
     * 相关文章
     */
    private List<ArticleInfoDto> relativeList;

    /**
     * 标签云
     */
    private List<TypeInfoDto> typeInfoDtoList;

    public List<ArticleInfoDto> getRankingList() {
        return rankingList;
    }

    public void setRankingList(List<ArticleInfoDto> rankingList) {
        this.rankingList = rankingList;
    }

    public List<ArticleInfoDto> getRelativeList() {
        return relativeList;
    }

    public void setRelativeList(List<ArticleInfoDto> relativeList) {
        this.relativeList = relativeList;
    }

    public List<TypeInfoDto> getTypeInfoDtoList() {
        return typeInfoDtoList;
    }

    public void setTypeInfoDtoList(List<TypeInfoDto> typeInfoDtoList) {
        this.typeInfoDtoList = typeInfoDtoList;
    }
}