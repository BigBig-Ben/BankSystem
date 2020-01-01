package DAO;

import Entity.CurrentAccount;
import Entity.FixedDeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FixedDepositDAO {
    //查询所有定期存款
    public static List<FixedDeposit> getAllDeposit()
    {
        List<FixedDeposit> fixedDeposits = new ArrayList<>();
        String sql = "select * from fixed_deposit";
        Connection conn = DBUtil.open();
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                FixedDeposit fd = new FixedDeposit();
                fd.setId(rs.getInt("Fid"));
                fd.setName(rs.getString("Fname"));
                fd.setPhoneNumber(rs.getLong("Ftel"));
                fd.setIdNumber(rs.getString("Fid_number"));
                fd.setType(rs.getInt("Ftype"));
                fd.setDepositDate(rs.getDate("Fdeposit_date"));
                fd.setDueDate(rs.getDate("Fdue_date"));
                fd.setInterestRate(rs.getDouble("Frate"));
                fd.setPassword(rs.getString("Fpwd"));
                fd.setAmount(rs.getInt("Famount"));
                fd.setDepositBank(rs.getInt("Fbank_id"));
                fixedDeposits.add(fd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return fixedDeposits;
    }



    //查询某定期存款byId
    public static FixedDeposit getDepositById(int depositId)
    {
        Connection conn = DBUtil.open();
        String sql = "select * from fixed_deposit where Fid = ? ";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, depositId);
            FixedDeposit fd = new FixedDeposit();
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                fd.setId(rs.getInt("Fid"));
                fd.setName(rs.getString("Fname"));
                fd.setPhoneNumber(rs.getLong("Ftel"));
                fd.setIdNumber(rs.getString("Fid_number"));
                fd.setType(rs.getInt("Ftype"));
                fd.setDepositDate(rs.getDate("Fdeposit_date"));
                fd.setDueDate(rs.getDate("Fdue_date"));
                fd.setInterestRate(rs.getDouble("Frate"));
                fd.setPassword(rs.getString("Fpwd"));
                fd.setAmount(rs.getInt("Famount"));
                fd.setDepositBank(rs.getInt("Fbank_id"));
            }

                return fd;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }

        return null;
    }


    //新建定期存款
    public static boolean addDeposit(FixedDeposit fd, int bankId)
    {
        Connection conn = DBUtil.open();
        String sql = "insert into fixed_deposit values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fd.getId());
            pstmt.setString(2, fd.getName());
            pstmt.setLong(3, fd.getPhoneNumber());
            pstmt.setString(4, fd.getIdNumber());
            pstmt.setInt(5, fd.getType());
            pstmt.setDate(6, fd.getDepositDate());
            pstmt.setDate(7, fd.getDueDate());
            pstmt.setDouble(8, fd.getInterestRate());
            pstmt.setString(9, fd.getPassword());
            pstmt.setInt(10, fd.getAmount());
            pstmt.setInt(11, bankId);
            pstmt.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }


    //删除某项定期
    public static boolean deleteDeposit(FixedDeposit fd)
    {
        Connection conn = DBUtil.open();
        String sql = "delete from fixed_deposit where Fid = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fd.getId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn);
        }
        return false;
    }


    //修改某项定期信息
    public static boolean updateDeposit(FixedDeposit fd)
    {
        Connection conn = DBUtil.open();
        String sql ="update fixed_deposit set Fname = ?, Ftel = ?, Fid_number = ?, Ftype = ?, Fdeposit_date = ?" +
                ",Fdue_date = ?, Frate = ?, Fpwd = ?, Famount = ?, Fbank_id = ? where Fid = ?";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fd.getName());
            pstmt.setLong(2,fd.getPhoneNumber());
            pstmt.setString(3, fd.getIdNumber());
            pstmt.setInt(4, fd.getType());
            pstmt.setDate(5, fd.getDepositDate());
            pstmt.setDate(6, fd.getDueDate());
            pstmt.setDouble(7, fd.getInterestRate());
            pstmt.setString(8, fd.getPassword());
            pstmt.setInt(9, fd.getAmount());
            pstmt.setInt(10, fd.getDepositBank());
            pstmt.setInt(11, fd.getId());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        return false;
    }

}
