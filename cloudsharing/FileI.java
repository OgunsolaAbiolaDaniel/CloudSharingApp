package cloudsharing;

public interface FileI {
    String getName();

    int getFileSizeMb();

    String getOwnerEmail();

    boolean isShared();
}
