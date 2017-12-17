package com.apache.calcite.sql.rule.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;

/**
 * Created by hanningning on 17/12/15.
 */
public class ConnectionProviderImpl implements ConnectionProvider{

    private static final String JDBC_URL = "jdbc:calcite:model=";
    private GenericObjectPool<Connection> connectionPool;

    public ConnectionProviderImpl() {
        String jsonConfigPath = ConnectionProviderImpl.class.getResource("/schemas.json").getPath();
        ConnectionPoolFactory connectionPoolFactory = new ConnectionPoolFactory(JDBC_URL+jsonConfigPath);
        connectionPool = new GenericObjectPool<Connection>(connectionPoolFactory);
        connectionPool.setMaxIdle(20);
        connectionPool.setMinIdle(5);
    }

    public Connection getConnection() {
        try{
            return connectionPool.borrowObject();
        } catch(Exception e){
            throw new RuntimeException("getConnection出现异常", e);
        }
    }

    public void returnConnection(Connection connection) {
        try{
            connectionPool.returnObject(connection);
        }catch (Exception e){
            throw new RuntimeException("returnConnection出现异常", e);
        }
    }
}
