package com.example.controller;

import com.example.entity.Information;
import com.example.service.IInformationService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
@RestController
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private IInformationService iInformationService;

    /**
     * 获取有字符串限制的内容
     * @return List
     */
    @GetMapping
    public List<Information> findAllLimit(){
        return iInformationService.findAllLimit();
    }

    /**
     * 获取完整全部内容
     * @return List
     */
    @GetMapping("/detail")
    public List<Information> findAll(){
        return iInformationService.findAll();
    }

    /**
     * 根据id获取详细内容
     * @param id 题目id
     * @return Information
     */
    @GetMapping("/getById/{id}")
    public Information getById(@PathVariable Integer id){
        return iInformationService.getById(id);
    }

    /**
     * 根据id与实体类更新数据
     * @param information 数据
     * @return true
     */
    @PostMapping("/updateByInfoId")
    public boolean updateByInfoId(@RequestBody Information information){
        return iInformationService.updateInfoById(information);
    }

    /**
     * 添加新数据
     * @param information 数据信息
     * @return true
     */
    @PostMapping("/saveNewInfo")
    public boolean saveNewInfo(@RequestBody Information information){
        return iInformationService.saveNewInfo(information);
    }

    /**
     * 根据id删除数据
     * @param id 序号
     * @return true
     */
    @DeleteMapping("/deleteInfoById/{id}")
    public boolean deleteInfoById(@PathVariable Integer id){
        return iInformationService.deleteInfoById(id);
    }
}
