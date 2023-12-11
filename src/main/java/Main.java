
public class Main {

    public static void main(String[] args) {
        
        UserService.registerUser("Bela", 123L, 5);

       
        UserService.startExpirationUpdater(1, "Bela", 123L);

        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        UserService.logoutUser("Bela");
    }
}
