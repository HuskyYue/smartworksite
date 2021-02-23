package com.slzhkj.smartworksite.server.config;

import com.liucf.dbrecord.Db;
import com.slzhkj.smartworksite.server.utils.db;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * <h1>
 *This is the database query subsystem data preload processor.
 * It will get all the table structures from the database
 * and store them in the {@link db}  tool class
 * The Method : db.setScheme() such as
 *  synchronized public static void setScheme(){
 *         List<String> tables = Db.query("show tables");
 *         Map<String,List<Map>> info = new HashMap<>();
 *         tables.forEach(table->{
 *             List<Map> tmp = fetch_all(String.format("show full columns from %s",table));
 *             info.put(table,tmp);
 *         });
 *         Map<String,List<String>> r = new HashMap<>();
 *         info.forEach((k,v)->{
 *             List<String> x = new ArrayList<>();
 *             v.forEach(item->{
 *                 x.add(String.valueOf(item.get("Field")));
 *             });
 *             r.put(k,x);
 *         });
 *         schmema = r;
 *     }
 * </h1>
 *
 * @author Yuezejian
 * @date 2020年 11月03日 15:04:35
 */
@Component
public class dbRecordRunner implements ApplicationRunner , Ordered {
    @Autowired
    Environment environment;
    private static final Logger logger = LoggerFactory.getLogger(dbRecordRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*启动mysql*/
        Db.init(environment.getProperty("datasource.url"),environment.getProperty("datasource.username"), environment.getProperty("datasource.password"));
        db.setScheme();
        db.syncMetadata();
        logger.info("========== Preloading of database table structure information completed! ===========");
    }


    /**
     * <h2>Makes the current class load with a higher priority</h2>
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
