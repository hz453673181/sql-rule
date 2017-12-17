package com.apache.calcite.sql.rule.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by hanningning on 17/12/15.
 */
public class ConnectionPoolFactory implements PooledObjectFactory<Connection> {

    private String jdbcUrl;

    public ConnectionPoolFactory(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public PooledObject<Connection> makeObject() throws Exception {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl);
        return new DefaultPooledObject<Connection>(connection);
    }

    public void destroyObject(PooledObject<Connection> pooledObject) throws Exception {
        Connection connection = pooledObject.getObject();
        if(connection != null){
            connection.close();
        }
    }

    public boolean validateObject(PooledObject<Connection> pooledObject) {
        Connection connection = pooledObject.getObject();
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void activateObject(PooledObject<Connection> pooledObject) throws Exception {

    }

    public void passivateObject(PooledObject<Connection> pooledObject) throws Exception {

    }
}
