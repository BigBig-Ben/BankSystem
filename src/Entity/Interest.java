package Entity;

//利率
public class Interest {
    private int id;                 //利率表内id
    private String type;            //存款类型
    private double interestRate;    //利率
    private int mouths;             //存款时长

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getMouths() {
        return mouths;
    }

    public void setMouths(int mouths) {
        this.mouths = mouths;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", interestRate=" + interestRate +
                '}';
    }
}
