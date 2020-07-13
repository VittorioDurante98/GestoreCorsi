package it.polito.tdp.corssi.d;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class CorsoDAO {

	public List<Corso> getCorsiPerPeriodo(Integer pd){
		String sql = "select * from corso where pd = ?";
		List<Corso> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectD.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			conn.close();
			
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public Map<Corso, Integer> getIscrittiPerPeriodo(Integer pd){
		String sql = "select c.codins, c.nome, c.crediti, c.pd, COUnT(*) as tot "+
	           "form corso as c, iscrizione i " +
				"where c.codins = i.codins and c.pd = ? " +
	           "group by c.codins, c.nome, c.crediti, c.pd";
		Map<Corso, Integer> result = new HashMap<>();
		
		try {
			Connection conn = ConnectD.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				Integer n = rs.getInt("tot");
				result.put(c, n);
			}
			
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public List<Studente> getStudentiPerCorso(Corso c){
		String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS" + 
				"FROM studente AS s, iscrizione AS i" + 
				"WHERE s.matricola = i.matricola AND i.codins = ?";
		List<Studente> studenti = new LinkedList<>();
		try {
			Connection conn = ConnectD.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c.getCodins());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
				Integer n = rs.getInt("tot");
				studenti.add(s);
			}
			
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return studenti;
	}
	
}
