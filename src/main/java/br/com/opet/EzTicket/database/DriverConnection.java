package br.com.opet.EzTicket.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverConnection {

    private Connection con;
    private PreparedStatement stm;

    public DriverConnection(Connection con, PreparedStatement stm) {
        this.con = con;
        this.stm = stm;
    }

    public Connection getConnection() {
        return this.con;
    }

    public PreparedStatement getStatement() {
        return this.stm;
    }

    public void roolback() {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(ResultSet result) {
        if (result != null) {
            try {
                if (!result.isClosed()) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (this.stm != null) {
            try {
                this.stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (this.con != null) {
            try {
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
