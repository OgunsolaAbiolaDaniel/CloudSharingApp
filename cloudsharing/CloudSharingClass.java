package cloudsharing;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;

public class CloudSharingClass implements CloudSharing {
    private final Array<Account> accounts;

    public CloudSharingClass() {
        this.accounts = new ArrayClass<>();
    }

    @Override
    public Result addAccount(String email, AccountType type) {
        if (getAccount(email) != null) {
            return Result.ACCOUNT_EXISTS;
        }
        Account account = type == AccountType.BASIC ? new BasicAccount(email) : new PremiumAccount(email);
        accounts.insertLast(account);
        return Result.SUCCESS;
    }

    @Override
    public Result upload(String email, String filename, int fileSizeMb) {
        Account account = getAccount(email);
        if (account == null) {
            return Result.ACCOUNT_NOT_FOUND;
        }
        if (account.hasOwnedFile(filename)) {
            return Result.FILE_EXISTS;
        }
        if (!account.canStoreOwnedFile(fileSizeMb)) {
            return Result.EXCEEDS_CAPACITY;
        }
        account.addOwnedFile(filename, fileSizeMb);
        return Result.SUCCESS;
    }

    @Override
    public Result share(String ownerEmail, String receiverEmail, String filename) {
        Account owner = getAccount(ownerEmail);
        Account receiver = getAccount(receiverEmail);
        if (owner == null || receiver == null) {
            return Result.ACCOUNT_NOT_FOUND;
        }
        if (!owner.canShareFiles()) {
            return Result.SHARING_NOT_ALLOWED;
        }

        FileI file = owner.getOwnedFile(filename);
        if (file == null) {
            return Result.FILE_NOT_FOUND;
        }
        if (receiver.hasSharedCopy(ownerEmail, filename)) {
            return Result.FILE_ALREADY_SHARED;
        }
        if (!receiver.canStoreSharedFile(file.getFileSizeMb())) {
            return Result.EXCEEDS_CAPACITY;
        }
        receiver.addSharedFile(file);
        return Result.SUCCESS;
    }

    @Override
    public Account getAccount(String email) {
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getEmail().equals(email)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Iterator<Account> accounts() {
        return accounts.iterator();
    }
}
