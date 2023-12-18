package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.config.exception.ServiceException;
import com.example.entity.Information;
import com.example.mapper.InformationMapper;
import com.example.service.IInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vo.ResultEnum;
import com.example.vo.ResultJson;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
@Service
public class IInformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements IInformationService {

    @Autowired
    private InformationMapper informationMapper;

    @Override
    public List<Information> findAll() {
        return list();
    }

    @Override
    public List<Information> findAllLimit() {
        return informationMapper.getAllLimit();
    }

    @Override
    public Information getById(Integer id) {
        return informationMapper.selectById(id);
    }

    @Override
    public boolean updateInfoById(Information information) {
        try{
            log.warn("Updating data...");
            informationMapper.updateById(information);
        }catch (Exception e){
            log.error(String.valueOf(e));
            throw new ServiceException(ResultEnum.UPDATEERROR);
        }
        return true;
    }

    @Override
    public boolean saveNewInfo(Information information) {
        try {
            information.setId(null);
            informationMapper.insert(information);
        }catch (Exception e){
            log.error(String.valueOf(e));
            throw new ServiceException(ResultEnum.SAVEERROR);
        }
        return true;
    }

    @Override
    public boolean deleteInfoById(Integer id) {
        try{
            informationMapper.deleteById(id);
        }catch (Exception e){
            log.error(String.valueOf(e));
            throw new ServiceException(ResultEnum.DELETEERROR);
        }
        return true;
    }
}
