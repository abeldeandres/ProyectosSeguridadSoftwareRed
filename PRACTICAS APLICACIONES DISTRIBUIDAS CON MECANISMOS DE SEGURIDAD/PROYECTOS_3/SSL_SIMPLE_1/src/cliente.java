import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket sslsocket = (SSLSocket)sslsocketfactory.createSocket("127.0.0.1",9999);
			InputStream inputStream = System.in;
			InputStreamReader inputstreamreader= new InputStreamReader(inputStream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			
			OutputStream outputstream= sslsocket.getOutputStream();
			OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
			BufferedWriter bufferedwriter= new BufferedWriter(outputstreamwriter);
			
			InputStream inputstream2 = sslsocket.getInputStream();
			InputStreamReader inputstreamreader2= new InputStreamReader(inputstream2);
			BufferedReader bufferedreader2 = new BufferedReader(inputstreamreader2);
			String string = null;
			
			while(!(string=bufferedreader.readLine()).equals("")){
				bufferedwriter.write(string+'\n');
				bufferedwriter.flush();
				System.out.println(bufferedreader2.readLine());
			}
			sslsocket.close();
		} catch (Exception e) {
			e.printStackTrace( );
		}	
		
	}

}
