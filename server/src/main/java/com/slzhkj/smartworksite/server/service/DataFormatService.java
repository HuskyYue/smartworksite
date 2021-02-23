package com.slzhkj.smartworksite.server.service;

import com.slzhkj.smartworksite.model.dto.FormatJsonDto;
import com.slzhkj.smartworksite.model.dto.ProvideMappersDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>数据接入Service</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月03日 10:49:55
 */
@Service
public interface DataFormatService {

    List<FormatJsonDto> getDataList(FormatJsonDto dto);

    List<FormatJsonDto> getUrlList(FormatJsonDto dto);

    List<FormatJsonDto> getTableList(FormatJsonDto dto);

    List<FormatJsonDto> getColumnList(FormatJsonDto dto);

   Integer saveMapper(ProvideMappersDto dto);
}
