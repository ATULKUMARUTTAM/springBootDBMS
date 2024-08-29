package com.atuluttam.springBootDBMS;

import com.atuluttam.springBootDBMS.Model.Student;
import com.atuluttam.springBootDBMS.service.studentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootDbmsApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringBootDbmsApplication.class, args);
		studentService ss = ctx.getBean(studentService.class);
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Choose an option:");
			System.out.println("1. Add a new student");
			System.out.println("2. Find a student by roll number");
			System.out.println("3. Display all students");
			System.out.println("4. Delete a student by roll number");
			System.out.println("5. Update a student's details");
			System.out.println("6. Exit");
			int choice = scanner.nextInt();
			try {
				switch (choice) {
					case 1:
						Student st = new Student();
						System.out.print("Enter roll number: ");
						st.setRoll(scanner.nextInt());
						System.out.print("Enter name: ");
						st.setName(scanner.next());
						System.out.print("Enter course: ");
						st.setCourse(scanner.next());
						ss.storeInDatabase(st);
						break;
					case 2:
						System.out.print("Enter roll number: ");
						int roll = scanner.nextInt();
						Student foundStudent = ss.findStudentByRoll(roll);
						System.out.println("Found Student: " + foundStudent);
						break;
					case 3:
						List<Student> students = ss.findAllStudents();
						System.out.println("All Students:");
						for (Student student : students) {
							System.out.println(student);
						}
						break;
					case 4:
						System.out.print("Enter roll number: ");
						roll = scanner.nextInt();
						ss.deleteStudentByRoll(roll);
						break;
					case 5:
						st = new Student();
						System.out.print("Enter roll number: ");
						st.setRoll(scanner.nextInt());
						System.out.print("Enter new name: ");
						st.setName(scanner.next());
						System.out.print("Enter new course: ");
						st.setCourse(scanner.next());
						ss.updateStudentInDatabase(st);
						break;
					case 6:
						System.out.println("Exiting...");
						scanner.close();
						System.exit(0);
					default:
						System.out.println("Invalid choice. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("!!!!ERROR FOUND!!!");
				System.out.println(e);
			}
		}
	}
}
