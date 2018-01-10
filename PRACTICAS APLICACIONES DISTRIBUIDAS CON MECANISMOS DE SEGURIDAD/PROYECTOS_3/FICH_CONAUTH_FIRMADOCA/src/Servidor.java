import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Servidor {
	public static void main(String[] args) throws Exception{
		System.setProperty("javax.net.debug", "ssl");
		System.setProperty("javax.net.ssl.keyStore","AlmacenSR");
		System.setProperty("javax.net.ssl.keyStorePassword","oooooo");
		System.setProperty("javax.net.ssl.trustStore", "AlmacenSRTrust");

		SSLServerSocketFactory sslserversocketfactory=(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
		SSLServerSocket sslserversocket=(SSLServerSocket)sslserversocketfactory.createServerSocket(9999);
		sslserversocket.setNeedClientAuth(true);
		/**** FAMILIA DE CIFRADORES POR TECLADO ****/
		//Se utiliza la familia de cifradores 15 que funciona TLS_ECDHE_RSA_WITH_RC4_128_SH
		String [] cifradores= sslserversocket.getSupportedCipherSuites();
		for (int i=0; i<cifradores.length; i++){
		System.out.println("support:  "+cifradores[i]);
		}

		System.out.print("¿selecionar familia?: (s/n)" );
		Scanner sc1 =new Scanner(System.in);
		String str1= sc1.nextLine();
		String s ="s";
		String n ="n";

		if (s.equals(str1))
		{
		System.out.print("Familia Cifradores:" );
		Scanner sc =new Scanner(System.in);
		String familia_cifradores_str= sc.nextLine();
		int familia_cifradores= Integer.parseInt(familia_cifradores_str);

		String [] mycifrador  = {cifradores[familia_cifradores]};
		sslserversocket.setEnabledCipherSuites(mycifrador);

		//OBTENEMOS LA OPCION SELECCIONADA
		String [] cifradores_enable =sslserversocket.getEnabledCipherSuites(); 
		for (int i=0; i<cifradores_enable.length; i++){
		System.out.println("enable: "+cifradores_enable[i]);
		          }
		}

		else {
		System.out.println("Familia Negociada en la Sesion \n");
		}


		/**** FAMILIA DE CIFRADORES POR TECLADO ****/

		
		// LM
		System.out.println("SERVIDOR ECO ESPERANDO PTO 9999 ....... ");
		
		
		SSLSocket socket_sr=(SSLSocket)sslserversocket.accept();
		// LM
		System.out.println("CLIENTE CONECTADO ....... ");
		


		
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
			fos.write(contents,0,NumBytes_read);
			
			System.out.println("Recibiendo FicheroNNumBytes_read..."+NumBytes_read);
			System.out.println("Recibiendo FicheroNNumBytes_read..."+NumBytesLeidos);
			System.out.println("Recibiendo Fichero...");
			
		}while(NumBytesLeidos<bytesLlegan);
		
		
		fos.close();
		
		socket_sr.close(); 

		System.out.println("Archivo guardado correctamente!");
	}
}
