import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			/*InputStreamReader Flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(Flujo);
			System.out.println("Introduzca la direccion del servidor");
			String direccion=teclado.readLine();
			System.out.println("Introduzca el puerto del servidor");
			int puerto=teclado.read();*/
			
			Socket socket_cliente = new Socket("127.0.0.1",9999);


			OutputStream Flujo_salida = socket_cliente.getOutputStream();
			InputStream Flujo_entrada = socket_cliente.getInputStream();

			DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
			DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);
			
			String mensaje ="mensaje cliente";
			int longitud_mensaje=mensaje.length();


			Flujo_s.writeInt(longitud_mensaje);
			byte[] Mensaje_enviar=new byte[longitud_mensaje];
			Mensaje_enviar=mensaje.getBytes();
			Flujo_s.write(Mensaje_enviar,0,longitud_mensaje);
			

			
		} catch (Exception e) {
			e.printStackTrace( );
		}		
	}

}
