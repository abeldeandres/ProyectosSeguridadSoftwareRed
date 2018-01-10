import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Cliente {
	public static void main(String[] args) throws Exception {
		System.setProperty("javax.net.debug", "ssl");
		System.setProperty("javax.net.ssl.trustStore", "AlmacenCLTrust");
		System.setProperty("javax.net.ssl.keyStore","AlmacenCL");
		System.setProperty("javax.net.ssl.keyStorePassword","oooooo");
		SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket sslsocket = (SSLSocket)sslsocketfactory.createSocket("127.0.0.1",9999);
		
		File file = new File("SOCKETS_SEGUROS_JAVA.rar");
		long tamñoArchivo = file.length(); 
		
		//LM-21DIC16
		//BufferedInputStream bis = new BufferedInputStream(fis); 
		
		
		SSLSession sesion = sslsocket.getSession();
		System.out.println("Host: "+sesion.getPeerHost());
		
		X509Certificate certificate = (X509Certificate) sesion.getPeerCertificates()[0];
		
		byte[] buf = certificate.getEncoded();
		FileOutputStream os = new FileOutputStream("server.cer");
		
		os.write(buf);
		os.close();
		

		OutputStream Flujo_salida = sslsocket.getOutputStream();
		InputStream Flujo_entrada = sslsocket.getInputStream();

		DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
		DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);
		
		int NumBytesLeidos=0;
		long long_enviada_fich=0;
		
		Flujo_s.writeLong(tamñoArchivo);	
		byte[] contenido;
		contenido = new byte[64000]; 
		
		FileInputStream fis = new FileInputStream(file);
		
		do{
			System.out.println("Enviando fichero...");
			NumBytesLeidos=fis.read(contenido);
			long_enviada_fich=long_enviada_fich+NumBytesLeidos;
			Flujo_s.write(contenido,0,NumBytesLeidos);
			
			System.out.println("NumBytesLeidos"+NumBytesLeidos);
			System.out.println("Longitud enviada fich"+long_enviada_fich);
		}while(long_enviada_fich<tamñoArchivo);
		
		System.out.println("ENVIADO FICHERO LONG "+long_enviada_fich);
		
		//LM-21DIC16
		sslsocket.close();
		System.out.println("socket cerrado ");
		
	}
}
