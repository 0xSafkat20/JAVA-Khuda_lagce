public class Account {
    private String accNo;
    private String balance;

    // Constructor
    public Account(String accNo, String balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    // Getter methods
    public String getAccNo() {
        return accNo;
    }

    public String getBalance() {
        return balance;
    }

    // Setter methods (optional)
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
