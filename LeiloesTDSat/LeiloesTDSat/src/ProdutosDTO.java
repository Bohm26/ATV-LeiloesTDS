
/**
 *
 * @author Adm
 */
public class ProdutosDTO {

	private Integer id;
	private String nome;
	private Integer valor;
	private String status;

	public ProdutosDTO() {
	}

	public ProdutosDTO(String nome, Integer valor, String status) {
		this.nome = nome;
		this.valor = valor;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return this.status;
	}
	
	public String getStatus(String formato) {
		return this.status + " (" + formato + ")";
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
