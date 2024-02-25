package com.jts.hms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagement {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		
		Scanner sc = new Scanner(System.in);
		
		try (Connection conn = DatabaseService.getConnection()){
			
			Patient patient = new Patient(conn);
			Doctor doctor = new Doctor(conn);
			BookAppointment appointment = new BookAppointment(conn, patient, doctor);
			
			while(true) {
				System.out.println("**** Hospital Management System ****");
				System.out.println("1. Add Patient");
				System.out.println("2. Add Doctor");
				System.out.println("3. View Patients");
				System.out.println("4. View Doctors");
				System.out.println("5. Book Appointment");
				System.out.println("6. Exit");
				
				System.out.print("Enter your choice: ");
				
				int ch = sc.nextInt();
				
				  switch (ch) {
                  case 1:
                      patient.addPatient();
                      System.out.println("Adding Patient...");                      
                      break;

                  case 2:
                      doctor.addDoctor();
                      System.out.println("Adding Doctor...");                      
                      break;

                  case 3:
                      patient.viewPatients();
                      System.out.println("Viewing Patients...");                      
                      break;

                  case 4:
                      doctor.viewDoctors();
                      System.out.println("Viewing Doctors...");                      
                      break;

                  case 5:
                      appointment.bookAppointment();                                           
                      break;

                  case 6:                      
                      System.out.println("Exiting...");
                      sc.close();
                      return;

                  default:
                      System.out.println("Invalid choice: "+ch+". Please enter a valid choice.");
                      break;
              }
				
			}
		}
		
	}
}
