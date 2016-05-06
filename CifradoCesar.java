package ExamenCesar;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;
import static java.nio.file.StandardOpenOption.*;

public class CifradoCesar {

	public static void main(String[] args) {
		// Examen de cifrado cesar
		// primer ejercicio

		Scanner sc = new Scanner(System.in);

		Path ficheroentrada = Paths.get("mensaje.txt");

		Path ficherosalida = Paths.get("mensajeMay.txt");

		System.out.println(ficheroentrada.getFileName().toAbsolutePath());

		BufferedReader input = null;
		BufferedWriter output = null;

		// Charset charset = Charset.forName("UTF-8");

		try {

			input = Files.newBufferedReader(ficheroentrada);
			output = Files.newBufferedWriter(ficherosalida);

			String bufferIn = "";
			while ((bufferIn = input.readLine()) != null) {
				bufferIn = bufferIn.toUpperCase();
				output.write(bufferIn);
				output.newLine();//para que respeta los saltos de linea puestos en el .txt
			}
			input.close();
			output.close();

			System.out.println("introduce el numero de desplazamiento: ");
			int desplazamiento=sc.nextInt();
			codificarcesar(ficherosalida);
			cesarMejorado(ficherosalida,desplazamiento);
			visualizar(ficheroentrada);
			visualizar(ficherosalida);
			visualizar(Paths.get("codificado.txt"));
			visualizar(Paths.get("codificadoMejorado.txt"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/*
	 * lo de arriba es el primer ejercicio del examen, ya que convierta los
	 * datos de un archivos a mayusculas en otro.
	 */
	public static void visualizar(Path fichero) {
		BufferedReader input = null;

		try {
			// Creamos un BuffereReader de java.io
			input = Files.newBufferedReader(fichero);
			String bufferIn = "";
			while ((bufferIn = input.readLine()) != null) {
				System.out.println(bufferIn);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * con lo anterior, tenemos una funcion que nos sirva para vusializar el
	 * contenido de los ejercicios
	 */

	public static void codificarcesar(Path fichero) {
		Path salida = Paths.get("codificado.txt");
		InputStream istream = null;
		OutputStream ostream = null;
		int c;
		try {
			istream = Files.newInputStream(fichero);
			ostream = Files.newOutputStream(salida);
			while ((c = istream.read()) != -1) {
				if (c >= 65 && c <= 87 ) {//codigo ascii de las letras seleccionadas de la A-W
					c += 3;
				} else if (c == 88) {
					c = 65;
				} else if (c == 89) {
					c = 66;
				} else if (c == 90) {
					c = 67;
				}
				
				ostream.write(c);

				
			}
			istream.close();
			ostream.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}


/*
 * con este ejercicio codificamos el mesaje que teniamos y lo escribimos en
 * codificado.txt
 */
	public static void cesarMejorado(Path fichero, int desplazamiento) {
		Path salida2 = Paths.get("codificadoMejorado.txt");
		InputStream istream = null;
		OutputStream ostream = null;
		int c;
		try {
			istream = Files.newInputStream(fichero);
			ostream = Files.newOutputStream(salida2);
			while ((c = istream.read()) != -1) {
				if (c >= 65 && c <= 90- desplazamiento ) {//codigo ascii de las letras seleccionadas de la A-W
					c += desplazamiento;
				} else{
					int aux= c+desplazamiento-90;
					c=65+aux;
				}
				
				ostream.write(c);
				
			}
			istream.close();
			ostream.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	}