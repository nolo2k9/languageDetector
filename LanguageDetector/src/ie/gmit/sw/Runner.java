package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author Keith The runner class runs all of the code. Requests necessary
 *         variables for implementation. Creates new instances of classes and
 *         uses the methods Creates threads to carry out the work needed to be
 *         done processing etc Outputs Result of query and returns the contents
 *         of the file
 */
public class Runner {

	/**
	 * @author Keith
	 * @param args
	 * @throws Throwable
	 * 
	 *                   The main method puts all of the code together Firstly it
	 *                   outputs the variables needed to run the program using the
	 *                   scanner The first Variable requests the file path of the
	 *                   wili file The second requests the file path of the query
	 *                   file It then asks for the amount of kmers/n-grams the user
	 *                   wants to use
	 * 
	 *                   Creates a new instance of the parser and database class
	 *                   Assigning the wili/kmer variables to Parser p p is then
	 *                   used to call the method databaseSetup and passes it the
	 *                   newly instantiated db instance
	 * 
	 *                   A thread is then created and started The db.resize method
	 *                   is then called to allow the user to select the top number
	 *                   of kmers they wish to be stored
	 * 
	 * 
	 *                   Buffered reader is then brought in to read the contents of
	 *                   the file path passed into s (query file location) Callable
	 *                   and future are then brought used via the created thread
	 *                   pool 'executor' passing it a string as a return type Inside
	 *                   the mandatory call method an instance of line is set to an
	 *                   empty String Line is used to hold the contents of the file
	 *                   Inside a while loop the analsyeQuery method is called
	 *                   passing it line This takes the string passed into line and
	 *                   starts the process the query it then breaks after finding
	 *                   the most likely language. The reader is then closed line is
	 *                   returned The executor service is then shit down Then using
	 *                   future.get it reurns the contents of the file Try/catch is
	 *                   used to catch various exceptions
	 */

	public static void main(String[] args) throws Throwable {
		ExecutorService executor = Executors.newCachedThreadPool();// creating thread pool

		Scanner sc = new Scanner(System.in);
		System.out.println("*************************************************************************");
		System.out.println("*       GMIT- Dept. Computer Science and applied Physics                *");
		System.out.println("*                                                                       *");
		System.out.println("*                   Text Language Detector                              *");
		System.out.println("*                                                                       *");
		System.out.println("*************************************************************************");
		System.out.println("\n");

		System.out.println("Please Enter Wili Data Location\n");
		String wili = sc.next();

		System.out.println("Please Enter Query file Location\n");
		String s = sc.next();

		System.out.println("Please Enter number of kmers\n");
		int kmrs = sc.nextInt();

		System.out.println("Parsing please wait........\n");

		// path to file, number of kmers.
		Parser p = new Parser(wili, kmrs);
		// new instance of Database
		Database db = new Database();
		// Assigning db to the parser method databaseSetup
		p.databaseSetup(db);

		// creating a new threads
		Thread t = new Thread(p);
		Thread t1 = new Thread(s);
		// starting thread
		t.start();
		t.join();
		t1.start();
		// joining thread
		t1.join();
		// calling db.resize method
		db.resize(300);
		// new instance of buffered reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
		// Callable/ future instantiation. Passed a string value
		Future<String> future = executor.submit(new Callable<String>() {
			// implementing mandatory callable call method
			@Override
			public String call() throws Exception {
				String line = " ";

				while ((line = reader.readLine()) != null) {

					System.out.println("Finishing..... \n");
					p.analyseQuery(line);// call analyseQuery

					break;// break from loop

				} // while

				reader.close();// close reader
				return line;// return contents

			}// call

		});// future

		executor.shutdown();// shutdown thread when thread finished
		try {
			System.out.println("Contents of file are: " + future.get());
		} // try
		catch (InterruptedException e) {

			e.printStackTrace();
		} // catch
		catch (ExecutionException e) {

			System.out.println(e);
		} // catch

	}// main

}// Runner
