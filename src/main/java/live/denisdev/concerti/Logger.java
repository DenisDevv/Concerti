package live.denisdev.concerti;

public class Logger {
    public static void log(String message, boolean errore) {
        String time = java.time.LocalTime.now().toString();
        String coloreBlu = "\u001B[34m";
        String coloreBianco = "\u001B[0m";
        String coloreRosso = "\u001B[31m";
        message = message.replace("true", "Internazionale");
        message = message.replace("false", "Nazionale");
        if (errore) {
            System.out.println(time + " - " + coloreRosso + message + coloreBianco);
        } else {
            System.out.println(time + " - " + coloreBlu + message + coloreBianco);
        }
    }
}
