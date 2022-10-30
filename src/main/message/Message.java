package main.message;

import java.io.Serializable;
import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String context;
	private HashMap<String, Object> session;
	private ArrayList actividades;
	
	
	public Message () {
		context=new String();
		session=new HashMap<String, Object>();
		actividades= new ArrayList();
		
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public HashMap<String, Object> getSession() {
		return session;
	}

	public void setSession(HashMap<String, Object> session) {
		this.session = session;
	}	
}
