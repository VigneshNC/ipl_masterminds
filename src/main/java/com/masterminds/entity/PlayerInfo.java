package com.masterminds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "player_info")
public class PlayerInfo {

	@Id
	@SequenceGenerator(name = "playerInfoSequence", sequenceName = "player_info_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "playerInfoSequence")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "player_id")
	private Long playerId;
	
	@Column(name = "player_name")
	private String playerName;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "ipl_team")
	private String iplTeam;
	
	@Column(name = "owned_by")
	private String owner;
	
	@Column(name = "bid")
	private String bid;
	
	@Column(name = "points")
	private Long points;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "match1")
	private Long match1;
	
	@Column(name = "match2")
	private Long match2;
	
	@Column(name = "match3")
	private Long match3;
	
	@Column(name = "match4")
	private Long match4;
	
	@Column(name = "match5")
	private Long match5;
	
	@Column(name = "match6")
	private Long match6;
	
	@Column(name = "match7")
	private Long match7;
	
	@Column(name = "match8")
	private Long match8;
	
	@Column(name = "match9")
	private Long match9;
	
	@Column(name = "match10")
	private Long match10;
	
	@Column(name = "match11")
	private Long match11;
	
	@Column(name = "match12")
	private Long match12;
	
	@Column(name = "match13")
	private Long match13;
	
	@Column(name = "match14")
	private Long match14;
	
	@Column(name = "match15")
	private Long match15;
	
	@Column(name = "match16")
	private Long match16;
	
	@Column(name = "match17")
	private Long match17;
	
	@Column(name = "match18")
	private Long match18;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIplTeam() {
		return iplTeam;
	}

	public void setIplTeam(String iplTeam) {
		this.iplTeam = iplTeam;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getMatch1() {
		return match1;
	}

	public void setMatch1(Long match1) {
		this.match1 = match1;
	}

	public Long getMatch2() {
		return match2;
	}

	public void setMatch2(Long match2) {
		this.match2 = match2;
	}

	public Long getMatch3() {
		return match3;
	}

	public void setMatch3(Long match3) {
		this.match3 = match3;
	}

	public Long getMatch4() {
		return match4;
	}

	public void setMatch4(Long match4) {
		this.match4 = match4;
	}

	public Long getMatch5() {
		return match5;
	}

	public void setMatch5(Long match5) {
		this.match5 = match5;
	}

	public Long getMatch6() {
		return match6;
	}

	public void setMatch6(Long match6) {
		this.match6 = match6;
	}

	public Long getMatch7() {
		return match7;
	}

	public void setMatch7(Long match7) {
		this.match7 = match7;
	}

	public Long getMatch8() {
		return match8;
	}

	public void setMatch8(Long match8) {
		this.match8 = match8;
	}

	public Long getMatch9() {
		return match9;
	}

	public void setMatch9(Long match9) {
		this.match9 = match9;
	}

	public Long getMatch10() {
		return match10;
	}

	public void setMatch10(Long match10) {
		this.match10 = match10;
	}

	public Long getMatch11() {
		return match11;
	}

	public void setMatch11(Long match11) {
		this.match11 = match11;
	}

	public Long getMatch12() {
		return match12;
	}

	public void setMatch12(Long match12) {
		this.match12 = match12;
	}

	public Long getMatch13() {
		return match13;
	}

	public void setMatch13(Long match13) {
		this.match13 = match13;
	}

	public Long getMatch14() {
		return match14;
	}

	public void setMatch14(Long match14) {
		this.match14 = match14;
	}

	public Long getMatch15() {
		return match15;
	}

	public void setMatch15(Long match15) {
		this.match15 = match15;
	}

	public Long getMatch16() {
		return match16;
	}

	public void setMatch16(Long match16) {
		this.match16 = match16;
	}

	public Long getMatch17() {
		return match17;
	}

	public void setMatch17(Long match17) {
		this.match17 = match17;
	}

	public Long getMatch18() {
		return match18;
	}

	public void setMatch18(Long match18) {
		this.match18 = match18;
	}
	
}
