package com.slzhkj.smartworksite.server.utils;

import com.slzhkj.smartworksite.model.dto.FormatJsonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据库建表加锁工具类
 *
 * @author Yuezejian
 * @date 2020年 08月23日 10:10:55
 */
public class DataFormatUtils {
    // map转换成list
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List mapTransitionList(Map map) {
        List<FormatJsonDto> list = new ArrayList();
        Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
        while (iter.hasNext()) {
            FormatJsonDto dto=new FormatJsonDto();
            Map.Entry entry = (Map.Entry) iter.next();
            dto.setKey(entry.getKey().toString());
            list.add(dto);
        }
        return list;
    }
}

