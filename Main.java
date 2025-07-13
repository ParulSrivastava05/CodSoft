public class Main {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(10000); // Starting with ₹10,000
        ATM atm = new ATM(myAccount);
        atm.start();
    }
}
