

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteArchivoMas2G {
	public static void main(String[] args) throws Exception {
		Socket socket_cliente = new Socket("127.0.0.1",9999);


		File file = new File("SOCKETS_SEGUROS_JAVA.rar");
		long tamñoArchivo = file.length(); 
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis); 

		OutputStream Flujo_salida = socket_cliente.getOutputStream();
		InputStream Flujo_entrada = socket_cliente.getInputStream();

		DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
		DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);
		
		int NumBytesLeidos=0;
		long long_enviada_fich=0;
		
		Flujo_s.writeLong(tamñoArchivo);	
		byte[] contenido;
		contenido = new byte[64000]; 
		do{
			System.out.println("Enviando fichero...");
			NumBytesLeidos=fis.read(contenido);
			long_enviada_fich=long_enviada_fich+NumBytesLeidos;
			Flujo_s.write(contenido,0,NumBytesLeidos);
			
			System.out.println("NumBytesLeidos"+NumBytesLeidos);
			System.out.println("Longitud enviada fich"+long_enviada_fich);
		}while(long_enviada_fich<tamñoArchivo);
		
		System.out.println("ENVIANDO FICHERO LONG "+long_enviada_fich);
	}
}
