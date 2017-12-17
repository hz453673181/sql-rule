package com.apache.calcite.sql.rule.pool;

import java.sql.Connection;

/**
 * Created by hanningning on 17/12/15.
 */
public interface ConnectionProvider {

    Connection getConnection();

    void returnConnection(Connection connection);
}
