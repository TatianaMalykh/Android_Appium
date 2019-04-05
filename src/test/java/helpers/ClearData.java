package helpers;

import java.io.IOException;

public class ClearData {

    public void application(String app){
        String deleteCmd = "pm clear " + app;
        Runtime runtime = Runtime.getRuntime();
        try { runtime.exec(deleteCmd); }
        catch (IOException e) { e.printStackTrace(); }
    }
}
