import java.io.*;
import java.net.URI;
import java.net.URLConnection;

public class Task5 {

    interface Task {
        void start();

        void stop();
    }

    public static class DownloadTask implements Task {
        private final String fileUrl;
        private final String savePath;

        private Thread downloadThread;
        private volatile boolean isRunning = false;
        private volatile boolean completed = false;
        private volatile InputStream currentStream;

        public DownloadTask(String fileUrl, String savePath) {
            this.fileUrl = fileUrl;
            this.savePath = savePath;
        }

        @Override
        public synchronized void start() {
            if (isRunning) {
                System.out.println("Скачивание уже запущено.");
                return;
            }
            isRunning = true;
            completed = false;

            downloadThread = new Thread(() -> {
                File destinationFile = new File(savePath);
                try {
                    URLConnection conn = URI.create(fileUrl).toURL().openConnection();
                    conn.setConnectTimeout(10_000);
                    conn.setReadTimeout(10_000);

                    try (BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
                         FileOutputStream fileOut = new FileOutputStream(destinationFile)) {

                        currentStream = in;
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while (isRunning && (bytesRead = in.read(buffer)) != -1) {
                            fileOut.write(buffer, 0, bytesRead);
                        }
                        completed = isRunning;
                    }
                } catch (IOException e) {
                    if (isRunning) {
                        System.err.println(e.getMessage());
                    }
                } finally {
                    currentStream = null;
                    isRunning = false;
                    if (completed) {
                        System.out.println("Скачивание успешно завершено.");
                    } else {
                        cleanUpResources(destinationFile);
                    }
                }
            });
            downloadThread.start();
        }

        @Override
        public void stop() {
            if (!isRunning) {
                return;
            }
            isRunning = false;
            InputStream s = currentStream;
            if (s != null) {
                try {
                    s.close();
                } catch (IOException ignored) {
                }
            }
        }

        private void cleanUpResources(File file) {
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Недокачанный файл удален.");
                } else {
                    System.out.println("Не удалось удалить недокачанный файл.");
                }
            }
        }
    }
}
