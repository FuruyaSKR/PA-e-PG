import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Gravajson2 implements Gravacao {

	private GsonBuilder builder;
	@SuppressWarnings("unused")
	private Gson gson;
	private ArrayList<Double> lista = new ArrayList<Double>();
	String nome;

	public Gravajson2(String nome) {
		this.nome = nome;
	}

	public boolean gravar(ArrayList<Double> lista) {
		builder = new GsonBuilder();
		Gson gson = builder.create();
		try {

			System.out.println(lista);
			FileWriter writer = new FileWriter(nome + ".json");
			writer.write(gson.toJson(lista));
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Double> ler() {
		builder = new GsonBuilder();
		gson = builder.create();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(nome + ".json"));
			Type listType = new TypeToken<ArrayList<Double>>() {
			}.getType();
			lista = new Gson().fromJson(bufferedReader, listType);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void geragrafico() {
		ArrayList<Double> a = ler();

		String html = "<html>\r\n" + "  <head>\r\n"
				+ "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n"
				+ "    <script type=\"text/javascript\">\r\n"
				+ "      google.charts.load('current', {'packages':['corechart']});\r\n"
				+ "      google.charts.setOnLoadCallback(drawChart);\r\n" + "\r\n" + "      function drawChart() {\r\n"
				+ "        var data = google.visualization.arrayToDataTable([" + "          ['Values', 'Values'],\n";

		for (int i = 0; i < a.size(); i++) {

			html += String.format("['%s', %s], \n", i, a.get(i));
		}

		html += "]);\r\n" + "\r\n" + "        var options = {\r\n" + "          title: 'Progressão',\r\n"
				+ "          hAxis: {title: 'x',  titleTextStyle: {color: '#333'}},\r\n"
				+ "          vAxis: {minValue: 0}\r\n" + "        };\r\n" + "\r\n"
				+ "        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));\r\n"
				+ "        chart.draw(data, options);\r\n" + "      }\r\n" + "    </script>\r\n" + "  </head>\r\n"
				+ "  <body>\r\n" + "    <div id=\"chart_div\" style=\"width: 100%; height: 500px;\"></div>\r\n"
				+ "  </body>\r\n" + "</html>";
		File file = new File(nome + ".html");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.write(html);
			writer.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Channels.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void geragraficopizza() {
		ArrayList<Double> a = ler();
		String html = "<html> <head> <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script> <script type=\"text/javascript\"> google.charts.load('current', {'packages':['corechart']}); google.charts.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable(["
				+ "          ['Values', 'Values'],\n";

		for (int i = 0; i < a.size(); i++) {

			html += String.format("['%s', %s], \n", i, a.get(i));
		}

		html += " ]);var options = {title: 'My Daily Activities' }; var chart = new google.visualization.PieChart(document.getElementById('piechart')); chart.draw(data, options); } </script> </head> <body> <div id=\"piechart\" style=\"width: 900px; height: 500px;\"></div> </body> </html>";

		File file = new File(nome + ".html");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.write(html);
			writer.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Channels.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void geragraficocoluna() {

		ArrayList<Double> a = ler();
		String html = "<html> <head> <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script><script type=\"text/javascript\"> google.charts.load('current', {'packages':['bar']}); google.charts.setOnLoadCallback(drawChart); function drawChart() { var data =google.visualization.arrayToDataTable(["
				+ "          ['Values', 'Values'],\n";

		for (int i = 0; i < a.size(); i++) {

			html += String.format("['%s', %s], \n", i, a.get(i));
		}

		html += " ]); var options = { chart: { title: 'Grafico json', subtitle: ' a ', }}; var chart = new google.charts.Bar(document.getElementById('columnchart_material')); chart.draw(data, google.charts.Bar.convertOptions(options)); } </script> </head> <body> <div id=\"columnchart_material\" style=\"width: 800px; height: 500px;\"></div> </body> </html>";

		File file = new File(nome + ".html");
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.write(html);
			writer.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Channels.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}