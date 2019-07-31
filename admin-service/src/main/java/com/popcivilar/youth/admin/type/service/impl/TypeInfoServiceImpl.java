package com.popcivilar.youth.admin.type.service.impl;

import com.popcivilar.youth.admin.type.entity.TypeInfo;
import com.popcivilar.youth.admin.type.service.TypeInfoService;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeInfoServiceImpl extends BaseServiceImpl<TypeInfo> implements TypeInfoService {
}
