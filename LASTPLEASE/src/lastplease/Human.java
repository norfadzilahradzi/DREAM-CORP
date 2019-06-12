package lastplease;

public class Human {
    int ID;
    String userName, userPassword, userEncryptedName, userDecryptedName;
    double userRevenue, companyRevenue;

    public Human(int ID, double revenue, String userName) {
        this.ID = ID;
        this.userName = userName;
        this.userRevenue = revenue;
        this.companyRevenue = companyRevenue;
        if (ID == 0) {
            this.userEncryptedName = "Boss";
        }
    }
    
    //METHOD UNTUK ADMIN CHECK USER PUNYA REVENUE @ MAIN
    public void userRevenue() {
        System.out.println("Revenue : " + this.userRevenue);
    }
    
    //METHOD UNTUK ADMIN CHECK COMPANY PUNYA REVENUE @ MAIN
    public void companyRevenue() {
        System.out.println("Revenue : " + this.companyRevenue);
    }
    
    //METHOD UNTUK ADMIN CHECK USER PUNYA ENCRYPTED NAME @ MAIN
    public byte[] userEncrypt(String name) {
        return encrypt(name.getBytes(), 127);
    }
    
    //METHOD UNTUK DECRYPT NAME
    public byte[] userDecrypt(String name) {
        String enc = new String(encrypt(name.getBytes(), 127));
        return decrypt(enc.getBytes(), 127);
    }
        
    //ENCRYP
    public byte[] encrypt(byte[] NAME, int KEY) {
        byte[] enc = new byte[NAME.length];        
        for(int i = 0; i<NAME.length; i++){
            enc[i] = (byte)((i%2 == 0) ? NAME[i]+1: NAME[i]-1);            
        }
        return enc;
    }

    //DECRYPT
    public byte[] decrypt(byte[] ENCRYPTED_NAME, int KEY) {
        byte[] enc = new byte[ENCRYPTED_NAME.length];
        for(int i = 0; i<ENCRYPTED_NAME.length; i++){
            enc[i] = (byte)((i%2 == 0) ? ENCRYPTED_NAME[i]-1: ENCRYPTED_NAME[i]+1);            
        }
        return enc;
    }
    
    //METHOD UNTUK SET PASSWORD EXISTING USER
    public void userPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
