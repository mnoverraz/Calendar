package calendar.core.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;

import calendar.core.exception.CoreException;

/**
 * Defines common methods (CRUD) for all controllers and holds
 * the naming context
 * 
 * All child objects must specify the type of objects they hold
 * in order to replace generic type notification
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public abstract class Controller<T> {
	
	protected Context context;
	
	public Controller(Context context) {
		this.context = context;
	}
	
	/**
	 * Creates an object of the specified type
	 * 
	 * @param object (generic object)
	 * @throws CoreException
	 */
	public abstract void create(T object) throws CoreException;
	
	/**
	 * Returns all objects found for specified filter in an ArrayList
	 * 
	 * @param params
	 * @return
	 * @throws CoreException
	 */
	public abstract ArrayList<T> read(HashMap<String, Object> filter) throws CoreException;
	
	/**
	 * Updates an existing object by given object as metho param
	 * @param object
	 * @throws CoreException
	 */
	public abstract void update(T object) throws CoreException;
	
	/**
	 * Deletes an existing object
	 * 
	 * @param object
	 * @throws CoreException
	 */
	public abstract void delete(T object) throws CoreException;
}
