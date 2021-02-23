package com.slzhkj.smartworksite.model.mapper;

import com.slzhkj.smartworksite.model.dto.RequestDto;
import com.slzhkj.smartworksite.model.dto.RequestIDDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 三方请求Mapper
 *
 * @author Yuezejian
 * @date 2020年 11月02日 13:56:29
 */
@Mapper
public interface RequestMapper {

   /**
    * 新增三方获取token的记录
    * @param dto
    * @return
    */
   int addRequestLog(RequestDto dto);

   /**
    * 通过appId和secret 查询三方记录信息
    *
    * @param appId
    * @param secret
    * @return
    */
   int getByAppIdAndSecret(@Param("appId") String appId,@Param("secret") String secret);

   /**
    * 更新三方获取token的记录
    *
    * @param dto
    * @return
    */
   int  updateRequestLog(RequestDto dto);

   /**
    * 查询 token 记录, 进行 redis 和 bloomfilter 数据预热
    * @return
    */
   List<RequestDto> selectTokenRecord();

   /**
    * 根据appid 三方获取token的记录
    *
    * @param appId
    * @return
    */
    int getByAppId(@Param("appId") String appId);

   /**
    * 是否为可申请 token 的用户
    *
    * @param appId
    * @param secret
    */
    int getByAppIdAndSecretForEnable(@Param("appId") String appId,@Param("secret") String secret);

   /**
    * 定时检查 token 失效，查询 token 生成记录，
    * @return
    */
   List<RequestIDDto> getAppIdAndUpdateTime();

   /**
    * 失效token进行逻辑删除
    * @return
    */
   int deleteTokenRecord(@Param("id") Integer id);
}
