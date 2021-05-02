package com.masterminds.entity;

public class BidParticipantInfo {

	private Long userId;
	
	private String username;
	
	private int totalPlayers;
	
	private String remainingPrice;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotalPlayers() {
		return totalPlayers;
	}

	public void setTotalPlayers(int totalPlayers) {
		this.totalPlayers = totalPlayers;
	}

	public String getRemainingPrice() {
		return remainingPrice;
	}

	public void setRemainingPrice(String remainingPrice) {
		this.remainingPrice = remainingPrice;
	}
	
}
