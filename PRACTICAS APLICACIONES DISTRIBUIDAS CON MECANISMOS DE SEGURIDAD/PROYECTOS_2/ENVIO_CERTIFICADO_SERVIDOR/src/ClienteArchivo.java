

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ClienteArchivo {

	public static void main(String[] args) throws Exception {
		Socket socket_cliente = new Socket("127.0.0.1",9999);


		File file = new File("CACertificado.crt");
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis); 

		OutputStream Flujo_salida = socket_cliente.getOutputStream();
		InputStream Flujo_entrada = socket_cliente.getInputStream();

		DataOutputStream Flujo_s = new DataOutputStream(Flujo_salida);
		DataInputStream Flujo_e = new DataInputStream(Flujo_entrada);


		byte[] contenido;
		long tamñoArchivo = file.length(); 
		Flujo_s.writeLong(tamñoArchivo);
		contenido = new byte[(int) tamñoArchivo]; 
		bis.read(contenido, 0, (int) tamñoArchivo); 
		Flujo_salida.write(contenido);

		
		Flujo_salida.flush(); 
		socket_cliente.close();
		System.out.println("Archivo enviado correctamente!");
	}
}