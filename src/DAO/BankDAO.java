package DAO;

import Entity.Bank;
import Entity.CurrentAccount;
import Entity.Interest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {
    //储蓄所登录
    public static boolean login(int bankid, String password) {
        Connection conn = DBUtil.open();
        String sql = "select Bpwd from bank where Bid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bankid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String pwd = rs.getString(1);
                if (pwd.equals(password))
                    return true;
                else return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }

    //获取银行信息
    public static Bank getBankById(int bankid) {
        Bank bank = new Bank();
        Connection conn = DBUtil.open();
        String sql = "select * from bank where Bid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bankid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                bank.setId(rs.getInt("Bid"));
                bank.setAddress(rs.getString("Baddress"));
                bank.setName(rs.getString("Bname"));
                bank.setTelNumber(rs.getString("Btel"));
                bank.setPassword(rs.getString("Bpwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        return bank;
    }


    //修改储蓄所信息
    public static boolean updateBank(int dbid, String pw) {
        Connection conn = DBUtil.open();
        String sql = "update bank set Bpwd=? where Bid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pw);
            pstmt.setInt(2, dbid);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }

    //查询具体利率
    public static Interest getRateById(int rateId) {
        Connection conn = DBUtil.open();
        String sql = "select * from Interest where Iid = ?";
        try {
            PreparedStatement ptsmt = conn.prepareStatement(sql);
            ptsmt.setInt(1, rateId);
            Interest ca = new Interest();
            ResultSet rs = ptsmt.executeQuery();
            if (rs.next()) {
                ca.setId(rs.getInt("Iid"));
                ca.setType(rs.getString("Itype"));
                ca.setInterestRate(rs.getDouble("Irate"));
                ca.setMouths(rs.getInt("Imouth"));
            }
            return ca;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return null;
    }

    //获取利率表
    public static List<Interest> getInterestRates() {
        List<Interest> rates = new ArrayList<Interest>();
        Connection conn = DBUtil.open();
        String sql = "select * from interest";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Interest rate = new Interest();
                rate.setId(rs.getInt("Iid"));
                rate.setType(rs.getString("Itype"));
                rate.setInterestRate(rs.getDouble("Irate"));
                rate.setMouths(rs.getInt("Imouth"));
                rates.add(rate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return rates;
    }


    //修改利率
    public static boolean updateRate(int id, double rate) {
        Connection conn = DBUtil.open();
        String sql = "update interest set Irate=? where Iid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, rate);
            pstmt.setInt(2, id);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }

}
