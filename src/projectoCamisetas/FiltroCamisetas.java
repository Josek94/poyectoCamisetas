package projectoCamisetas;

public enum FiltroCamisetas {
	ID(0,"id",true),CANTIDAD(1,"cantidad",true),COLOR(2,"color",false),MARCA(3,"marca",false),TIPO_GENERO(4,"tipo/genero",false),TALLA(5,"talla",false);
	
	private int numeroDeColumna;
	private String nombreDelArchivo;
	private boolean camposNumerico;
	
	
	
	private FiltroCamisetas(int numeroDeColumna, String nombreDelArchivo, boolean camposNumerico) {
		this.numeroDeColumna = numeroDeColumna;
		this.nombreDelArchivo = nombreDelArchivo;
		this.camposNumerico = camposNumerico;
	}



	public int getNumeroDeColumna() {
		return numeroDeColumna;
	}



	public String getNombreDelArchivo() {
		return nombreDelArchivo;
	}



	public boolean isCamposNumerico() {
		return camposNumerico;
	}
	
	
	
}
