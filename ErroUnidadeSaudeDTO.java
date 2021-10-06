package igorgroup.desafiopandemia.controller.DTO;

public class ErroUnidadeSaudeDTO {

	private String campo;
	private String erro;
	
	public ErroUnidadeSaudeDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public String getErro() {
		return erro;
	}
	
}
