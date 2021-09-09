package com.masterminds.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
//@Table(name = "picked_players")
public class PickedPlayer implements Comparable<PickedPlayer> {

	@Id
	@SequenceGenerator(name = "pickedPlayerSequence", sequenceName = "picked_player_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pickedPlayerSequence")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "player_id")
	private Long playerId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "player_price")
	private String playerPrice;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "started")
	private boolean started;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPlayerPrice() {
		return playerPrice;
	}

	public void setPlayerPrice(String playerPrice) {
		this.playerPrice = playerPrice;
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

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	@Override
	public int compareTo(PickedPlayer o) {
		return this.getUserId().compareTo(o.getUserId());
	}
	
}
