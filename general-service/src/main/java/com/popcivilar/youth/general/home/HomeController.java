package com.popcivilar.youth.general.home;

import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.general.article.entity.ArticleView;
import com.popcivilar.youth.general.article.service.ArticleInfoService;
import com.popcivilar.youth.general.type.entity.ArticleTabNav;
import com.popcivilar.youth.general.type.entity.TypeInfo;
import com.popcivilar.youth.general.type.entity.TypeInfoDto;
import com.popcivilar.youth.general.type.service.TypeInfoService;
import com.popcivilar.youth.general.website.entity.WebSiteInfo;
import com.popcivilar.youth.general.website.entity.WebSiteInfoDto;
import com.popcivilar.youth.general.website.service.WebSiteInfoService;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.token.TokenPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HomeController
 * @Description 首页
 * @Author zhangch
 * @Date 2019/7/15 15:51
 * @Version 1.0
 **/
@Controller
public class HomeController {

    @Autowired
    private ArticleInfoService articleInfoService;

    @Autowired
    private TypeInfoService typeInfoService;

    @Autowired
    private WebSiteInfoService webSiteInfoService;

    @Value("${general.indexArticleShowNum}")
    private int indexArticleShowNum;

    @TokenPass
    @GetMapping("/")
    public String index(){
        return "index";
    }


    /**
     * 首页 获取redis 储存信息
     * 1.点击排行
     * 2.标签云
     * 3.站点信息
     * 4.首页 文章
     * 5.首页 置顶博文
     * @return
     */
    @GetMapping("/gCacheIndex")
    @ResponseBody
    public ModuleReturn<Map<String, Object>> indexPageInfoFromRedis(){
        ModuleReturn<Map<String, Object>> moduleReturn = ModuleReturn.success();
        //1.点击排行
        List<ArticleInfoDto> articleInfoDtoList = articleInfoService.getArticleRankindInRedis();
        //2.标签云
        List<TypeInfo> typeCloudList = typeInfoService.getTypeInfoCloudInRedis();
        //3.站点信息
        WebSiteInfoDto webSiteInfoDto = webSiteInfoService.getWebSiteInfoInRedis();
        //4.首页- 最新博文 siz = 3
        List<ArticleView> articleIndexList = indexList();
        //5.首页- 置顶博文
        List<ArticleView> topArticleViews = queryTopArticle();
        //6.TAB-NAV
        List<ArticleTabNav> articleTabNav = typeInfoService.getArticleTabNav();
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("ARTICLE_RANK",articleInfoDtoList);
        returnMap.put("TYPE_CLOUD",typeCloudList);
        returnMap.put("WEBSITE_INFO",webSiteInfoDto);
        returnMap.put("ARTICLE_INDEX_LIST",articleIndexList);
        returnMap.put("TOP_ARTICLE_VIEWS",topArticleViews);
        returnMap.put("ARTICLE_TAB_NAV",articleTabNav);
        moduleReturn.setData(returnMap);
        return moduleReturn;
    }

    /**
     * 首页展示 无分页  仅展示${indexArticleShowNum}条
     * @param
     * @return
     */
    public List<ArticleView> indexList(){
        int indexShowNum = 0;
        if(indexArticleShowNum > 0){
            indexShowNum = indexArticleShowNum;
        }
        return articleInfoService.queryLatest(indexShowNum);
    }


    private List<ArticleView> queryTopArticle(){
        return articleInfoService.queryTopArticle();
    }

}
