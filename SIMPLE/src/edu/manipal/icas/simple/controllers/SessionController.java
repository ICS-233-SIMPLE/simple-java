package edu.manipal.icas.simple.controllers;

import java.io.IOException;

import edu.manipal.icas.simple.databases.SessionDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessSessionDatabase;
import edu.manipal.icas.simple.session.Session;

public class SessionController {
	private static final SessionController CONTROLLER = new SessionController();
	private SessionDatabase db;
	
	private SessionController() {
		db = MsAccessSessionDatabase.getDatabase();
	}
	
	public static SessionController getController() {
		return CONTROLLER;
	}
	
	public Session getCurrentSession() {
		return db.getCurrentSession();
	}
	
	public boolean startSession(Session session) {
		try {
			db.startSession(session);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
