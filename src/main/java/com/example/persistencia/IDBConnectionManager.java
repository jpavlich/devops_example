package com.example.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBConnectionManager {
    public Connection getConnection() throws SQLException;

}
