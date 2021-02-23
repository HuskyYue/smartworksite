package com.slzhkj.smartworksite.server.service.impl;

import com.slzhkj.smartworksite.model.dto.FormatJsonDto;
import com.slzhkj.smartworksite.model.dto.ProvideMappersDto;
import com.slzhkj.smartworksite.model.mapper.DataBaseMapper;
import com.slzhkj.smartworksite.server.service.DataFormatService;
import com.slzhkj.smartworksite.server.utils.UniqId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>数据接入Service实现类</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月03日 11:10:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataFormatServiceImpl implements DataFormatService {

    @Resource
    DataBaseMapper dataBaseMapper;

    /**
     * url: /database/data/list
     * <h2>查询 数据库</h2>
     * @return
     */
    @Override
    public List<FormatJsonDto> getDataList(FormatJsonDto dto){
        return dataBaseMapper.getDataList(dto);
    }

    /**
     * <h2>数据接入数据库</h2>
     * @param dto
     * @return
     */
    @Override
    public List<FormatJsonDto> getUrlList(FormatJsonDto dto){
        return dataBaseMapper.getUrlList(dto);
    }


    /**
     * <h2>查询 表</h2>
     * @params keys FormatJsonDto  dataName 库名
     * @return
     */
    @Override
    public List<FormatJsonDto> getTableList(FormatJsonDto dto){
        List<FormatJsonDto> dataList=new ArrayList<>();
        List<FormatJsonDto> tableList=new ArrayList<>();
        if(dto!=null&&dto.getDataName()!=null&&!"".equals(dto.getDataName())){
            FormatJsonDto formatJsonDto=new FormatJsonDto();
            formatJsonDto.setDataName(dto.getDataName());
            dataList.add(formatJsonDto);
        }else{
            dataList.addAll(dataBaseMapper.getDataList(dto));
        }
        for (FormatJsonDto item:dataList) {
            item.setJsonData(dto.getJsonData());
            List<FormatJsonDto> formatJsonDtoList=dataBaseMapper.getTableList(item);
            tableList.addAll(formatJsonDtoList);
        }
        return tableList;
    }

    /**
     * <h2>查询 字段</h2>
     * @params keys FormatJsonDto  tableName 表名
     * @return
     */
    @Override
    public List<FormatJsonDto> getColumnList(FormatJsonDto dto){
        return dataBaseMapper.getColumnList(dto);
    }

    /**
     * <h2>保存 映射关系</h2>
     * @params
     * @return
     */
    @Override
    public  Integer saveMapper(ProvideMappersDto dto){
        dto.setId(UniqId.getInstanceWithLog().getUniqID());
        return dataBaseMapper.saveMapper(dto);
    }


}
