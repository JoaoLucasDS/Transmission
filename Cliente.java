import java.awt.*;
import java.net.*;
import java.io.*;
public class Cliente {
    public static Point roboto(    ) {
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        //{mousePosition.getX(), mousePosition.getY()}
        return(mousePosition);
    }
    public static void main(String[] args) {
        Socket s = null;
        try {
            int serverPort = 5050;
            String computerName = InetAddress.getLocalHost().getHostName();
            s = new Socket(computerName, serverPort);
            System.out.println("Client connected");

            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            while(true){
                out.writeObject(roboto());
            }

        } catch (UnknownHostException e) {
            System.out.println("Socket:	" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("Não	é	possível	fechar	o	socket");
                }
            }
        }
    }
}