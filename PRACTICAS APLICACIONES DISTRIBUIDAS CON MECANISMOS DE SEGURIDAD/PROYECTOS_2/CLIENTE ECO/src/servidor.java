import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 
		try{
			Socket socket;		
			ServerSocket socket_servidor = new ServerSocket(9999);
			socket = socket_servidor.accept();
		 InputStream inputstream = socket.getInputStream();
		 InputStreamReader inputstreamreader= new InputStreamReader(inputstream);
		 BufferedReader bufferedreader= new BufferedReader(inputstreamreader);
		 String string = null;
		 
		 OutputStream outputstream = socket.getOutputStream();
		 OutputStreamWriter outpustreamwriter = new OutputStreamWriter(outputstream);
		 BufferedWriter bufferedwriter = new BufferedWriter(outpustreamwriter);
		 
		 while((string= bufferedreader.readLine())!=null){
			 System.out.println(string);
			 Thread.currentThread().sleep(1000);
			 bufferedwriter.write(string+'\n');
			 bufferedwriter.flush();
		 }
		socket.close();
		} catch (Exception e) {
			e.printStackTrace( );
		}
		 
	}

}
