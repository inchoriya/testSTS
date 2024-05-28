package com.icia.cgv.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cgv.dto.Movie;
import com.icia.cgv.service.CGVService;

@Controller
public class CGVController {
	
	@Autowired
	private CGVService csvc;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/movies", method = RequestMethod.POST)
	public String movies(@RequestBody List<Movie> movies) {
		// System.out.println(movies);
		csvc.insertMovie(movies);
		
		return "index";
	}
	
	// mvList : jsp이동
	@RequestMapping(value="/mvList", method = RequestMethod.GET)
	public String mvList() {
		return "CgvList";
	}
	
	// cgvList : 영화목록
	@RequestMapping(value="cgvList", method=RequestMethod.POST)
	public @ResponseBody List<Movie> cgvList(){
		List<Movie> list = csvc.cgvList();
		return list;
	}
	
	private ModelAndView mav;
	
	// detail-view
	@RequestMapping(value="/detailView", method = RequestMethod.GET)
	public ModelAndView detailView(@RequestParam("rank") int rank) {
		mav = csvc.detailView(rank);
		return mav;
	}

		
	@RequestMapping(value="/crawling", method = RequestMethod.GET)
	public String crawling(HttpServletRequest request) {
		
		try {
            String pythonScriptPath = request.getServletContext().getRealPath("src/main/webapp/resources/cgv.py")
    				.replace(".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\", "");
            
            String[] cmd = new String[2];
            cmd[0] = "C:/Users/user/AppData/Local/Programs/Python/Python312/python.exe"; // 또는 파이썬 설치 경로를 직접 지정할 수 있음, 예: /usr/bin/python3
            cmd[1] = pythonScriptPath;

            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);

            BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line = bfr.readLine()) != null) {
                // 출력된 내용을 로그 또는 콘솔에 출력
                // System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		return "CgvList";
	}
	
	
	

}
