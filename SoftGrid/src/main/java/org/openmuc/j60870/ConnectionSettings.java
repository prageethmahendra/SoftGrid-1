/* Copyright (C) 2016 Advanced Digital Science Centre

        * This file is part of Soft-Grid.
        * For more information visit https://www.illinois.adsc.com.sg/cybersage/
        *
        * Soft-Grid is free software: you can redistribute it and/or modify
        * it under the terms of the GNU General Public License as published by
        * the Free Software Foundation, either version 3 of the License, or
        * (at your option) any later version.
        *
        * Soft-Grid is distributed in the hope that it will be useful,
        * but WITHOUT ANY WARRANTY; without even the implied warranty of
        * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        * GNU General Public License for more details.
        *
        * You should have received a copy of the GNU General Public License
        * along with Soft-Grid.  If not, see <http://www.gnu.org/licenses/>.

        * @author Prageeth Mahendra Gunathilaka
*/
/*
 * Copyright 2014 Fraunhofer ISE
 *
 * This file is part of j60870.
 * For more information visit http://www.openmuc.org
 *
 * j60870 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * j60870 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with j60870.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.j60870;

final class ConnectionSettings {

	public int messageFragmentTimeout = 5000;

	public int cotFieldLength = 2;
	public int commonAddressFieldLength = 2;
	public int ioaFieldLength = 3;

	public int maxTimeNoAckReceived = 15000;
	public int maxTimeNoAckSent = 10000;
	public int maxIdleTime = 20000;

	public int maxUnconfirmedIPdusReceived = 8;

	public ConnectionSettings getCopy() {
		ConnectionSettings settings = new ConnectionSettings();

		settings.messageFragmentTimeout = messageFragmentTimeout;

		settings.cotFieldLength = cotFieldLength;
		settings.commonAddressFieldLength = commonAddressFieldLength;
		settings.ioaFieldLength = ioaFieldLength;

		settings.maxTimeNoAckReceived = maxTimeNoAckReceived;
		settings.maxTimeNoAckSent = maxTimeNoAckSent;
		settings.maxIdleTime = maxIdleTime;

		settings.maxUnconfirmedIPdusReceived = maxUnconfirmedIPdusReceived;

		return settings;
	}

}
