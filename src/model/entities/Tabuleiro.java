package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.exception.ExplosaoException;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampo();
		associarOsVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linhas, int colunas) {
		try {
			campos.stream()
			.filter(c -> c.getLinha()==linhas && c.getColuna()==colunas)
			.findFirst()
			.ifPresent(c -> c.abrir());
		} catch (ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}

	public void marcar(int linhas, int colunas) {
		campos.stream()
		.filter(c -> c.getLinha()==linhas && c.getColuna()==colunas)
		.findFirst().ifPresentOrElse(c -> c.alternarMarcacao(), null);
	}
	
	private void sortearMinas() {
		long minasArmadas = 0;
		
		do {
			int aleatorio = (int)(Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(c -> c.isMinado()).count();
		}while(minasArmadas < minas);
	}

	private void associarOsVizinhos() {
		for(Campo c1: campos) {
			for (Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void gerarCampo() {
		for (int i = 0; i <linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				campos.add(new Campo(i, j));
			}
			
		}
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		 campos.stream().forEach(c -> c.reiniciar());
		 sortearMinas();
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int c =0;
		
		sb.append("  ");
		
		for (int i = 0; i < colunas; i++) {
			sb.append("|");
			sb.append(i);
			sb.append("|");
		}
		
		sb.append("\n");
		
		for (int i = 0; i < linhas; i++) {
			sb.append(i);
			sb.append("|");
			for (int j = 0; j < colunas; j++) {
				sb.append("|");
				sb.append(campos.get(c));
				sb.append("|");
				c++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
}
