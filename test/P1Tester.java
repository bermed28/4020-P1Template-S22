import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.stream.Stream;

import org.junit.Test;

import Main.Election;

public class P1Tester {

	String[] output1 = {
			"Number of ballots: 10",
			"Number of blank ballots: 0",
			"Number of invalid ballots: 0",
			"Round 1: Lola Mento was eliminated with 1 #1's",
			"Round 2: Juan Lopez was eliminated with 1 #1's",
			"Round 3: Pucho Avellanet was eliminated with 3 #1's",
			"Winner: Pepe Perez wins with 6 #1's"
	};
	
	String[] output2 = {
			"Number of ballots: 2",
			"Number of blank ballots: 0",
			"Number of invalid ballots: 0",
			"Round 1: Juan Lopez was eliminated with 1 #1's",
			"Winner: Pardeep Kumar wins with 2 #1's"
	};
	
	String[] output3 = {
			"Number of ballots: 22",
			"Number of blank ballots: 7",
			"Number of invalid ballots: 10",
			"Round 1: Pedro Rivera was eliminated with 0 #1's",
			"Round 2: Jose Navaro was eliminated with 0 #1's",
			"Round 3: Juan O. Lopez was eliminated with 0 #1's",
			"Round 4: Wilson Rivera was eliminated with 0 #1's",
			"Round 5: Wilfredo Lugo was eliminated with 0 #1's",
			"Round 6: Bienvenido Velez was eliminated with 1 #1's",
			"Round 7: Emmanuel Arzuaga was eliminated with 1 #1's",
			"Winner: Heidy Sierra wins with 3 #1's"
	};
	
	String[] output4 = {
			"Number of ballots: 15",
			"Number of blank ballots: 2",
			"Number of invalid ballots: 3",
			"Round 1: Lola Mento was eliminated with 1 #1's",
			"Round 2: Juan Lopez was eliminated with 1 #1's",
			"Round 3: Pucho Avellanet was eliminated with 3 #1's",
			"Winner: Pepe Perez wins with 6 #1's"
	};

	@Test
	public void test1() {
		String[] paths = {"inputFiles/ballots.csv", "inputFiles/candidates.csv"};
		Election.main(paths);
		BufferedReader outputReader = null;
		try {
			outputReader = new BufferedReader(new FileReader("results.txt"));
			String row;
			int i = 0;
			while ((row = outputReader.readLine()) != null && i < output1.length) {
				assertTrue("Line does not match, expected: \"" + output1[i] + "\", got: \"" + row + "\"", row.equals(output1[i]));
				i++;
			}
			if(i != output1.length) fail("Output File Format is Incorrect");


		} catch(Exception e) {
			e.printStackTrace(System.out);
		}

	}

