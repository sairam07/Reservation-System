package io.reservation.dao;

import io.reservation.exception.AppException;
import io.reservation.model.Customer;
import io.reservation.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class CustomerDAO {

	public List<Customer> findall() throws AppException {
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM customer");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer cst = new Customer();
				cst.setCust_id(rs.getInt("CUSTID"));
				cst.setFirstName(rs.getString("FIRST_NAME"));
				cst.setLastName(rs.getString("LAST_NAME"));
				cst.setPhone(rs.getString("PHONE"));
				cst.setEmail(rs.getString("EMAIL"));
				cst.setCity(rs.getString("CITY"));
				cst.setState(rs.getString("STATE"));
				cst.setZip(rs.getInt("ZIP"));
				cst.setDate(rs.getString("DAT"));
				cst.setTime(rs.getString("TIM"));
				cst.setGuests(rs.getInt("GUESTS"));
				cst.setOccasion(rs.getString("OCCASION"));
				cst.setSupplement(rs.getString("SUPPLEMENT"));
				cst.setStatus(rs.getString("STATUS"));
				customers.add(cst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return customers;
	}

	
	public Customer findone(int id) throws AppException {
		Customer cst = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM customer where CUSTID=?");
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cst = new Customer();
				cst.setCust_id(rs.getInt("CUSTID"));
				cst.setFirstName(rs.getString("FIRST_NAME"));
				cst.setLastName(rs.getString("LAST_NAME"));
				cst.setPhone(rs.getString("PHONE"));
				cst.setEmail(rs.getString("EMAIL"));
				cst.setCity(rs.getString("CITY"));
				cst.setState(rs.getString("STATE"));
				cst.setZip(rs.getInt("ZIP"));
				cst.setDate(rs.getString("DAT"));
				cst.setTime(rs.getString("TIM"));
				cst.setGuests(rs.getInt("GUESTS"));
				cst.setOccasion(rs.getString("OCCASION"));
				cst.setSupplement(rs.getString("SUPPLEMENT"));
				
				}
			else{
				throw new NotFoundException("The id not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return cst;
	}


	public Customer create(Customer cst) throws AppException {
		
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO customer (FIRST_NAME, LAST_NAME, PHONE, EMAIL, CITY, STATE, ZIP, DAT, TIM, GUESTS, OCCASION, SUPPLEMENT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, cst.getFirstName());
			ps.setString(2, cst.getLastName());
			ps.setString(3, cst.getPhone());
			ps.setString(4, cst.getEmail());
			ps.setString(5, cst.getCity());
			ps.setString(6, cst.getState());
			ps.setInt(7, cst.getZip());
			ps.setString(8, cst.getDate());
			ps.setString(9, cst.getTime());
			ps.setInt(10, cst.getGuests());
			ps.setString(11, cst.getOccasion());
			ps.setString(12, cst.getSupplement());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				cst.setCust_id(rs.getInt(1));
				
				}
			else{
				throw new NotFoundException("The id not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return cst;
	}


	


	public Customer update(int id, Customer cst) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM customer where CUSTID=?");
			ps.setInt(1, id);
		
			rs = ps.executeQuery();
			if(rs.next()){
				ps1 = con.prepareStatement("UPDATE customer SET PHONE=?,EMAIL=?,DAT=?,TIM=?,GUESTS=? WHERE CUSTID=?;");
				
				ps1.setString(1, cst.getPhone());
				ps1.setString(2, cst.getEmail());
				ps1.setString(3, cst.getDate());
				ps1.setString(4, cst.getTime());
				ps1.setInt(5, cst.getGuests());
				ps1.setInt(6, id);
				ps1.executeUpdate();
				ps1.close();
			}
			 
		else{
				throw new NotFoundException("The id not found");
			}
			ps2 = con.prepareStatement("SELECT * FROM customer where CUSTID=?");
			ps2.setInt(1, id);
		
			rs1 = ps.executeQuery();
			 if(rs1.next()){
				cst.setCust_id(rs1.getInt("CUSTID"));
				cst.setFirstName(rs1.getString("FIRST_NAME"));
				cst.setLastName(rs1.getString("LAST_NAME"));
				cst.setPhone(rs1.getString("PHONE"));
				cst.setEmail(rs1.getString("EMAIL"));
				cst.setCity(rs1.getString("CITY"));
				cst.setState(rs1.getString("STATE"));
				cst.setZip(rs1.getInt("ZIP"));
				cst.setDate(rs1.getString("DAT"));
				cst.setTime(rs1.getString("TIM"));
				cst.setGuests(rs1.getInt("GUESTS"));
				cst.setOccasion(rs1.getString("OCCASION"));
				cst.setSupplement(rs1.getString("SUPPLEMENT"));
				
			 }
				
			ps2.close();
			rs1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cst;	
	}


	public void delete(int id) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("DELETE FROM customer where CUSTID=? ");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	public List<Customer> findstatus() throws AppException {
		List<Customer> customers = new ArrayList<Customer>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM customer where status is NOT NULL");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer cst = new Customer();
				cst.setCust_id(rs.getInt("CUSTID"));
				cst.setFirstName(rs.getString("FIRST_NAME"));
				cst.setLastName(rs.getString("LAST_NAME"));
				cst.setPhone(rs.getString("PHONE"));
				cst.setEmail(rs.getString("EMAIL"));
				cst.setCity(rs.getString("CITY"));
				cst.setState(rs.getString("STATE"));
				cst.setZip(rs.getInt("ZIP"));
				cst.setDate(rs.getString("DAT"));
				cst.setTime(rs.getString("TIM"));
				cst.setGuests(rs.getInt("GUESTS"));
				cst.setOccasion(rs.getString("OCCASION"));
				cst.setSupplement(rs.getString("SUPPLEMENT"));
				cst.setStatus(rs.getString("STATUS"));
				customers.add(cst);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		finally {
			
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return customers;
	}

}
