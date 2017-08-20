package io.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import io.reservation.exception.AppException;
import io.reservation.model.Customer;
import io.reservation.model.Tab;
import io.reservation.util.DBUtil;

public class ReservationDAO {

	public List<Tab> findall() throws AppException {
		List<Tab> tabs = new ArrayList<Tab>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM tab");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Tab tabs1 = new Tab();
				tabs1.setTable_name(rs.getString("Table_name"));
				tabs1.setStatus(rs.getString("STATUS"));
				tabs1.setDate(rs.getString("DAT"));
				tabs1.setTime(rs.getString("TIM"));
				tabs1.setCust_id(rs.getInt("CUSTID"));
				tabs.add(tabs1);
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
		
		
		return tabs;
	}

	public List<Tab> findone(String date) throws AppException {
		List<Tab> tabs = new ArrayList<Tab>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM tab where DAT=? ");
			ps.setString(1, date);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Tab tabs1 = new Tab();
				tabs1.setTable_name(rs.getString("Table_name"));
				tabs1.setStatus(rs.getString("STATUS"));
				tabs1.setTime(rs.getString("TIM"));
				tabs1.setCust_id(rs.getInt("CUSTID"));
				
				tabs.add(tabs1);
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
		
		
		
		return tabs;
	}

	public Tab create(int custid, Tab tabl) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO tab (Table_name, STATUS, TIM, CUSTID, DAT) VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tabl.getTable_name());
			ps.setString(2, "OCCUPIED");
			ps.setString(3, tabl.getTime());
			ps.setInt(4, custid);
			ps.setString(5, tabl.getDate());
			
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				tabl.setTab_id(rs.getInt(1));
				ps1=con.prepareStatement("UPDATE customer SET STATUS=? WHERE CUSTID=?;");
				ps1.setString(1, "RESERVED");
				System.out.println("at reserved");
				ps1.setInt(2, custid);
				ps1.executeUpdate();
				ps1.close();
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
		return tabl;
	}

	public Tab update(int custid, Tab tabl) throws AppException {
		
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM tab where CUSTID=?");
			ps.setInt(1, custid);
		
			rs = ps.executeQuery();
			if(rs.next()){
				ps1 = con.prepareStatement("UPDATE tab SET Table_name=?,STATUS=?,TIM=?,DAT=? WHERE CUSTID=?;");
				ps1.setString(1, tabl.getTable_name());
				ps1.setString(2, "OCCUPIED");
				ps1.setString(3, tabl.getTime());
				ps1.setString(4, tabl.getDate());
				ps1.setInt(5, custid);
				ps1.executeUpdate();
				ps1.close();
			}
			 
		else{
				throw new NotFoundException("The id not found");
			}
			ps2 = con.prepareStatement("SELECT * FROM tab where CUSTID=?");
			ps2.setInt(1, custid);
		
			rs1 = ps.executeQuery();
			 if(rs1.next()){
				 tabl.setTable_name(rs1.getString("Table_name"));
					tabl.setStatus(rs1.getString("STATUS"));
					tabl.setTime(rs1.getString("TIM"));
					tabl.setDate(rs1.getString("DAT"));
				
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
		
		return tabl;
	}

	public void delete(int custid) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("DELETE FROM tab where CUSTID=? ");
			ps.setInt(1, custid);
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

}
