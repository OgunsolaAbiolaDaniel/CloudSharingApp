import cloudsharing.Account;
import cloudsharing.CloudSharing;
import cloudsharing.CloudSharing.AccountType;
import cloudsharing.CloudSharing.Result;
import cloudsharing.CloudSharingClass;
import cloudsharing.FileI;
import dataStructures.Iterator;

import java.util.Scanner;

public class Main {
    private static final String ADD = "ADD";
    private static final String UPLOAD = "UPLOAD";
    private static final String SHARE = "SHARE";
    private static final String LISTFILES = "LISTFILES";
    private static final String LISTALL = "LISTALL";
    private static final String EXIT = "EXIT";

    private static final String ACCOUNT_ADDED = "Account was added.";
    private static final String ACCOUNT_EXISTS = "Account already exists.";
    private static final String FILE_UPLOADED = "File uploaded into account.";
    private static final String FILE_EXISTS = "File already exists in the account.";
    private static final String EXCEEDS_CAPACITY = "File size exceeds account capacity.";
    private static final String FILE_SHARED = "File was shared.";
    private static final String ACCOUNT_NOT_FOUND = "Account does not exist.";
    private static final String SHARING_NOT_ALLOWED = "Account does not allow file sharing.";
    private static final String FILE_ALREADY_SHARED = "File already shared.";
    private static final String FILE_NOT_FOUND = "File does not exist.";
    private static final String ACCOUNT_FILES = "Account files:";
    private static final String ALL_ACCOUNTS = "All accounts:";
    private static final String EXITING = "Exiting...";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CloudSharing app = new CloudSharingClass();

        while (in.hasNext()) {
            String command = in.next().toUpperCase();
            switch (command) {
                case ADD -> handleAdd(in, app);
                case UPLOAD -> handleUpload(in, app);
                case SHARE -> handleShare(in, app);
                case LISTFILES -> handleListFiles(in, app);
                case LISTALL -> handleListAll(app);
                case EXIT -> {
                    System.out.println(EXITING);
                    in.close();
                    return;
                }
                default -> {
                    if (in.hasNextLine()) {
                        in.nextLine();
                    }
                }
            }
        }
        in.close();
    }

    private static void handleAdd(Scanner in, CloudSharing app) {
        String email = in.next();
        String typeToken = in.next().toLowerCase();
        AccountType type = "basic".equals(typeToken) ? AccountType.BASIC : AccountType.PREMIUM;
        Result result = app.addAccount(email, type);
        if (result == Result.SUCCESS) {
            System.out.println(ACCOUNT_ADDED);
        } else if (result == Result.ACCOUNT_EXISTS) {
            System.out.println(ACCOUNT_EXISTS);
        }
    }

    private static void handleUpload(Scanner in, CloudSharing app) {
        String email = in.next();
        String fileName = in.next();
        int sizeMb = in.nextInt();
        printUploadResult(app.upload(email, fileName, sizeMb));
    }

    private static void handleShare(Scanner in, CloudSharing app) {
        String ownerEmail = in.next();
        String receiverEmail = in.next();
        String fileName = in.next();
        printShareResult(app.share(ownerEmail, receiverEmail, fileName));
    }

    private static void handleListFiles(Scanner in, CloudSharing app) {
        String email = in.next();
        Account account = app.getAccount(email);
        if (account == null) {
            System.out.println(ACCOUNT_NOT_FOUND);
            return;
        }

        System.out.println(ACCOUNT_FILES);
        Iterator<FileI> ownedFiles = account.ownedFiles();
        while (ownedFiles.hasNext()) {
            printFile(ownedFiles.next());
        }

        Iterator<FileI> sharedFiles = account.sharedFiles();
        while (sharedFiles.hasNext()) {
            printFile(sharedFiles.next());
        }
    }

    private static void handleListAll(CloudSharing app) {
        System.out.println(ALL_ACCOUNTS);
        Iterator<Account> accountIterator = app.accounts();
        while (accountIterator.hasNext()) {
            Account account = accountIterator.next();
            System.out.println(account.getEmail() + " (" + account.getTypeLabel() + ")");
        }
    }

    private static void printFile(FileI file) {
        String output = file.getName() + " (" + file.getFileSizeMb() + " MB)";
        if (file.isShared()) {
            output += " (shared)";
        }
        System.out.println(output);
    }

    private static void printUploadResult(Result result) {
        switch (result) {
            case SUCCESS -> System.out.println(FILE_UPLOADED);
            case ACCOUNT_NOT_FOUND -> System.out.println(ACCOUNT_NOT_FOUND);
            case FILE_EXISTS -> System.out.println(FILE_EXISTS);
            case EXCEEDS_CAPACITY -> System.out.println(EXCEEDS_CAPACITY);
            default -> {
                // Not applicable to UPLOAD.
            }
        }
    }

    private static void printShareResult(Result result) {
        switch (result) {
            case SUCCESS -> System.out.println(FILE_SHARED);
            case ACCOUNT_NOT_FOUND -> System.out.println(ACCOUNT_NOT_FOUND);
            case EXCEEDS_CAPACITY -> System.out.println(EXCEEDS_CAPACITY);
            case SHARING_NOT_ALLOWED -> System.out.println(SHARING_NOT_ALLOWED);
            case FILE_ALREADY_SHARED -> System.out.println(FILE_ALREADY_SHARED);
            case FILE_NOT_FOUND -> System.out.println(FILE_NOT_FOUND);
            default -> {
                // Not applicable to SHARE.
            }
        }
    }
}
