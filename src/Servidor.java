import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        //Crear  ServerSockert en el puerto 5000
        final int PUERTO = 5100;
        //Cerrar todos los recursos con try with resources
        try (ServerSocket ss1 = new ServerSocket(PUERTO)) {
            System.out.println("Servidor eco en el puerto " + PUERTO);

            while (true) {
                //Aceptar una conexión
                try (Socket cliente = ss1.accept()) {
                    //Obtener Buffered Reader y Print Writter
                    BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

                    //Bucle leer linea convertir a mayúscula y enviar respuesta
                    String line;
                    boolean fin = false;
                    while (!fin && (line = in.readLine()) != null) {

                        //Leer en linea convertir a mayúscula enviar respuesta
                        line = line.toLowerCase();
                        fin = line.equals("fin");
                        if (!fin) {
                            System.out.println("Recibido: " + line);
                            out.println("Servidor : " + line.toUpperCase());
                        }else{
                            System.out.println("Cliente solicita cierre");
                            System.out.println("Esperando nuevo cliente");
                        }
                    }
                    System.out.println("Cliente finalizado");
                } catch (IOException e) {
                    System.out.println("Error en el servidor " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}