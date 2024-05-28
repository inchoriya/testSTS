package com.icia.cgv.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.cgv.dto.Movie;

@Repository
public class CGVDao {
	
	@Autowired
	private SqlSessionTemplate sql;

	public void insertMovie(List<Movie> movies) {
		sql.delete("CGV.delMov");

		for (int i = 0; i < movies.size(); i++) {
			System.out.println(movies.get(i));
			sql.insert("CGV.insertMovie", movies.get(i));
		}

	}

	public List<Movie> cgvList() {
		return sql.selectList("CGV.cgvList");
	}

	public Movie detailView(int rank) {
		return sql.selectOne("CGV.detailView", rank);
	}

}
