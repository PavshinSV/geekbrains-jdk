package seminar01.server;

import java.io.*;

public class LogerTxt {
    private File logFile;

    public LogerTxt(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при попытке создания несуществующего файла\n" + e);
            }
        }
        this.logFile = file;
    }

    public void newLog(String message) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(logFile, true))) {
            br.append(message);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ошибка при попытки записи в файл. Файл не найден\n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Неожиданная ошибка при попытке записи в файл\n" + e);
        }
    }
}