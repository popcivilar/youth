package com.popcivilar.youth.general.type.web;

import com.popcivilar.youth.general.type.entity.TypeInfo;
import com.popcivilar.youth.general.type.entity.TypeInfoDto;
import com.popcivilar.youth.general.type.service.TypeInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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



}
