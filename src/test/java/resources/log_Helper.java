package resources;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class log_Helper {

    //StringBuilder Object
    static StringBuilder sb = new StringBuilder();

    //Logging_function_To_Log_an_Info
    public static void LogInfo(String msg) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        var tempMsg = dtf.format(now) + "\n" + " INFO: " + msg + "\n";
        sb.append(tempMsg);

        String localDir = System.getProperty("user.dir");
        FileWriter fw = new FileWriter(localDir + "\\logs\\stackTraceLog.txt");
        fw.write(String.valueOf(sb.append(tempMsg)));
        fw.close();
    }

    public void exception (Exception e) throws FileNotFoundException {
        String localDir = System.getProperty("user.dir");
        FileOutputStream fos = new FileOutputStream(new File(localDir + "\\logs\\stackTraceLog.txt"));
        PrintStream ps = new PrintStream(fos);
        e.printStackTrace(ps);
    }
}