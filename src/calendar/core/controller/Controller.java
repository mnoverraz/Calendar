package calendar.core.controller;

import java.util.HashMap;

import calendar.core.exception.CoreException;

public abstract class Controller {
	public abstract <T> void create(T object) throws CoreException;
	public abstract <T> T read(HashMap<String, Object> params) throws CoreException;
	public abstract <T> void update(T object) throws CoreException;
	public abstract <T> void delete(T object) throws CoreException;
}