	@Test
	public void test2() {
		
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("inputFiles/twoCandidates.csv"), "utf-8"));
			writer.write("Pardeep Kumar,1\r\n" + "Juan Lopez,2");
			
		} catch (IOException e) {
			e.printStackTrace(System.out);		
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("inputFiles/tiedBallots.csv"), "utf-8"));
			writer.write("1234,1:1,2:2\r\n"+ "278,2:1,1:2");
			
		} catch (IOException e) {
			e.printStackTrace(System.out);		
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		
		
		String[] paths = {"inputFiles/tiedBallots.csv", "inputFiles/twoCandidates.csv"};
		Election.main(paths);
		BufferedReader outputReader = null;
		try {
			outputReader = new BufferedReader(new FileReader("results.txt"));
			String row;
			int i = 0;
			while ((row = outputReader.readLine()) != null && i < output2.length) {
				assertTrue("Line does not match, expected: \"" + output2[i] + "\", got: \"" + row + "\"", row.equals(output2[i]));
				i++;
			}
			if(i != output2.length) fail("Output File Format is Incorrect");


		} catch(Exception e) {
			e.printStackTrace(System.out);
		}

	}
	
	@Test
	public void test3() {
		
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("inputFiles/tenCandidates.csv"), "utf-8"));
			writer.write("Juan O. Lopez,001\r\n"
					+ "Bienvenido Velez,002\r\n"
					+ "Wilson Rivera,003\r\n"
					+ "Pedro Rivera,004\r\n"
					+ "Jose Navaro,005\r\n"
					+ "Ahmed ElSaid,006\r\n"
					+ "Kejie Lu,007\r\n"
					+ "Wilfredo Lugo,008\r\n"
					+ "Emmanuel Arzuaga,009\r\n"
					+ "Heidy Sierra,010\r\n");
			
		} catch (IOException e) {
			e.printStackTrace(System.out);		
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("inputFiles/invalidBallots.csv"), "utf-8"));
			//Valid
			writer.write("52591,1:6,2:10,3:8,4:7,5:3,6:1,7:5,8:2,9:9,10:4\n");
			writer.write("55202,1:2,2:6,3:5,4:9,5:10,6:3,7:7,8:4,9:8,10:1\n");
			writer.write("48395,1:5,2:9,3:3,4:6,5:4,6:10,7:8,8:2,9:1,10:7\n");
			writer.write("71420,1:9,2:1,3:6,4:4,5:5,6:8,7:7,8:2,9:10,10:3\n");
			writer.write("94708,1:6,2:10,3:2,4:4,5:3,6:5,7:1,8:7,9:9,10:8\n");
			//Invalid
			writer.write("90361,1:-12,2:-9,3:15,4:-14,5:21,6:6,7:13,8:15,9:3,10:8\n");
			writer.write("66033,1:16,2:24,3:2,4:-2,5:5,6:10,7:-4,8:4,9:-10,10:3\n");
			writer.write("41900,1:15,2:-15,3:14,4:14,5:1,6:23,7:-11,8:-7,9:-1,10:-6\n");
			writer.write("91375,1:-10,2:24,3:-3,4:16,5:-7,6:-12,7:16,8:17,9:4,10:-14\n");
			writer.write("57245,1:6,2:5,3:-15,4:-11,5:14,6:11,7:16,8:-15,9:20,10:18\n");
			writer.write("320,1:21,2:20,3:9,4:7,5:-9,6:-7,7:-12,8:-7,9:7,10:-4\n");
			writer.write("38858,1:-9,2:20,3:-9,4:14,5:9,6:-13,7:24,8:3,9:15,10:13\n");
			writer.write("19208,1:-2,2:-5,3:-13,4:12,5:20,6:3,7:24,8:19,9:-13,10:-6\n");
			writer.write("7963,1:-3,2:1,3:22,4:-15,5:13,6:8,7:4,8:4,9:-11,10:11\n");
			writer.write("36310,1:-7,2:0,3:-8,4:0,5:19,6:12,7:-6,8:24,9:7,10:6\n");
			//Blank
			writer.write("573504\n");
			writer.write("575304\n");
			writer.write("575104\n");
			writer.write("575014\n");
			writer.write("517504\n");
			writer.write("571504\n");
			writer.write("575204");
			
			
		} catch (IOException e) {
			e.printStackTrace(System.out);		
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		
		
		String[] paths = {"inputFiles/invalidBallots.csv", "inputFiles/tenCandidates.csv"};
		Election.main(paths);
		BufferedReader outputReader = null;
		try {
			outputReader = new BufferedReader(new FileReader("results.txt"));
			String row;
			int i = 0;
			while ((row = outputReader.readLine()) != null && i < output3.length) {
				assertTrue("Line does not match, expected: \"" + output3[i] + "\", got: \"" + row + "\"", row.equals(output3[i]));
				i++;
			}
			if(i != output3.length) fail("Output File Format is Incorrect");


		} catch(Exception e) {
			e.printStackTrace(System.out);
		}

	}
	
	@Test
	public void test4() {
		String[] paths = {"inputFiles/ballots2.csv", "inputFiles/candidates.csv"};
		Election.main(paths);
		BufferedReader outputReader = null;
		try {
			outputReader = new BufferedReader(new FileReader("results.txt"));
			String row;
			int i = 0;
			while ((row = outputReader.readLine()) != null && i < output4.length) {
				System.out.println(row);
				//assertTrue("Line does not match, expected: \"" + output4[i] + "\", got: \"" + row + "\"", row.equals(output4[i]));
				i++;
			}
			if(i != output4.length) fail("Output File Format is Incorrect");


		} catch(Exception e) {
			e.printStackTrace(System.out);
		}

	}

}
