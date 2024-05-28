package com.icia.cgv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cgv.dao.CGVDao;
import com.icia.cgv.dto.Movie;

@Service
public class CGVService {

	@Autowired
	private CGVDao cdao;

	public void insertMovie(List<Movie> movies) {
		cdao.insertMovie(movies);
	}

	public List<Movie> cgvList() {
		List<Movie> list = cdao.cgvList();
		return list;
	}

	private ModelAndView mav;
	
	public ModelAndView detailView(int rank) {
		mav = new ModelAndView();
		
		Movie detail = cdao. detailView(rank);
		
		mav.setViewName("CgvDetail");
		mav.addObject("detail", detail);
				
		return mav;
	}

}
