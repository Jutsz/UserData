
public class Main {

    public static void main(String[] args) {
        
        UserService.registerUser("user1", 123L, 5);

       
        UserService.startExpirationUpdater(1);

        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        UserService.logoutUser("user1");
    }
}
