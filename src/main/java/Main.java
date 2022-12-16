import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final int PORT = 8989;

    public static void main(String[] args) throws Exception {

        MaxCategory maxCategory = new MaxCategory(new File("categories.tsv"));

        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            System.out.println("Сервер ожидает запрос");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    String bought = in.readLine().toLowerCase();
                    maxCategory.setListInCategory(bought);
                    // System.out.println(maxCategory.maxSumForPeriod());
                    out.println(maxCategory.maxSumForPeriod());
                    System.out.println("Ответ отправлен клиенту. Сервер ожидает следующий запрос");
                }
            }
        } catch (
                IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
