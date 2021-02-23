package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户mapper
 *
 * @author Yuezejian
 * @date 2020年 08月22日 19:55:24
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("table") String table,@Param("params") String params,@Param("values") String values);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectList();

    User selectByEmail(@Param("email") String email);

    List<User> selectNamesById(@Param("ids") String ids);

    int get();
}