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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandRunner {

	private Process r;
	private boolean running = false;

	public boolean execute(String command) {
		try {
			String[] cmd = { "/bin/sh", "-c", command };
			r = Runtime.getRuntime().exec(cmd);
			running = true;
			Runnable runnable = new Runnable() {
				public void run() {
					r.destroy();
				}
			};
		
			Runtime.getRuntime().addShutdownHook(new Thread(runnable));

			return true;
		} catch (Exception e1) {
			return false;
		}
	}

	public Process getProcess() {
		return r;
	}

	public String getOutput() {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					r.getInputStream()));
			String line;
			line = reader.readLine();
		
			return line;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Kills a given procces
	public void kill() {
		if (running) {
			r.destroy();
		}

	}
}
