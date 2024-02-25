package com.jts.hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookAppointment {

	private Connection connection;
	private Patient patient;
	private Doctor doctor;

	Scanner scanner = new Scanner(System.in);

	public BookAppointment(Connection connection, Patient patient, Doctor doctor) {
		this.connection = connection;
		this.patient = patient;
		this.doctor = doctor;
	}

	public void bookAppointment() throws SQLException {

		System.out.print("Enter Patient ID: ");
		int patientId = scanner.nextInt();

		System.out.print("Enter Doctor ID: ");
		int doctorId = scanner.nextInt();

		System.out.print("Enter appointment date yyyy-mm-dd: ");
		String appointmentDate = scanner.next();

		if (!patient.getPatientById(patientId)) {
			System.out.print("Provide a valid patient ID.");
			return;
		}

		if (!doctor.getDoctorById(doctorId)) {
			System.out.println("Provide a valid doctor ID.");
			return;
		}
		
		if(!checkAvialability(connection, doctorId, appointmentDate)) {
			System.out.println("Doctor not available.");
			return;
		}
		
		String query = "insert into appointments(patient_id, doctor_id, appointment_date) values(?, ?, ?)";
		
		
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setInt(1, patientId);
			ps.setInt(2, doctorId);
			ps.setString(3, appointmentDate);
			
			if (ps.executeUpdate() > 0) {
				System.out.println("Appointment booked successfully.");
			} else {
				System.out.println("Failed to book appointment.");
			}
		
	}
	}
	
	public boolean checkAvialability(Connection connection, int doctorId, String appointmentDate) throws SQLException {

		String query = "select count(1) from appointments where doctor_id = ? and appointment_date = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, doctorId);
			preparedStatement.setString(2, appointmentDate);

			try (ResultSet rs = preparedStatement.executeQuery()) {
			    if (rs.next()) {
			        return rs.getInt(1) == 0;
			    }
			}
		}

		return false;
	}
}
