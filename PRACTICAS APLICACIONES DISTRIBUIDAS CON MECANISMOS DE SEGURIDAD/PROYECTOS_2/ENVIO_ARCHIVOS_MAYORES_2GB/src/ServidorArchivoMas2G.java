

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorArchivoMas2G {
	public static void main(String[] args) throws Exception{

		//Se inicializa el Socket
		Socket socket_sr;		
		ServerSocket socket_servidor = new ServerSocket(9999);
		// LM
		System.out.println("SERVIDOR ECO ESPERANDO PTO 9999 ....... ");
		
		socket_sr = socket_servidor.accept();
		// LM
		System.out.println("CLIENTE CONECTADO ....... ");
		


		
	// LM 	BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		InputStream Flujo_entrada = socket_sr.getInputStream();
		OutputStream Flujo_salida = socket_sr.getOutputStream();
		DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
		DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);
		
		long bytesLlegan = Flujo_e.readLong(); //Recibimos la longitud
		//LM
		System.out.println("Recibiendo Longitud..."+bytesLlegan);
		
		
		byte[] contents = new byte[64000]; //Creamos un buffer para almacenar el contenido, con el tamaño del archivo
		
		int NumBytes_read=0;
		long NumBytesLeidos=0;
		
		
		FileOutputStream fos = new FileOutputStream("SOCKETS_SEGUROS_JAVARECIBIDO.rar");
		
		do{
			NumBytes_read=Flujo_e.read(contents);
			NumBytesLeidos=NumBytes_read+NumBytesLeidos;
		// LM 	bos.write(contents, 0, (int)bytesLlegan); //Escribimos los bytes en el fichero
		// LM	
			fos.write(contents,0,NumBytes_read);
			
			System.out.println("Recibiendo FicheroNNumBytes_read..."+NumBytes_read);
			System.out.println("Recibiendo FicheroNNumBytes_read..."+NumBytesLeidos);
			System.out.println("Recibiendo Fichero...");
			
		}while(NumBytesLeidos<bytesLlegan);
		
	
	// LM 	bos.flush(); 
		
		fos.close();
		
		socket_sr.close(); 

		System.out.println("Archivo guardado correctamente!");
	}
}
