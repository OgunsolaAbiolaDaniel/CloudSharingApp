package cloudsharing;

import dataStructures.Iterator;

public interface CloudSharing {

    enum AccountType {
        BASIC, PREMIUM
    }

    enum Result {
        SUCCESS,
        ACCOUNT_EXISTS,
        ACCOUNT_NOT_FOUND,
        FILE_EXISTS,
        FILE_NOT_FOUND,
        SHARING_NOT_ALLOWED,
        FILE_ALREADY_SHARED,
        EXCEEDS_CAPACITY
    }

    Result addAccount(String email, AccountType type);

    Result upload(String email, String filename, int fileSizeMb);

    Result share(String ownerEmail, String receiverEmail, String filename);

    Account getAccount(String email);

    Iterator<Account> accounts();
}
