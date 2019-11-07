

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface Gravacao {

	public boolean gravar(ArrayList<Double> lista) throws IOException;

	public ArrayList<Double> ler() throws ParseException;
}
