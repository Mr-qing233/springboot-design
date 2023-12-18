package com.example.service;

import com.example.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
public interface IInformationService extends IService<Information> {
    List<Information> findAll();
    List<Information> findAllLimit();
    Information getById(Integer id);
    boolean updateInfoById(Information information);
    boolean saveNewInfo(Information information);
    boolean deleteInfoById(Integer id);
}
