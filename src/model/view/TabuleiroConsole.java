package model.view;

import java.util.Scanner;

import model.entities.Tabuleiro;
import model.exception.ExplosaoException;
import model.exception.SairException;

public class TabuleiroConsole {
	
	Scanner sc = new Scanner(System.in);
	private Tabuleiro tab;

	public TabuleiroConsole(Tabuleiro tab) {
		this.tab = tab;
		
		executarJogo();
	}

	private void executarJogo() {
		
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				String respota = sc.nextLine();
				
				if("n".equalsIgnoreCase(respota)) {
					continuar = false;
				}
				else {
					tab.reiniciar();
				}
			}
		}
		catch (SairException e) {
			System.out.println("Tchau");
		}
		finally {
			sc.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while(!tab.objetivoAlcancado()) {
				
				System.out.println(tab);
				String digitado = capturarValorDigitado("Digite o valor de (X,Y): ");
				String[] pos = digitado.split(",");
				Integer x = Integer.parseInt(pos[0]);
				Integer y = Integer.parseInt(pos[1]);
				
				digitado = capturarValorDigitado("O que deseja fazer? " + 
				"Digite 1 - para abrir ou Digite 2 - para marcar: ");
					
				System.out.printf("Valores selecionados: %d e %d \n\n" , x , y);
				if(digitado.equals("1")) {
					tab.abrir(x, y);
				}else if (digitado.equals("2")) {
					tab.marcar(x, y);
				}
			}
		}
		catch (ExplosaoException e) {
			System.out.println(tab);
			System.out.println("Voce perdeu!!!");
		}
	}

	private String capturarValorDigitado(String msg) {
		System.out.print(msg);
		String entrada = sc.nextLine();
		
		if("sair".equalsIgnoreCase(entrada)) {
			throw new SairException();
		}
		return entrada;
	}
	
	
}
