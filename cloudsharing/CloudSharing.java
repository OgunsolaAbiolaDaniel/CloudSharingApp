package cloudsharing;

public interface CloudSharing {

    public enum AccountType{
        BASIC,PREMIUM
    }
/**
@params receives an <code>email</code> and the account <code>type</code>

*/
    public void addAccount(String email, AccountType type);
    public void upload(String email, String filename, String fileSize);
    public void share(String owner, String receiver);
    /**
     * @param email : the users email
     * @return a list all owned and shared files of an account
    */
    public void ListFile(String email);
}

