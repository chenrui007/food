package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Build;
import com.example.demo.mapper.BuildMapper;
import com.example.demo.service.IBuildService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenRui
 * @since 2019-04-22
 */
@Service
public class BuildServiceImpl extends ServiceImpl<BuildMapper, Build> implements IBuildService {

}
