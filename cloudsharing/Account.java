package cloudsharing;

import dataStructures.Iterator;

public interface Account {
    String getEmail();

    String getTypeLabel();

    boolean canShareFiles();

    boolean hasOwnedFile(String fileName);

    FileI getOwnedFile(String fileName);

    boolean hasSharedCopy(String ownerEmail, String fileName);

    boolean canStoreOwnedFile(int fileSizeMb);

    boolean canStoreSharedFile(int fileSizeMb);

    void addOwnedFile(String fileName, int fileSizeMb);

    void addSharedFile(FileI file);

    Iterator<FileI> ownedFiles();

    Iterator<FileI> sharedFiles();
}
