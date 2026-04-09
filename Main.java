import cloudsharing.CloudSharing;
import cloudsharing.CloudSharingClass;

import java.util.Scanner;



public class Main {
    /*Command Line Messages*/
    public static final String ADD_ACCT = "ADD";
    public static final String UPLOAD ="UPLOAD";
    public static final String SHARE_FILE= "SHARE";
    public static final String LISTFILES = "LISTFILES";
    public static final String LISTALL = "LISTALL";
    public static final String QUIT = "EXIT";

    // Constants defining messages for the user
    public static final String ADDED = "Account was added.";
    public static final String ACCT_EXISTS = "Account Already Exists";
    public static final String FILE_ADDED = "File uploaded into account.";
    public static final String FILE_EXISTS  = "File already exists in the account.";
    public static final String EXCEEDS_CAPACITY ="File size exceeds account capacity";
    public static final String FILE_SHARED = "File was shared.";
    public static final String ACCT_DNE = "Account does not exist.";
    public static final String NO_FILE_SHARING = "Account does not allow file sharing.";
    public static final String ALREADY_SHARED =  "File already shared.";
    public static final String ACCT_FILES = "Account files:";
    public static final String EXITING =  "Exiting...";
    public static final String NAME_NOT_EXIST = "Contact does not exist.";


    public static void main(String[] args) {
        //new cloud sharing Application;
        Scanner in= new Scanner(System.in);
        CloudSharing CloudSharingApp = new CloudSharingClass();
        String command;

        do {
            command=getComm(in);
            switch (command){
               case ADD_ACCT -> System.out.println(ADDED);
                case UPLOAD  ->System.out.println(FILE_ADDED);
                case SHARE_FILE -> System.out.println(FILE_SHARED);
                case LISTFILES ->System.out.println(ACCT_FILES);
                case LISTALL -> System.out.println("ALL USERS:");
            }

        }while (!command.equals(QUIT));
        in.close();


    }

    private static String getComm(Scanner in){
        String input;

        input= in.nextLine().toUpperCase();
        return input;
    }

    private static void add_Acct(Scanner in, CloudSharing CloudSharingApp){
        String name, type;
        name = in.nextLine();
        type = in.next();
        if(!CloudSharingApp.hasuser()){
            CloudSharingApp.
        }
        System.out.println(ACCT_EXISTS);


    };














}
