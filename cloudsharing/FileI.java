package cloudsharing;

public interface FileI {
    /**
     * @return the name of the file
     */
   public String getName();
    /**
     * @return the file size in mb
     * */
   public String getFileSize();
    /**
     * @return the name of the owner
     * */
    public String getOwner();
    /**
     * @return true or false if file is shareable
     * */
    boolean checkShareable();
}
