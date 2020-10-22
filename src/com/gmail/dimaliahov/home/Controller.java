package com.gmail.dimaliahov.home;

import com.gmail.dimaliahov.db.DBwork;
import com.gmail.dimaliahov.findRouting.Edge;
import com.gmail.dimaliahov.findRouting.Graph;
import com.gmail.dimaliahov.model.Rout;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.gmail.dimaliahov.findRouting.Main.isConnected;

public class Controller {

	@FXML
	public Button uploudSourceFile;

	@FXML
	public Button quickCheck;

	@FXML
	public Button uploudFileList;

	@FXML
	private TextField textA;

	@FXML
	private TextField textB;

	@FXML
	public void sourceUploud () {

		File sourceFile = getFile();
		if (sourceFile != null) {
			purserSourceFileAndSaveToDB(sourceFile);

			DBwork d = new DBwork();
			getWindow("Now you have on DB \n " + d.getAllFromRouting());
		} else getWindow("Wrong");

	}

	@FXML
	public void listUploud () {
		File uploudFile = getFile();
		if (uploudFile != null) {
			getWindow(checkFromList(uploudFile).toString());
		} else getWindow("Wrong");
	}

	@FXML
	public void quickCheck () {

		int a = Integer.parseInt(String.valueOf(textA.getCharacters()));
		int b = Integer.parseInt(String.valueOf(textB.getCharacters()));

		StringBuilder stringBuilder = check(a, b);

		getWindow(stringBuilder.toString());
	}

	@FXML
	public void clean () {
		DBwork dBwork = new DBwork();
		dBwork.cleaneRouting();
		getWindow("Done");

	}

	@FXML
	void initialize () {
//		quickCheck.setOnAction(
//				event -> {
//					quickCheck.getScene().getWindow().hide();
//					FXMLLoader loader = new FXMLLoader();
//					loader.setLocation(getClass().getResource("upSource.fxml"));
//					try {
//						loader.load();
//					} catch (IOException e){
//						e.printStackTrace();
//					}
//					Parent root = loader.getRoot();
//					Stage stage = new Stage();
//					stage.setScene(new Scene(root));
//					stage.showAndWait();
//				});

	}

	private File getFile () {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv file", "*.csv"));
		return fc.showOpenDialog(null);
	}

	private void getWindow (String txt) {
		Label secondLabel = new Label(txt);
		StackPane secondaryLayout = new StackPane();
		secondaryLayout.getChildren().add(secondLabel);
		Scene secondScene = new Scene(secondaryLayout, 350, 150);
		Stage newWindow = new Stage();
		newWindow.setTitle("Source");
		newWindow.setScene(secondScene);
		newWindow.initModality(Modality.WINDOW_MODAL);
		newWindow.show();
	}

	private void purserSourceFileAndSaveToDB (File file) {
		String line = "";
		String cvsSplitBy = ";";
		List<Rout> retRout = new ArrayList<>();
		DBwork dBwork = new DBwork();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				String[] rout = line.split(cvsSplitBy);

				Rout r = new Rout();
				r
						.setId(1)
						.setPointA(Integer.parseInt(rout[0]))
						.setPointB(Integer.parseInt(rout[1]))
						.setLength(Integer.parseInt(rout[2]));

				dBwork.setRoutToDB(r);
				retRout.add(r);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}


	private StringBuilder check (int a, int b) {

		StringBuilder str = new StringBuilder();

		DBwork dBwork = new DBwork();

		List<Rout> routList = dBwork.getAllFromRouting();

		List<Edge> edges = new ArrayList<>();

		for (Rout r : routList) {
			edges.add(Edge.of(r.getPointA(), r.getPointB()));
		}

		int N = 20;

		Graph graph = new Graph(edges, N);
		boolean[] discovered = new boolean[N];

		Stack<Integer> path = new Stack<>();
		if (isConnected(graph, a, b, discovered, path)) {
//			System.out.println("From " + a + " to " + b + " path " + path);
			str.append("From ").append(a).append(" to ").append(b).append(" path ").append(path);
			int lengt = 0;

			for (int i = 0; i < path.size(); i++) {

				path.get(i);
				if (i + 1 == path.size()) {
					break;
				}
				Rout r = dBwork.getAllWherePointA(path.get(i), path.get(i + 1));
				lengt = lengt + r.getLength();

			}

			str.append("\n Length is ").append(lengt);
		} else {
			System.out.println("No path exists between vertices " + a + " and " + b);
		}

		return str;
	}

	private StringBuilder checkFromList (File file) {
		StringBuilder str = new StringBuilder();
		String line = "";
		String cvsSplitBy = ";";
		List<Rout> retRout = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			while ((line = br.readLine()) != null) {
				String[] rout = line.split(cvsSplitBy);

				Rout r = new Rout();
				r
						.setId(1)
						.setPointA(Integer.parseInt(rout[0]))
						.setPointB(Integer.parseInt(rout[1]));
				retRout.add(r);
			}
		} catch (IOException e){
			e.printStackTrace();
		}


		DBwork dBwork = new DBwork();

		List<Rout> routList = dBwork.getAllFromRouting();

		List<Edge> edges = new ArrayList<>();

		for (Rout r : routList) {
			edges.add(Edge.of(r.getPointA(), r.getPointB()));
		}
		int N = 20;
		Graph graph = new Graph(edges, N);
		boolean[] discovered = new boolean[N];
		List<Rout> last = new ArrayList<>();
		for (Rout rout : retRout) {
			int src = rout.getPointA(), dest = rout.getPointB();
			Stack<Integer> path = new Stack<>();

			Rout n = new Rout();
			if (isConnected(graph, src, dest, discovered, path)) {
				System.out.println("From " + src + " to " + dest + " path " + path);
				str.append("\n From ").append(src).append(" to ").append(dest).append(" path ").append(path);
				int lengt = 0;
				n.setPointA(src);
				n.setPointB(dest);
				for (int j = 0; j < path.size(); j++) {

					path.get(j);
					if (j + 1 == path.size()) {
						break;
					}
					Rout r = dBwork.getAllWherePointA(path.get(j), path.get(j + 1));
					lengt = lengt + r.getLength();
				}
				n.setLength(lengt);
				n.setRoutingBol(true);
				last.add(n);
				str.append("\n Length is ").append(lengt);
				System.out.println(lengt);
			} else {
				System.out.println("No path exists between vertices " + src + " and " + dest);
				n.setRoutingBol(false);
				last.add(n);
			}
		}
		try {
			FileWriter writer = new FileWriter("test.csv");
			writer.append("IDA");
			writer.append(";");
			writer.append("IDB");
			writer.append("\n");
			for (Rout r : last) {
				String bol = String.valueOf(r.isRoutingBol());
				if (!bol.equals("false")) {
					String length = String.valueOf(r.getLength());
					writer.append(bol.toUpperCase());
					writer.append(";");
					writer.append(length);
					writer.append("\n");

				}else {
					writer.append(bol.toUpperCase());
					writer.append(";");
					writer.append("\n");
				}
			}

			writer.flush();
			writer.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		str.append("\nFile was save");
		return str;
	}
}
