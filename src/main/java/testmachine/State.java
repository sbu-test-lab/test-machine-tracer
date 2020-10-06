package testmachine;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class State extends HashMap<String, String> {

	
	public void fromFile(String path) {
		InputStream fis;
		BufferedReader br = null;
		String line;

		try {
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis,
					Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
				String[] part=line.trim().split("\t");
				this.put(part[0], part[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br = null;
			fis = null;
		}
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i=0;
		for (Entry<String, String> s : this.entrySet()){
			sb.append(String.format("%s =( %s )\n", s.getKey(), s.getValue()));
			i++;
		}
		return sb.toString();
	}
	public void add(String string, String string2) {
		// TODO Auto-generated method stub
		put(string,string2);
	}
}
