package DAO;

import Entity.CurrentAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrentAccountDAO {
    //获得所有账户信息
    public static List<CurrentAccount> getAllAccount()
    {
        List<CurrentAccount> accounts = new ArrayList<CurrentAccount>();
        Connection conn = DBUtil.open();
        String sql = "select * from current_account";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                CurrentAccount ac = new CurrentAccount();
                ac.setId(rs.getInt("Cid"));
                ac.setName(rs.getString("Cname"));
                ac.setPhoneNumber(rs.getLong("Ctel"));
                ac.setIdNumber(rs.getString("Cid_number"));
                ac.setPassword(rs.getString("Cpwd"));
                ac.setAmount(rs.getInt("Camount"));
                accounts.add(ac);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return accounts;
    }


    //获取账户信息byid
    public static CurrentAccount getAccountById(int accountId)
    {
        Connection conn = DBUtil.open();
        String sql = "select * from current_account where Cid = ?";
        try {
            PreparedStatement ptsmt = conn.prepareStatement(sql);
            ptsmt.setInt(1, accountId);
            CurrentAccount ca = new CurrentAccount();
            ResultSet rs = ptsmt.executeQuery();
            if(rs.next())
            {
                ca.setId(rs.getInt("Cid"));
                ca.setName(rs.getString("Cname"));
                ca.setPhoneNumber(rs.getLong("Ctel"));
                ca.setIdNumber(rs.getString("Cid_number"));
                ca.setPassword(rs.getString("Cpwd"));
                ca.setAmount(rs.getInt("Camount"));
            }
            return ca;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return null;
    }


    //新建账户
    public static boolean addAccount(CurrentAccount ac)
    {
        Connection conn = DBUtil.open();
        String sql = "insert into current_account values(?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ac.getId());
            pstmt.setString(2, ac.getName());
            pstmt.setLong(3, ac.getPhoneNumber());
            pstmt.setString(4, ac.getIdNumber());
            if(!(ac.getPassword()==null))
                pstmt.setString(5, ac.getPassword());
            else pstmt.setString(5, "123456");
            pstmt.setInt(6, ac.getAmount());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
          DBUtil.close(conn);
        }
        return false;
    }


    //修改账户
    public static boolean updateAccount(CurrentAccount ac)
    {
        Connection conn = DBUtil.open();
        String sql = "update current_account set Cname = ?, Ctel = ?, Cid_number = ?, Cpwd = ?, Camount = ? where Cid = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ac.getName());
            pstmt.setLong(2, ac.getPhoneNumber());
            pstmt.setString(3, ac.getIdNumber());
            pstmt.setString(4, ac.getPassword());
            pstmt.setInt(5, ac.getAmount());
            pstmt.setInt(6, ac.getId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }


    //删除账户
    public static boolean deleteAccount(int accountId)
    {
        Connection conn = DBUtil.open();
        String sql = "delete from current_account where Cid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accountId);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }


    //存款
    public static boolean deposit(int accountId, int amount)
    {
        Connection conn = DBUtil.open();
        String sql = "update current_account set Camount = Camount+? where Cid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setInt(2, accountId);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }


    //取款
    public static boolean withdraw(int accountId, int amount)
    {
        Connection conn = DBUtil.open();
        String sql = "update current_account set Camount = Camount-? where Cid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, amount);
            pstmt.setInt(2, accountId);
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
