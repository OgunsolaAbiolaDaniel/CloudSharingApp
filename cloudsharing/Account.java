package cloudsharing;

public interface Account {

    /**
     * @return the email of the user
     * */
    public String getEmail();
   /**
    * @return the name of the user
    */
    public String getName();
    /**
    * @return type of user
     */
    public  String getType();
    /**
     * @params email :
     * set the email of the account
     * */
    public  void setEmail(String email);
    /**
     * @params name;
     * set The name of the account;
     * */
    public void setName(String name);
    /**
     * @params <code>type</code> : sets the type of the Account;
     * */
    public void setType(String type);
}
