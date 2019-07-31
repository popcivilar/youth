package com.popcivilar.youth.admin.type.web;

import com.popcivilar.youth.admin.type.entity.TypeInfo;
import com.popcivilar.youth.admin.type.entity.TypeInfoDto;
import com.popcivilar.youth.admin.type.service.TypeInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/typeInfo")
public class TypeInfoController extends BaseController<TypeInfo, TypeInfoDto, TypeInfoService> {


    @Autowired
    private TypeInfoService typeInfoService;


    /**
     * 新增or修改
     * @param typeInfoDto
     * @return
     */
    @PostMapping("/save")
    public ModuleReturn<TypeInfoDto> save(TypeInfoDto typeInfoDto){
        ModuleReturn<TypeInfoDto> moduleReturn = ModuleReturn.success();
        TypeInfo typeInfo = new TypeInfo();
        BeanUtils.copyProperties(typeInfoDto,typeInfo);
        if(typeInfoDto.getId() == null || typeInfoDto.getId() == -1){
            //新增
            this.initalBean(typeInfo);
            typeInfoService.insertSelective(typeInfo);
        }else{
            //更新
            typeInfoService.updateByPrimaryKeySelective(typeInfo);
        }
        return moduleReturn;
    }


    /**
     *
     * @param req
     * @param typeInfo
     * @return
     */
    @GetMapping("/list")
    public UniPage selectUserInfoForLookup(HttpServletRequest req, TypeInfo typeInfo) {
        typeInfo.setDeletedFlag("0");
        UniParam<TypeInfo> uniParam = this.initUniParam(req, typeInfo);
        return typeInfoService.list(uniParam);
    }

}
