package Entity;

//储蓄所
public class Bank {
    private int id;             //银行id
    private String name;        //银行名字
    private String address;     //银行地址
    private String telNumber;   //银行联系电话
    //private String code;        //银行代码
    private String password;    //银行登录密码

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
