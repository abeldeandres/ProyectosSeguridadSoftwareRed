import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class cliente {

	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl");
		System.setProperty("javax.net.ssl.trustStore", "AlmacenCLTrust");
		System.setProperty("javax.net.ssl.keyStore","AlmacenCL");
		System.setProperty("javax.net.ssl.keyStorePassword","oooooo");
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
			
			SSLSession sesion = sslsocket.getSession();
			System.out.println("Host: "+sesion.getPeerHost());
			
			X509Certificate certificate = (X509Certificate) sesion.getPeerCertificates()[0];
			System.out.println("Propietario: "+certificate.getSubjectDN());
			System.out.println("Emisor: "+certificate.getIssuerDN());
			System.out.println("Numero Serie: "+certificate.getSerialNumber() );
			System.out.println("to string: "+certificate.toString()  );
			
			byte[] buf = certificate.getEncoded();
			FileOutputStream os = new FileOutputStream("server.cer");
			
			os.write(buf);
			os.close();
			
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
