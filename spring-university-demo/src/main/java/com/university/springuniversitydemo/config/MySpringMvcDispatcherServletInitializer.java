package com.university.springuniversitydemo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.university.springuniversitydemo.SpringUniversityDemoApplication;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringUniversityDemoApplication.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}






