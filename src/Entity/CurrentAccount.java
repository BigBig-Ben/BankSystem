package Entity;

import java.util.Date;

//活期账户
public class CurrentAccount {
    private int id;             //账号
    private String name;        //姓名
    private long phoneNumber;   //电话号码
    private String idNumber;    //身份证号码
    private String password;    //密码
    private int amount;         //存款数目

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

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", idNumber='" + idNumber + '\'' +
                ", password='" + password + '\'' +
                ", amount=" + amount +
                '}';
    }
}
