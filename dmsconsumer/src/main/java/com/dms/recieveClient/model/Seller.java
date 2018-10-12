package com.dms.recieveClient.model;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;

import javax.persistence.*;

@Entity
@Table(name = "seller", schema = SchemaConstant.DMS_SCHEMA)
public class Seller extends DMSEntity<Long, Seller> {

    private static final long serialVersionUID = 2142294688784119378L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "GUEST_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "account_holder_name", length = 50)
    private String accountHolderName;

    @Column(name = "seller_IFSC_code", length = 20, nullable = false)
    private String seller_ifsc_code;

    @Column(name = "seller_account_number", length = 20)
    private String seller_account_number;

    @Column(name = "payment_mode", length = 100)
    private String payoutmode;

    @Column(name = "sellerId", length = 20)
    private String sellerId;

    @Column(name = "status", length = 20)
    private Integer status;

    @Column(name = "balance", length = 100)
    private String balance;

    @Column(name = "tenant_package_id")
    private Long tenantPackageID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSeller_ifsc_code() {
        return seller_ifsc_code;
    }

    public void setSeller_ifsc_code(String seller_ifsc_code) {
        this.seller_ifsc_code = seller_ifsc_code;
    }

    public String getSeller_account_number() {
        return seller_account_number;
    }

    public void setSeller_account_number(String seller_account_number) {
        this.seller_account_number = seller_account_number;
    }

    public String getPayoutmode() {
        return payoutmode;
    }

    public void setPayoutmode(String payoutmode) {
        this.payoutmode = payoutmode;
    }

    public Long getTenantPackageID() {
        return tenantPackageID;
    }

    public void setTenantPackageID(Long tenantPackageID) {
        this.tenantPackageID = tenantPackageID;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
}