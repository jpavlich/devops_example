package com.example.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Cuenta;
import com.example.persistencia.IDBConnectionManager;

public class CuentaRepository {

    private IDBConnectionManager connMgr;

    public CuentaRepository(IDBConnectionManager connMgr) {
        this.connMgr = connMgr;
    }

    public Cuenta findCuentaById(Long cuentaId) {
        String query = "SELECT id, name, saldo FROM Cuenta WHERE id = ?";
        try (Connection conn = connMgr.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, cuentaId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Long id = rs.getLong("id");
                    String name = rs.getString("name");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    return new Cuenta(id, name, saldo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cambiarSaldo(Long cuentaId, BigDecimal monto) {

        String update = "UPDATE Cuenta SET saldo = saldo + ? WHERE id = ?";
        Connection conn = null;
        try {
            conn = connMgr.getConnection();
            PreparedStatement pstmtUpdate = conn.prepareStatement(update);

            conn.setAutoCommit(false);

            pstmtUpdate.setBigDecimal(1, monto);
            pstmtUpdate.setLong(2, cuentaId);
            int affectedRows = pstmtUpdate.executeUpdate();
            conn.commit();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
