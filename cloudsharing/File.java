package cloudsharing;

import static cloudsharing.CloudSharing.AccountType.*;

public class File {
enum{
    BASIC, PREMIUM ;
    }
    private String name;
    private String size;
    private String ownerType;
    private String ownerName;
    private boolean shareable;

    public File(String name, String ownerName,String ownerType){
        this.name = name;
        this.ownerName=ownerName;
        this.ownerType= ownerType;
    }
}
