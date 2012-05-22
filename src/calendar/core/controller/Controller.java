package calendar.core.controller;

import java.util.ArrayList;
import java.util.HashMap;

import calendar.core.exception.CoreException;

public abstract class Controller<T> {
	public abstract void create(T object) throws CoreException;
	public abstract ArrayList<T> read(HashMap<String, Object> params) throws CoreException;
	public abstract void update(T object) throws CoreException;
	public abstract void delete(T object) throws CoreException;
}
