package calendar.core.controller;

import java.util.HashMap;

import calendar.core.exception.CoreException;

public abstract class Controller {
	public abstract void create(Object object) throws CoreException;
	public abstract Object read(HashMap<String, Object> params) throws CoreException;
	public abstract void update(Object object) throws CoreException;
	public abstract void delete(Object object) throws CoreException;
}
