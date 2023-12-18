package com.example.mapper;

import com.example.entity.Information;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
@Mapper
public interface InformationMapper extends BaseMapper<Information> {
    @Select("""
            SELECT
            \tid,
            IF
            \t( LENGTH( title ) > 30, CONCAT( SUBSTR( `title`, 1, 30 ), '...' ), title ) AS title,
            IF
            \t( LENGTH( description ) > 30, CONCAT( SUBSTR( `description`, 1, 30 ), '...' ), `description` ) AS description,
            IF
            \t( LENGTH( `code` ) > 30, CONCAT( SUBSTR( `code`, 1, 30 ), '...' ), `code` ) AS `code`,
            IF
            \t( LENGTH( result ) > 30, CONCAT( SUBSTR( `result`, 1, 30 ), '...' ), result ) AS result\s
            FROM
            \tinformation""")
    List<Information> getAllLimit();
}
