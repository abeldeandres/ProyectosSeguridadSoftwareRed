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

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
public class servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 
		try{
			//Se inicializa el Socket
			//Socket socket_sr;		
			//ServerSocket socket_servidor = new ServerSocket(9999);
			SSLServerSocketFactory sslserversocketfactory=(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			SSLServerSocket sslserversocket=(SSLServerSocket)sslserversocketfactory.createServerSocket(9999);
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
