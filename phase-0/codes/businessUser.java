// package codes;
// 
public class businessUser extends User {
    public String businessType;

    public businessUser(String username, String businessType, boolean isPublic) {
        // only called when a user registers
    }

    public void Post(Post post) {
        // posts a Post to the dataBase and handles promotions
        // specialized for business Users
    }

    public void returnEngagementStats() {
        // gives detailed info on how the business is performing
    }
}