package projectoCamisetas;



public class Camiseta {
	private static Long nextId = 1L;
	private Long id;
	private Long cantidad;
	private String color;
	private String marca;
	private String modelo;
	private String talla;
	
	public Camiseta(Long cantidad, String color, String marca, String modelo, String talla) {
		super();
		this.id = nextId++;
		this.cantidad = cantidad;
		this.color = color;
		this.marca = marca;
		this.modelo = modelo;
		this.talla = talla;
	}
	
	public Camiseta() {
		this.id = nextId++;
	}
	
	




	@Override
	public String toString() {
		return "Camiseta [id=" + id + ", cantidad=" + cantidad + ", color=" + color + ", marca=" + marca + ", modelo="
				+ modelo + ", talla=" + talla + "]";
	}

	public Long getId() {
		return id;
	}



	public Long getCantidad() {
		return cantidad;
	}



	public String getColor() {
		return color;
	}



	public String getMarca() {
		return marca;
	}



	public String getModelo() {
		return modelo;
	}



	public String getTalla() {
		return talla;
	}


	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	
	
	
}
