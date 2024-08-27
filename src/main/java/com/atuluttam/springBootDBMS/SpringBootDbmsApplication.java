package com.atuluttam.springBootDBMS;

import com.atuluttam.springBootDBMS.Model.Student;
import com.atuluttam.springBootDBMS.service.studentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDbmsApplication {

	public static void main(String[] args) {

		ApplicationContext ctx =  SpringApplication.run(SpringBootDbmsApplication.class, args);
	Student st =	ctx.getBean(Student.class);
	st.setCourse("CSF401");


	studentService ss =  ctx.getBean(studentService.class);
      ss.storeindatabase(st);

	}

}
