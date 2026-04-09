package cloudsharing;

public interface Account {

    /**
     * @return the email of the user
     * */
    public String getEmail();
   /**
    * @return the name of the user
    * */
    public String getName();
    /*
    * @return type of user
    * */
    public  String getType();
    public   void setEmail(String email);
    public void setName(String name);
    public void setType(String type);

}
