package jpkg.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * This class makes quick reading from and writing to of files convenient. 
 * 
 * @author minerguy31
 *
 */
public class SimpleIO {
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	public static final int DEFAULT_BUFFER_SIZE = 4096;
	
	/**
	 * Read file into a String. This method is discouraged unless you're
	 * fairly sure the input file will fit in memory.
	 * @param f File to read from
	 * @param c Charset to use
	 * @return A string containing the contents of the file
	 * @throws IOException if any problems are encountered
	 */
	public static String readFileToString(File f, Charset c) throws IOException {
		// TODO speed up more
		
		char[] buff = new char[DEFAULT_BUFFER_SIZE];
		
		StringBuilder ret = new StringBuilder();
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader fr = new InputStreamReader(fis, c);
		BufferedReader br = new BufferedReader(fr);
		
		while(br.read(buff) != -1) {
			ret.append(buff);
		}
		
		br.close();
		return ret.toString();
	}
	
	/**
	 * Read file into a String. This method is discouraged unless you're
	 * fairly sure the input file will fit in memory. This method also 
	 * uses the Charset {@link SimpleIO#DEFAULT_CHARSET}. 
	 * @param f File to read from
	 * @return A string containing the contents of the file
	 * @throws IOException if any problems are encountered
	 */
	public static String readFileToString(File f) throws IOException {
		return readFileToString(f, DEFAULT_CHARSET);
	}
	
	/**
	 * Write a String to a file.
	 * @param s String to write
	 * @param f File to write to
	 * @throws IOException
	 */
	public static void writeStringToFile(String s, File f) throws IOException {
		FileWriter fos = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fos);
		
		bw.write(s);
		bw.close();
	}
	
	/**
	 * Write to file and swallow exceptions
	 */
	public static void writeSwallowed(String s, File f, boolean showexception) {
		try {
			writeStringToFile(s, f);
		} catch (IOException e) {
			if(showexception)
				e.printStackTrace();
		}
	}
	
	/**
	 * Read from file and swallow exceptions
	 */
	public static String readSwallowed(File f, String defaultreturn, boolean showexception) {
		try {
			return readFileToString(f);
		} catch (IOException e) {
			if(showexception)
				e.printStackTrace();
			return defaultreturn;
		}
	}
}
