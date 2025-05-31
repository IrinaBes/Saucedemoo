package user;

import utils.PropertyReader;

public class UserFactory {

    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemoo.user"),
                PropertyReader.getProperty("saucedemoo.password"));
    }

    public static User withLockedUserPermission() {
        return new User(PropertyReader.getProperty("saucedemoo.locked.user"),
                PropertyReader.getProperty("saucedemoo.password"));
    }

    public static User withHRPermission() {
        return new User(PropertyReader.getProperty("hr-link.email.as.employeeHR"),
                PropertyReader.getProperty("hr-link.password.as.employeeHR"));
    }
}
