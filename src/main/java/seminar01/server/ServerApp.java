package seminar01.server;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

public class ServerApp {
    public boolean isRunning = false;
    ServerAppWindow serverAppWindow;
    File logFile = new File("C:\\Users\\PavshinSV\\Documents\\Repos\\jdk\\src\\main\\java\\seminar01\\server\\server-log.txt");
    LogerTxt loger = new LogerTxt(logFile);

    public void run() {
        serverAppWindow = new ServerAppWindow(this);
    }

    public void log(String message) {
        message = (LocalDateTime.now()).toString() + ": " + message + "\n";
        serverAppWindow.printMessage(message);
        loger.newLog(message);
    }
}
