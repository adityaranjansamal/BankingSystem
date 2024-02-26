public class space_counter {
    protected static void setspaces(String str) {
        if (str.length() < 12) {
            for (int i = 0; i < 12 - str.length(); i++)
                System.out.print(" ");
        } else {
            System.out.println("Very Long Names. Can't Restore Formatting!\nExiting to Menu!");
            Manager.menu();
        }
    }
}
