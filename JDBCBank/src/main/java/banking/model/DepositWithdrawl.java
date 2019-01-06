package banking.model;

import java.time.LocalDateTime;

public class DepositWithdrawl {
	private Integer depositWithdrawlID;
	private DepositOrWithdrawl type;
	private Double amount;
	private Integer accountID;
	private LocalDateTime timestamp;
	
//--CONSTRUCTORS--//
	public DepositWithdrawl(Integer depositWithdrawlID, DepositOrWithdrawl type, Double amount, Integer accountID,
			LocalDateTime timestamp) {
		super();
		this.depositWithdrawlID = depositWithdrawlID;
		this.type = type;
		this.amount = amount;
		this.accountID = accountID;
		this.timestamp = timestamp;
	}

//--FUNCTIONS--//
	
	//--GETTERS/SETTERS--//
	public Integer getDepositWithdrawlID() {
		return depositWithdrawlID;
	}


	public void setDepositWithdrawlID(Integer depositWithdrawlID) {
		this.depositWithdrawlID = depositWithdrawlID;
	}


	public DepositOrWithdrawl getType() {
		return type;
	}


	public void setType(DepositOrWithdrawl type) {
		this.type = type;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Integer getAccountID() {
		return accountID;
	}


	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
