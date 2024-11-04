package projectoCamisetas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;


public class gestorDeCamisetas {
	
	static Long contadorTotalDeCamisetas = 0L;
	static Long contadorDeCamisetasMalas = 0L;
	
	
	private static ArrayList<String> leerArchivo(String archivo) {
		ArrayList<String> listaCamisetas = new ArrayList<>();
			try(BufferedReader br = new BufferedReader(new FileReader("src\\files\\" + archivo))){
				String linea = "";
				while((linea = br.readLine()) != null) {
					listaCamisetas.add(linea);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		return listaCamisetas;
	}
	
	public static void generarReporteCompleto(String archivo) {
		ArrayList<String> listaSinDepurar = leerArchivo(archivo);
		generarReporteBueno(listaSinDepurar);
		generarReporteMalo(listaSinDepurar);
	}
	
	private static void generarReporteBueno(ArrayList<String> listaCamisetas) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src\\files\\camisetas_sin_errores_de_linea.txt"))){
			for(String linea : listaCamisetas) {
				String[] campos = linea.split(",");
				if(campos.length != 6) {
					contadorTotalDeCamisetas++;
					contadorDeCamisetasMalas++;
					continue;
				}
				boolean estaVacio = false;
				for(String campo : campos) {
					if(campo.trim().isEmpty()) {
						estaVacio = true;
					}
				}
				if(estaVacio) {
					contadorTotalDeCamisetas++;
					contadorDeCamisetasMalas++;
					continue;
				}
				contadorTotalDeCamisetas++;
				bw.append(linea);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void generarReporteMalo(ArrayList<String> listaCamisetas) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src\\files\\camisetas_con_errores_de_linea.log"))){
			bw.append(String.format("Camisetas_con_errores_de_linea.log%n"
					+ "Total lineas analizadas: %d%n"
					+ "Total lineas eliminadas: %d%n%n"
					+ "Las lineas eliminadas son: %n", contadorTotalDeCamisetas,contadorDeCamisetasMalas));
			for(String linea : listaCamisetas) {
				String[] campos = linea.split(",");
				if(campos.length != 6) {
					bw.append(linea);
					bw.newLine();
				}
				boolean estaVacio = false;
				for(String campo : campos) {
					if(campo.trim().isEmpty()) {
						estaVacio = true;
					}
				}
				if(estaVacio) {
					bw.append(linea);
					bw.newLine();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Camiseta> generarLaListaDeCamisetasClaseCamiseta(String archivo){
		ArrayList<String> listaDeCamisetasEnString = leerArchivo(archivo);
		ArrayList<Camiseta> listaDeCamisetasEnClaseCamiseta = new ArrayList<>();
		for(String linea : listaDeCamisetasEnString) {
			Camiseta camiseta = new Camiseta();
			String[] campos = linea.split(",");
			camiseta.setCantidad(Long.parseLong(campos[1]));
			camiseta.setColor(campos[2]);
			camiseta.setMarca(campos[3]);
			camiseta.setModelo(campos[4]);
			camiseta.setTalla(campos[5]);
			listaDeCamisetasEnClaseCamiseta.add(camiseta);
		}
		return listaDeCamisetasEnClaseCamiseta;
	}
	
	private static void internacionalizarDatos(String archivo) {
		ArrayList<String> lista = leerArchivo(archivo);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src\\files\\" + archivo))){
			for(String linea : lista) {
				String lineaInternacional = Normalizer.normalize(linea, Form.NFD);
				lineaInternacional = lineaInternacional.replaceAll("\\p{M}", "");
				lineaInternacional = lineaInternacional.toLowerCase();
				bw.append(lineaInternacional);
				bw.newLine();
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void reporteDeFrecuencias(String archivo, boolean esDespues) {
		ArrayList<Camiseta> lista = generarLaListaDeCamisetasClaseCamiseta(archivo);
		String ruta = "";
		if(esDespues) {
			ruta = "camiseta_frecuencias_despues.log";
		} else {
			ruta = "camiseta_frecuencias_antes.log";
			
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src\\files\\" + ruta))){
			
			Map<String, Long> mapaDeColores = lista.stream().
			map(Camiseta::getColor).
			sorted((c1, c2) -> c1.compareTo(c2)).
			collect(Collectors.groupingBy(color -> color,Collectors.counting()));
			
			bw.append(mapaDeColores.toString());
			bw.newLine();
			
			Map<Long, Long> mapaDeCantidad = lista.stream().
			map(Camiseta::getCantidad).
			sorted((c1, c2) -> c1.compareTo(c2)).
			collect(Collectors.groupingBy(cantidad -> cantidad,Collectors.counting()));
			
			bw.append(mapaDeCantidad.toString());
			bw.newLine();
			
			Map<String, Long> mapaDeMarca = lista.stream().
			map(Camiseta::getMarca).
			sorted((c1, c2) -> c1.compareTo(c2)).
			collect(Collectors.groupingBy(marca -> marca,Collectors.counting()));
			
			bw.append(mapaDeMarca.toString());
			bw.newLine();
			
			Map<String, Long> mapaDeModelo = lista.stream().
			map(Camiseta::getModelo).
			sorted((c1, c2) -> c1.compareTo(c2)).
			collect(Collectors.groupingBy(modelo -> modelo,Collectors.counting()));
			
			bw.append(mapaDeModelo.toString());
			bw.newLine();
			
			Map<String, Long> mapaDeTalla = lista.stream().
			map(Camiseta::getTalla).
			sorted((c1, c2) -> c1.compareTo(c2)).
			collect(Collectors.groupingBy(talla -> talla,Collectors.counting()));
			
			bw.append(mapaDeTalla.toString());
			bw.newLine();
			

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	
	public static void generarArchivofinal() {
		internacionalizarDatos("camisetas_sin_errores_de_linea.txt");
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src\\files\\camisetas_finales.txt"))){
			ArrayList<String> lista = leerArchivo("camisetas_sin_errores_de_linea.txt");
			for(String linea : lista) {
				bw.append(linea);
				bw.newLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void generarArchivoSql() {
		ArrayList<Camiseta> listaCamisetas = generarLaListaDeCamisetasClaseCamiseta("camisetas_finales.txt");
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src\\files\\camisetas.sql"))){
			bw.append(String.format("CREATE DATABASE camisetas;%n"));
			bw.append(String.format("show databases;%n"));
			bw.append(String.format("USE camisetas;%n"));
			bw.append(String.format("CREATE TABLE camisetas (cantidad LONG, color VARCHAR(30), marca VARCHAR(30), modelo VARCHAR(30), talla VARCHAR(30));%n"));
			bw.append(String.format("DESCRIBE camisetas;%n"));
			bw.append(String.format("INSERT INTO camisetas (cantidad, color , marca, modelo, talla) VALUES %n"));
			for(int i=0; i < listaCamisetas.size(); i++) {
				bw.append(String.format("('%d', '%s', '%s', '%s', '%s'),%n", 
					listaCamisetas.get(i).getCantidad(), listaCamisetas.get(i).getColor(), listaCamisetas.get(i).getMarca(), listaCamisetas.get(i).getModelo(),listaCamisetas.get(i).getTalla()));
			}
			bw.append(String.format("('%d', '%s', '%s', '%s', '%s');", 
					listaCamisetas.get(listaCamisetas.size()-1).getCantidad(), 
					listaCamisetas.get(listaCamisetas.size()-1).getColor(), 
					listaCamisetas.get(listaCamisetas.size()-1).getMarca(), 
					listaCamisetas.get(listaCamisetas.size()-1).getModelo(),
					listaCamisetas.get(listaCamisetas.size()-1).getTalla()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		generarReporteCompleto("camisetas_reducido.txt");
		reporteDeFrecuencias("camisetas_sin_errores_de_linea.txt", false);
		internacionalizarDatos("camisetas_sin_errores_de_linea.txt");
		reporteDeFrecuencias("camisetas_sin_errores_de_linea.txt", true);
		generarArchivofinal();
		generarArchivoSql();
		
	}
}
