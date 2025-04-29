package com.example.init;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;

import com.example.persistencia.DBConnectionManager;
import com.example.persistencia.IDBConnectionManager;

public class DBInitializer {

    private IDBConnectionManager connMgr;

    public DBInitializer(IDBConnectionManager connMgr) {
        this.connMgr = connMgr;
    }

    public void initDB() {
        try (Connection conn = connMgr.getConnection();) {
            String sqlFile = this.getClass().getResource("/data.sql").getFile();
            RunScript.execute(conn, new FileReader(sqlFile));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DBInitializer(new DBConnectionManager()).initDB();
    }
}
