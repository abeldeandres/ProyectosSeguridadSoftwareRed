import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
public class servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.debug", "ssl");
		System.setProperty("javax.net.ssl.keyStore","AlmacenSR");
		System.setProperty("javax.net.ssl.keyStorePassword","oooooo");
		System.setProperty("javax.net.ssl.trustStore", "AlmacenSRTrust");

		try{
			//Se inicializa el Socket
			//Socket socket_sr;		
			//ServerSocket socket_servidor = new ServerSocket(9999);
			SSLServerSocketFactory sslserversocketfactory=(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			SSLServerSocket sslserversocket=(SSLServerSocket)sslserversocketfactory.createServerSocket(9999);

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
			//socket_sr = socket_servidor.accept();
			SSLSocket sslsocket=(SSLSocket)sslserversocket.accept();
			// LM
			System.out.println("CLIENTE CONECTADO ....... ");
			
			InputStream inputstream = sslsocket.getInputStream();
			InputStreamReader inputstreamreader= new InputStreamReader(inputstream);
			BufferedReader bufferedreader= new BufferedReader(inputstreamreader);
			String string = null;

			OutputStream outputstream = sslsocket.getOutputStream();
			OutputStreamWriter outpustreamwriter = new OutputStreamWriter(outputstream);
			BufferedWriter bufferedwriter = new BufferedWriter(outpustreamwriter);
		
		
			while((string= bufferedreader.readLine())!=null){
				System.out.println(string);
				Thread.currentThread().sleep(1000);
				bufferedwriter.write(string+'\n');
				bufferedwriter.flush();
			}
			sslserversocket.close();
		} catch (Exception e) {
			e.printStackTrace( );
		}

	}

}
