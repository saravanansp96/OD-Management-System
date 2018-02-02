package com.zilker.mymodule.interfaces;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewOdInterface {
	public void viewOdDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
