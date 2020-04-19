package application;

import java.util.Scanner;

import model.entities.Tabuleiro;
import model.view.TabuleiroConsole;

public class Application {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
				
		System.out.println("Digite quantas linhas deseja:");
		int linhas = sc.nextInt();
		System.out.println("Digite quantas colunas deseja:");
		int colunas = sc.nextInt();
		System.out.println("Digite quantas minas deseja:");
		int minas = sc.nextInt();

		Tabuleiro tab = new Tabuleiro(linhas,colunas,minas);
		new TabuleiroConsole(tab);
	}
}
