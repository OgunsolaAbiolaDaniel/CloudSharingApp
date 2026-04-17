package cloudsharing;

public class PremiumAccount extends AccountClass {
    private static final int PREMIUM_CAPACITY_MB = 5 * 1024;

    public PremiumAccount(String email) {
        super(email, PREMIUM_CAPACITY_MB);
    }

    @Override
    public String getTypeLabel() {
        return "Premium";
    }

    @Override
    public boolean canShareFiles() {
        return true;
    }
}
