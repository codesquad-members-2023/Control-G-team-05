package com.codesquad.controlG;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleanup implements InitializingBean {
    
    private final List<String> tableNames = new ArrayList<>();

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityManager entityManager;

    @Override
    public void afterPropertiesSet() {
        try {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
            ResultSet tables = metaData.getTables("controlG", null, null, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
        } catch (Exception e) {
            throw new RuntimeException("테이블 이름을 불러올 수 없습니다.");
        }
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        for (String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE `" + tableName + "`").executeUpdate();
        }

        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}
