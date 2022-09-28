import java.awt.*;
import java.net.*;
import java.io.*;
public class Servidor {
    public static void main(String[]	args)	{
        try {
            int serverPort =	4444;
            ServerSocket listenSocket =	new	ServerSocket(	serverPort );
            while(	true )	{
                Socket	clientSocket =	listenSocket.accept(	);
                Connection	c =	new	Connection(	clientSocket );
            }
        }
        catch(	IOException e	)	{
            System.out.println("	Listen:	"	+	e.getMessage(	)	);
        }
    }
}
class Connection	extends Thread	{
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket	clientSocket;
    public Connection(	Socket	aClientSocket )	{
        try {
            clientSocket =	aClientSocket;
            in	=	new	ObjectInputStream(	clientSocket.getInputStream(	)	);
            out	=	new	ObjectOutputStream(	clientSocket.getOutputStream(	)	);
            this.start(	);
        }
        catch(	IOException e	)	{
            System.out.println(	"Connection:	"	+	e.getMessage(	));
        }
    }
    public void run(	)	{
        while (true){
            try {
                Point data	= (Point)	in.readObject(	);
                System.out.println(data);
            }
            catch (ClassNotFoundException e){
                System.out.println("ClassNotFoundException:	"	+	e.getMessage());
            }
            catch(	EOFException e	)	{
                System.out.println("EOF:	"	+	e.getMessage());
            }
            catch(	IOException e	)	{
                System.out.println("IO:	"	+	e.getMessage());
                break;
            }
        }
    }
}
