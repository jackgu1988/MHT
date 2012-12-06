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

import java.util.ArrayList;

import java.lang.Object;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.*;

/**
 * Here is sample of reading attributes of a given XML element.
 */

public class XMLParser extends DefaultHandler {

	int counter = 0;
	private ArrayList ipAddressList = new ArrayList<String>();
	private ArrayList macAddressList = new ArrayList<String>();
	private boolean down = false;

	/**
	 * Getters / Setters
	 * 
	 * @return
	 */

	public ArrayList getIpAddressList() {
		return ipAddressList;
	}

	public void setIpAddressList(ArrayList ipAddressList) {
		this.ipAddressList = ipAddressList;
	}

	public ArrayList getMacAddressList() {
		return macAddressList;
	}

	public void setMacAddressList(ArrayList macAddressList) {
		this.macAddressList = macAddressList;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) throws SAXException {

		String ipAddress = null;
		String macAddress = null;

		boolean ipAddressFlag = false;
		boolean macAddressFlag = false;

		if (qName.equals("status"))
			if (attrs.getValue("state").equals("down"))
				down = true;
			else if (attrs.getValue("state").equals("up"))
				down = false;

		if (!down)
			if (qName.equals("address")) {

				if (attrs.getValue("addrtype").equals("ipv4")
						|| attrs.getValue("addrtype").equals("ipv6")) {
					ipAddress = attrs.getValue("addr");
					ipAddressFlag = true;

				} else if (attrs.getValue("addrtype").equals("mac")) {
					macAddress = attrs.getValue("addr");
					macAddressFlag = true;

				}

				if (ipAddressFlag) {
					ipAddressList.add(ipAddress);

					if (counter % 2 != 0 && counter >= 1
							&& ipAddressList.size() > macAddressList.size()) {
						counter++;
						macAddressList.add(null);
					}
					counter++;
				} else if (macAddressFlag) {
					macAddressList.add(macAddress);
					counter++;
				}
			}
	}

	public void fixMacList() {
		if (macAddressList.size() < ipAddressList.size())
			macAddressList.add(null);
	}

}
