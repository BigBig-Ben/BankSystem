package Entity;

import java.sql.Date;

//定期存款
public class FixedDeposit {
    private int id;             //单号
    private String name;        //姓名
    private long phoneNumber;   //电话号码
    private String idNumber;    //身份证号码
    private int type;           //存款类型
    private Date depositDate;   //存款时间
    private Date dueDate;       //到期时间
    private double interestRate;//利率（存入时）
    private String password;    //密码
    private int amount;         //存款数目
    private int depositBank;   //储蓄银行

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(int depositBank) {
        this.depositBank = depositBank;
    }

    @Override
    public String toString() {
        return "FixedDeposit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", idNumber='" + idNumber + '\'' +
                ", type=" + type +
                ", depositDate=" + depositDate +
                ", dueDate=" + dueDate +
                ", interestRate=" + interestRate +
                ", password='" + password + '\'' +
                ", amount=" + amount +
                ", depositBank=" + depositBank +
                '}';
    }
}
