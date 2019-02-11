package org.edu.eci;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.imageio.ImageIO;

public class ServerWeb {

	public static void main(String[] args) {
		ServerSocket servidor = null;
		Socket cliente = null;
		BufferedReader in;
		PrintWriter out;
		String content = "text/html";

		final int PUERTO = 5000;

		try {
			servidor = new ServerSocket(getPort());
			System.out.println("Servidor Prendido");
			for (;;) {

				cliente = servidor.accept();
				System.out.println("Cliente Conectado");
				in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));// Recibir mensajes del cliente
				out = new PrintWriter(cliente.getOutputStream(), true);
				String mensaje;

				while ((mensaje = in.readLine()) != null) {
					System.out.println("Received: " + mensaje);
					if (!in.ready()) {
						break;
					}
				}

				System.out.println(mensaje);

				out.println("HTTP/1.1 200 OK");
				out.println("Content-Type: text/html" + "\r\n");
				out.println("<!DOCTYPE html>" + "\r\n");
				out.println("<html>" + "\r\n");
				out.println("<head>" + "\r\n");
				out.println("<meta charset=\"UTF-8\">" + "\r\n");
				out.println("<title>Title of the document</title>" + "\r\n");
				out.println("</head>" + "\r\n");
				out.println("<body>" + "\r\n");
				out.println("<h1>My Web Site</h1>" + "\r\n");
				out.println("</body>" + "\r\n");
				out.println("</html>" + "\r\n");
				
				out.close();
				in.close();
				cliente.close();

				/*
				 * if (mensaje != null) { System.out.println("HOLI ENTRE"); PrintWriter response
				 * = new PrintWriter(cliente.getOutputStream(), true);
				 * response.println("HTTP/1.1 200 OK");
				 * response.println("Content-Type: image/png"); response.println();
				 * BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir"),
				 * "image.png")); ImageIO.write(image, "png", cliente.getOutputStream());
				 * out.close(); in.close(); cliente.close();
				 * System.out.println("Cliente Desconectado"); } }
				 */
			}
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567;
	}

}
