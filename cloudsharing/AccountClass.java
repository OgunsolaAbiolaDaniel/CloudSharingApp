package cloudsharing;

public abstract class AccountClass implements Account{
private String name;
private String email;
private String type;

public AccountClass(String name, String email){
this.name= name;
this.email=email;
}

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getName(){
    return (this.name);
}

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public void setName(String name) {
            this.name=name;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }


}
