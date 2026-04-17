package cloudsharing;

import dataStructures.Array;
import dataStructures.ArrayClass;
import dataStructures.Iterator;

public abstract class AccountClass implements Account {
    private final String email;
    private final int capacityMb;
    private final Array<FileI> ownedFiles;
    private final Array<FileI> sharedFiles;
    private int ownedSpaceMb;
    private int sharedSpaceMb;

    protected AccountClass(String email, int capacityMb) {
        this.email = email;
        this.capacityMb = capacityMb;
        this.ownedFiles = new ArrayClass<>();
        this.sharedFiles = new ArrayClass<>();
        this.ownedSpaceMb = 0;
        this.sharedSpaceMb = 0;
    }

    protected int getCapacityMb() {
        return capacityMb;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public boolean hasOwnedFile(String fileName) {
        return getOwnedFile(fileName) != null;
    }

    @Override
    public FileI getOwnedFile(String fileName) {
        Iterator<FileI> iterator = ownedFiles.iterator();
        while (iterator.hasNext()) {
            FileI file = iterator.next();
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    @Override
    public boolean hasSharedCopy(String ownerEmail, String fileName) {
        Iterator<FileI> iterator = sharedFiles.iterator();
        while (iterator.hasNext()) {
            FileI file = iterator.next();
            if (file.getOwnerEmail().equals(ownerEmail) && file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addOwnedFile(String fileName, int fileSizeMb) {
        ownedFiles.insertLast(new File(fileName, fileSizeMb, email, false));
        ownedSpaceMb += fileSizeMb;
    }

    @Override
    public void addSharedFile(FileI file) {
        sharedFiles.insertLast(new File(file.getName(), file.getFileSizeMb(), file.getOwnerEmail(), true));
        sharedSpaceMb += file.getFileSizeMb();
    }

    @Override
    public Iterator<FileI> ownedFiles() {
        return ownedFiles.iterator();
    }

    @Override
    public Iterator<FileI> sharedFiles() {
        return sharedFiles.iterator();
    }

    @Override
    public boolean canStoreOwnedFile(int fileSizeMb) {
        if (canShareFiles()) {
            return ownedSpaceMb + fileSizeMb <= capacityMb;
        }
        return (2 * (ownedSpaceMb + fileSizeMb)) + sharedSpaceMb <= 2 * capacityMb;
    }

    @Override
    public boolean canStoreSharedFile(int fileSizeMb) {
        if (canShareFiles()) {
            return true;
        }
        return (2 * ownedSpaceMb) + (sharedSpaceMb + fileSizeMb) <= 2 * capacityMb;
    }
}
