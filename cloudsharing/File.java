package cloudsharing;

public class File implements FileI {
    private final String name;
    private final int sizeMb;
    private final String ownerEmail;
    private final boolean shared;

    public File(String name, int sizeMb, String ownerEmail, boolean shared) {
        this.name = name;
        this.sizeMb = sizeMb;
        this.ownerEmail = ownerEmail;
        this.shared = shared;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getFileSizeMb() {
        return sizeMb;
    }

    @Override
    public String getOwnerEmail() {
        return ownerEmail;
    }

    @Override
    public boolean isShared() {
        return shared;
    }
}
