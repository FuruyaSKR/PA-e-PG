import java.util.ArrayList;

public class Pa {

	private double razao, a1,quantidade;
	private double mediana, media, somatoria;
	private Progressao tipo;
	ArrayList<Double> valores = new ArrayList<Double>();
	
	public Pa() {

	}
	
	public Pa(ArrayList<Double> valores) {
		this.valores = valores;
		descobrevalor();
		descobreQuantidade();
		mediana();
		somatoria();
		analisearq();
		porcentalteracao(); 
	}
	
	public String porcentalteracao() {
		Double aux = 0.0;
		aux = analisearq()[0];
		if (aux == 1) {
			aux = analisaarqpg()[0];
			aux = (aux / valores.size()) * 100.0;
			this.razao = analisaarqpg()[1];
		} else if (aux != null) {
			aux = (aux / valores.size()) * 100.0;
			this.razao = analisearq()[1];
		}
		if (aux != 100.0) {
			setTipo(null);
		}
		String a = Double.toString(aux);
		return a;
	}
	
	public Double[] analisaarqpg() {
		ArrayList<Double> auxs = new ArrayList<Double>();
		Double[] auxer = new Double[valores.size()];
		for (int i = 0; i < auxer.length; i++) {
			auxer[i] = 0.0;
		}
		for (int i = 0; i < valores.size() - 1; i++) {
			Double dom = (Double) (valores.get(i + 1) / valores.get(i));
			if (!(auxs.contains(dom))) {
				auxs.add(dom);
				auxer[i] = auxer[i] + 1;
			} else {
				for (int j = 0; j < auxs.size(); j++) {
					if (auxs.get(j).equals(dom)) {
						auxer[j] = auxer[j] + 1;
					}
				}
			}
		}
		Double maior[] = new Double[2];
		maior[0] = 0.0;
		maior[1] = 0.0;
		for (int i = 0; i < auxer.length; i++) {
			if (auxer[i] > maior[0]) {
				maior[0] = auxer[i].doubleValue() + 1;
				maior[1] = (Double) auxs.get(i);
			}
		}
		return maior;
	}

	public Double[] analisearq() {
		ArrayList<Double> auxs = new ArrayList<Double>();
		Double[] auxer = new Double[valores.size()];
		for (int i = 0; i < auxer.length; i++) {
			auxer[i] = 0.0;
		}
		for (int i = 0; i < valores.size() - 1; i++) {
			Double dom = (Double) (valores.get(i + 1) - valores.get(i));
			if (!(auxs.contains(dom))) {
				auxs.add(dom);
				auxer[i] = auxer[i] + 1;
			} else {
				for (int j = 0; j < auxs.size(); j++) {
					if (auxs.get(j).equals(dom)) {
						auxer[j] = auxer[j] + 1;
					}
				}
			}
		}
		Double maior[] = new Double[2];
		maior[0] = 0.0;
		maior[1] = 0.0;
		for (int i = 0; i < auxer.length; i++) {
			if (auxer[i] > maior[0]) {
				maior[0] = auxer[i].doubleValue() + 1;
				maior[1] = (Double) auxs.get(i);
			}
		}
		return maior;
	}
	
	private void mediana() {
		if (valores.size() % 2 == 1) {
			mediana = valores.get(valores.size() / 2);
		} else {
			mediana = (valores.get(valores.size() / 2 - 1) + valores.get(valores.size() / 2 + 1) / 2);
		}
	}

	private void somatoria() {
		Double a = 0.0;
		for (int i = 0; i < valores.size(); i++) {
			a += valores.get(i);
		}
		somatoria = a;
	}
	
	private void descobrevalor() {
		a1 = valores.get(0);
	}

	private void descobreQuantidade() {
		quantidade = valores.size();
	}
	
	public void geraProg() {
		if (tipo == Progressao.aritmetica) {
			geraPa();
		} else if (tipo == Progressao.geometrica) {
			geraPg();
		}
	}
	
	public void geraPa() {
		ArrayList<Double> pa = new ArrayList<Double>();
		pa.add(a1);
		for(int i = 0; i < quantidade; i++) {
			a1 = a1 + razao;
			pa.add(a1);
		}
		valores = pa;
	}
	
	public void geraPg() {
		ArrayList<Double> pg = new ArrayList<Double>();
		pg.add(a1);
		for (int i = 1; i < quantidade; i++) {
			a1 = a1 * razao;
			pg.add(a1);
		}
		valores = pg;
	}
	
	public double getRazao() {
		return razao;
	}
	
	public void setRazao(double razao) {
		this.razao = razao;
	}
	
	public double getA1() {
		return a1;
	}
	
	public void setA1(double a1) {
		this.a1 = a1;
	}
	
	public double getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getMediana() {
		return mediana;
	}
	
	public void setMediana(double mediana) {
		this.mediana = mediana;
	}
	
	public double getMedia() {
		return media;
	}
	
	public void setMedia(double media) {
		this.media = media;
	}
	
	public double getSomatoria() {
		return somatoria;
	}
	
	public void setSomatoria(double somatoria) {
		this.somatoria = somatoria;
	}
	
	public Progressao getTipo() {
		return tipo;
	}
	
	public void setTipo(Progressao tipo) {
		this.tipo = tipo;
	}
	
	public ArrayList<Double> getValores() {
		return valores;
	}
	
	public void setValores(ArrayList<Double> valores) {
		this.valores = valores;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Pa [mediana=");
		builder.append(mediana);
		builder.append(", media=");
		builder.append(media);
		builder.append(", somatoria=");
		builder.append(somatoria);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", valores=");
		builder.append(valores != null ? valores.subList(0, Math.min(valores.size(), maxLen)) : null);
		builder.append(", valorinicial=");
		builder.append(a1);
		builder.append(", razao=");
		builder.append(razao);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append("]");
		return builder.toString();
	}
	
	
}
