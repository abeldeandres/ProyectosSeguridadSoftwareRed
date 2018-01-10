import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		try {				
			Socket socket_sr;		
			ServerSocket socket_servidor = new ServerSocket(9999);
			while(true){

				    socket_sr = socket_servidor.accept();
					OutputStream Flujo_salida = socket_sr.getOutputStream();
					InputStream Flujo_entrada = socket_sr.getInputStream();
					DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
					DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);
					int num_recibido=Flujo_e.readInt();	
					//System.out.println(num_recibido);
					
					byte[] Mensaje_recibido = new byte[num_recibido];
					int bytes_leidos = Flujo_e.read(Mensaje_recibido);
					System.out.println("Mensaje Recibido"+new String(Mensaje_recibido)+" leidos"+ bytes_leidos+" byttes");
					
			}
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

}
