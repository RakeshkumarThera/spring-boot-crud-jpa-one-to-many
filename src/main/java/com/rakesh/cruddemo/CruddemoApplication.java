package com.rakesh.cruddemo;

import com.rakesh.cruddemo.dao.AppDAO;
import com.rakesh.cruddemo.entity.Course;
import com.rakesh.cruddemo.entity.Instructor;
import com.rakesh.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
			createInstructorWithCourses(appDAO);
		};
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//create the instructor
		Instructor tempInstructor =
				new Instructor("rakesh", "thera", "rakeshkumarthera@gmail.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("https://www.youtube.com/@godswordministries.",
						"Travelling");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//Create some courses

		Course tempCourse1 = new Course("Genesis");
		Course tempCourse2 = new Course("Exodus");

		//Add Courses to Instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);


		//Save the instructors
		//
		//NOTE: this will also save the courses
		//because of CascadeType.PERSIST
		//
		System.out.println("Saving Instructor: " + tempInstructor);
		System.out.println("The Courses: " +tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("DONE!!!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructor detail with id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Delete Complete!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;

		//get the instructor detail
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("Instructor detials: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting the instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Deleted!!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("the associated instructor details only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//create the instructor
		Instructor tempInstructor =
				new Instructor("rakesh", "thera", "rakeshkumarthera@gmail.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("https://www.youtube.com/@godswordministries.",
						"Travelling");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructors

		//
		//NOTE: this will save the details object
		//because of CascadeType:ALL
		//
		System.out.println("Saving Instructors: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}