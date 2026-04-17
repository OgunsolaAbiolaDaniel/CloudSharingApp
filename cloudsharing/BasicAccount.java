package cloudsharing;

public class BasicAccount extends AccountClass {
    private static final int BASIC_CAPACITY_MB = 2 * 1024;

    public BasicAccount(String email) {
        super(email, BASIC_CAPACITY_MB);
    }

    @Override
    public String getTypeLabel() {
        return "Basic";
    }

    @Override
    public boolean canShareFiles() {
        return false;
    }
}
