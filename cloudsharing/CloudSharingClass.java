package cloudsharing;

public class CloudSharingClass implements CloudSharing {
   private  Account[] users;



public boolean hasUser(String name){

   for(int i = 0;i < users.length;i++){
     if (users[i].equals(name))
        break;
     return true;
   }
   return false;
}
}
