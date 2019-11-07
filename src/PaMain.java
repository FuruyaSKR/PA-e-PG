import java.util.Scanner;

public class PaMain {

	public static void main(String[] args) {

		Pa progr = new Pa();
		boolean menu = true;
		Scanner entrada = new Scanner(System.in);

		System.out.println("Deseja importar algum arquivo já gerado?\nSIM - 1 / NÃO - 2");
		int importar = entrada.nextInt();

		switch (importar) {
		case 1:
			System.out.println("Digite o nome exato do arquivo, sem a sua extensao Ex: (.json)");
			Gravajson2 gravar = new Gravajson2(entrada.nextLine());
			progr = new Pa(gravar.ler());

			System.out.println("Deseja gerar um gráfico com os valores?\nSIM - 1 / NÃO - 2");
			int aux = entrada.nextInt();

			if (aux == 1) {
				gravar.geragrafico();
				System.out.println("Gráfico gerado");
			} else {
				System.out.println("OK");
			}
			
			break;
		}
		
		progr = new Pa();
		
		while(menu) {
			System.out.println("Criar PA - 1\nCriar PG - 2\nSair - 0");
			int a = entrada.nextInt();
			
			switch(a) {
			
			case 1:
				progr.setTipo(Progressao.aritmetica);
				break;

			case 2:
				progr.setTipo(Progressao.geometrica);
				break;
		
			case 3:
				menu = false;
				break;
			
			default:
				System.out.println("Opção Invalida, digite novamente!");
				break;
			}
				if (menu != false) {
					System.out.println("Ok! Agora digite qual será o valor inicial: ");
					progr.setA1(entrada.nextDouble());
					System.out.println("Digite agora a razão: ");
					progr.setRazao(entrada.nextDouble());
					System.out.println("Digite a quantidade de termos desejados: ");
					progr.setQuantidade(entrada.nextDouble());
					System.out.println("Qual será o nome do arquivo gerado?");
					Gravajson2 grava = new Gravajson2(entrada.next());
					progr.geraProg();
					grava.gravar(progr.getValores());
					grava.geragrafico();
				}
				
				System.out.println("Good Bye!");
			}
			
		entrada.close();
		
	}
	}
