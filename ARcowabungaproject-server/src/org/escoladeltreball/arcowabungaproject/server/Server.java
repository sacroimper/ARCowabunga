/*
 *  Server.java
 *  
 *  This file is part of ARcowabungaproject.
 *  
 *  Copyright 2014 	Bernabe Gonzalez Garcia <bernagonzga@gmail.com>
 *  			Marc Sabate Piñol <masapim@hotmail.com>
 *  			Victor Purcallas Marchesi <vpurcallas@gmail.com>
 *  			Joaquim Dalmau Torva <jdalmaut@gmail.com>
 *
 *   ARcowabungaproject is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   ARcowabungaproject is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with ARcowabungaproject.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.escoladeltreball.arcowabungaproject.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server extends Thread {

    // ====================
    // CONSTANTS
    // ====================

    public static final int HALL_PORT = 4444;

    // ====================
    // ATTRIBUTES
    // ====================

    protected ServerSocket serverSocket;
    protected Socket socketService;

    protected ObjectInputStream in;
    protected ObjectOutputStream out;

    private int port;

    // ====================
    // CONSTRUCTORS
    // ====================

    public Server(int port) {
	super();
	setName(getClass().getSimpleName() + ":" + port);
	this.port = port;
    }

    // ====================
    // PUBLIC METHODS
    // ====================

    // ====================
    // PROTECTED METHODS
    // ====================

    protected void waitClient() throws IOException {
	print("Waiting client ...");
	socketService = serverSocket.accept();
	out = new ObjectOutputStream(socketService.getOutputStream());
	out.flush();
	in = new ObjectInputStream(socketService.getInputStream());
	print("Client conected");
    }

    protected void closeClient() throws IOException {
	out.close();
	in.close();
	socketService.close();
	print("Client closed");
    }

    protected void close() {
	try {
	    closeClient();
	} catch (IOException e) {
	    System.out.println(e);
	} finally {
	    print("Closed");
	}
    }

    protected void init() {
	try {
	    serverSocket = new ServerSocket(port);
	    print("Opened");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    protected void print(String msg) {
	System.out.println(getName() + "> " + msg);
    }

    // ====================
    // PRIVATE METHODS
    // ====================

    // ====================
    // OVERRIDE METHODS
    // ====================

    @Override
    public abstract void run();

    // ====================
    // GETTERS & SETTERS
    // ====================

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }

}
