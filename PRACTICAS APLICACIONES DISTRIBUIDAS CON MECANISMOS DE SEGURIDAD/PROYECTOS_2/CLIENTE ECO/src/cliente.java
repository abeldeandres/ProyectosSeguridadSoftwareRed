import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Socket socket = new Socket("127.0.0.1",9999);
			InputStream inputStream = System.in;
			InputStreamReader inputstreamreader= new InputStreamReader(inputStream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			
			OutputStream outputstream= socket.getOutputStream();
			OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
			BufferedWriter bufferedwriter= new BufferedWriter(outputstreamwriter);
			
			InputStream inputstream2 = socket.getInputStream();
			InputStreamReader inputstreamreader2= new InputStreamReader(inputstream2);
			BufferedReader bufferedreader2 = new BufferedReader(inputstreamreader2);
			String string = null;
			
			while(!(string=bufferedreader.readLine()).equals("")){
				bufferedwriter.write(string+'\n');
				bufferedwriter.flush();
				System.out.println(bufferedreader2.readLine());
			}
			socket.close(); 
		} catch (Exception e) {
			e.printStackTrace( );
		}	
		
	}

}
