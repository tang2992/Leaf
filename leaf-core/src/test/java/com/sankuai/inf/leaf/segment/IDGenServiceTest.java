package com.sankuai.inf.leaf.segment;

import com.alibaba.druid.pool.DruidDataSource;
import com.sankuai.inf.leaf.IDGen;
import com.sankuai.inf.leaf.common.PropertyFactory;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.segment.dao.impl.IDAllocDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class IDGenServiceTest {
    IDGen idGen;
    DruidDataSource dataSource;
    @Before
    public void before() throws IOException, SQLException {
        // Load Db Config
        Properties properties = PropertyFactory.getProperties();

        // Config dataSource
        dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getProperty("leaf.jdbc.url"));
        dataSource.setUsername(properties.getProperty("leaf.jdbc.username"));
        dataSource.setPassword(properties.getProperty("leaf.jdbc.password"));
        dataSource.init();

        // Config Dao
        IDAllocDao dao = new IDAllocDaoImpl(dataSource);

        // Config ID Gen
        idGen = new SegmentIDGenImpl();
        ((SegmentIDGenImpl) idGen).setDao(dao);
        idGen.init();
    }
    @Test
    public void testGetId() {
        for (int i = 0; i < 100; ++i) {
            Result r = idGen.get("leaf-segment-test");
            System.out.println(r);
        }
    }
    @After
    public void after() {
       dataSource.close();
    }

    @Test
    public void bit() {
        long sequenceMask = ~(-1L << 12);
        System.out.println(sequenceMask);
    }
}
