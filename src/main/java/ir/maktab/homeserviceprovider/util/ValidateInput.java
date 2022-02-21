package ir.maktab.homeserviceprovider.util;

public class ValidateInput {

    public static boolean validUserName(String userName) {
        String pattern = "[a-zA-z0-9._-]+";
        boolean matches = userName.matches(pattern);
        if (matches)
            return true;
        return false;
    }

    public static boolean validPassword(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        boolean matches = password.matches(pattern);
        if (matches)
            return true;
        return false;
        //region passCondition
        /* At least 8 chars
        Contains at least one digit
        Contains at least one lower alpha char and one upper alpha char
        Contains at least one char within a set of special chars (@#%$^ etc.)
        Does not contain space, tab, etc.*/
        //endregion
    }

    public static boolean validName(String name) {
        boolean matches = name.matches("^[a-zA-Z]\\w{2,20}$");
        if (matches)
            return true;
        return false;
    }

    public static boolean validPhone(String phone) {
        boolean matches = phone.matches("9((0[1-3]|5)|(1[0-9])|(2[0-2])|(3(1|[3-9]))|(9[0-1]))[0-9]{7}");
        if (matches)
            return true;
        return false;
    }

    public static boolean validEmail(String email) {
        boolean matches = email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
                "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        if (matches)
            return true;
        return false;
    }


}
