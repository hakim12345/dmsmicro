package com.dms.recieveClient.model;

import com.dms.recieveClient.constants.SchemaConstant;
import com.dms.recieveClient.generic.DMSEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tenant", schema = SchemaConstant.DMS_SCHEMA)
@NamedNativeQueries({
        @NamedNativeQuery(name = "getTenantByCityId", query = "SELECT * FROM DMS.tenant t where t.city_id= :cityID", resultClass = TravelOperator.class),
        @NamedNativeQuery(name = "getTenantByCityTenant", query = "SELECT * FROM tenant tn where (tn.tanentid=:tenant OR tn.companyname=:tenant) AND tn.city_id=:cityID", resultClass = TravelOperator.class)
})
public class TravelOperator extends DMSEntity<Long, TravelOperator> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "DMS_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "TENANT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "companyname", nullable = false, length = 100)
    private String companyname;

    @Column(name = "contactno", unique = true, nullable = false, length = 20)
    private Long contactno;

    @Column(name = "website", nullable = false, length = 150)
    private String website;

    @Column(name = "landline_number", length = 20)
    private Long landlineNumber;

    @Column(name = "emailid", unique = true, nullable = false, length = 100)
    private String emailid;

    @Column(name = "gstno", unique = true,  length = 20)
    private String gstno;

    @Column(name = "panno", unique = true, length = 20)
    private String panno;

    @Column(name = "packageid")
    private Long packageid;

    @Column(name = "merchant_id")
    private String  merchantid;

    @Column(name = "merchant_password")
    private String  merchantPassword;

    @Column(name = "paymentid")
    private Long paymentid;

    @Column(name = "amount")
    private double amount;

    @Column(name = "tanentid", unique = true, nullable = false, length = 100)
    private String tanentid;

    @Column(name = "status")
    private int status;

    @Column(name = "owner_name", nullable = false, length = 50)
    private String ownerName;

    @Column(name = "address1", nullable = false, length = 100)
    private String address1;

    @Column(name = "address2", nullable = false, length = 50)
    private String address2;

    @Column(name = "landmark", length = 50)
    private String landmark;

    @Column(name = "pinCode", nullable = false)
    private Integer pinCode;

    @Column(name = "is_delete", nullable = false)
    private boolean isDeleted;

    @Column(name = "email_status", nullable = false)
    private int emailStatus;

    @Column(name = "email_flag", nullable = false)
    private boolean emailFlag;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "atom_login_id")
    private String atomLoginId;

    /* @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
     @JoinColumn(name = "companytypename", nullable = false)
     private CompanyType companytypename;
 */
    @Column(name = "contactno1")
    private Long contactno1;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

   /* @JoinColumn(name = "group_id")
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    private Group group;*/

    /*  @JoinColumn(name = "permission_id")
      @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
      private Permission permission;
  */
    @OneToOne(cascade= { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name="seller_id")
    private Seller seller;

    @Column(name = "first_name1", nullable = false, length = 50)
    private String firstName1;

    @Column(name = "first_name2", length = 50)
    private String firstName2;

    @Column(name = "last_name1", nullable = false, length = 50)
    private String lastName1;

    @Column(name = "last_name2", length = 50)
    private String lastName2;

    @Column(name = "mobile_no1", nullable = false, length = 20)
    private String mobileNo1;

    @Column(name = "mobile_no2", length = 20)
    private String mobileNo2;

    @Column(name = "mobile_no3", length = 20)
    private String mobileNo3;

    @Column(name = "mobile_no4", length = 20)
    private String mobileNo4;

    @Column(name = "email1", length = 100)
    private String email1;

    @Column(name = "email2", length = 100)
    private String email2;

    /*@Column(name="payment_status")
    private int paymentStatus;*/

    /*@OneToMany(fetch = FetchType.EAGER, mappedBy = "tanentID")
    private List<TenantContacts> listofTenantContacts = new ArrayList<>();

    public List<TenantContacts> getListofTenantContacts() {
		return listofTenantContacts;
	}

	public void setListofTenantContacts(List<TenantContacts> listofTenantContacts) {
		this.listofTenantContacts = listofTenantContacts;
	}*/

    @Transient
    private boolean typeDeleteOption;

    @Transient
    private boolean packageEnable;


    @Transient
    private int packageCount;

    @Transient
    private int userType;


    @Column(name = "is_unregistered")
    private Boolean isUnregistered;

    @Column(name = "total_cars")
    private Integer totalCars;

    @Transient
    private Long carmodelid;

    public MultipartFile getImgFile1() {
        return imgFile1;
    }

    public void setImgFile1(MultipartFile imgFile1) {
        this.imgFile1 = imgFile1;
    }

    @Transient
    private MultipartFile imgFile1;

    public Long getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(Long landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Long getContactno() {
        return contactno;
    }

    public void setContactno(Long contactno) {
        this.contactno = contactno;
    }

    public Long getContactno1() {
        return contactno1;
    }

    public void setContactno1(Long contactno1) {
        this.contactno1 = contactno1;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFirstName1() {
        return firstName1;
    }

    public void setFirstName1(String firstName1) {
        this.firstName1 = firstName1;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getMobileNo1() {
        return mobileNo1;
    }

    public void setMobileNo1(String mobileNo1) {
        this.mobileNo1 = mobileNo1;
    }

    public String getMobileNo2() {
        return mobileNo2;
    }

    public void setMobileNo2(String mobileNo2) {
        this.mobileNo2 = mobileNo2;
    }

    public String getMobileNo3() {
        return mobileNo3;
    }

    public void setMobileNo3(String mobileNo3) {
        this.mobileNo3 = mobileNo3;
    }

    public String getMobileNo4() {
        return mobileNo4;
    }

    public void setMobileNo4(String mobileNo4) {
        this.mobileNo4 = mobileNo4;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPanno() {
        return panno;
    }

    public void setPanno(String panno) {
        this.panno = panno;
    }

    public Long getPackageid() {
        return packageid;
    }

    public void setPackageid(Long packageid) {
        this.packageid = packageid;
    }

    public Long getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Long paymentid) {
        this.paymentid = paymentid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTanentid() {
        return tanentid;
    }

    public void setTanentid(String tanentid) {
        this.tanentid = tanentid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public boolean isTypeDeleteOption() {
        return typeDeleteOption;
    }

    public void setTypeDeleteOption(boolean typeDeleteOption) {
        this.typeDeleteOption = typeDeleteOption;
    }

    public boolean isPackageEnable() {
        return packageEnable;
    }

    public void setPackageEnable(boolean packageEnable) {
        this.packageEnable = packageEnable;
    }

    public int getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(int emailStatus) {
        this.emailStatus = emailStatus;
    }

    public boolean getEmailFlag() {
        return emailFlag;
    }

    public void setEmailFlag(boolean emailFlag) {
        this.emailFlag = emailFlag;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /* public CompanyType getCompanytypename() {
         return companytypename;
     }

     public void setCompanytypename(CompanyType companytypename) {
         this.companytypename = companytypename;
     }
 */
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

   /* public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }*/

   /* public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }*/

    public String getGstno() {
        return gstno;
    }

    public void setGstno(String gstno) {
        this.gstno = gstno;
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public int getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(int packageCount) {
        this.packageCount = packageCount;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getAtomLoginId() {
        return atomLoginId;
    }

    public void setAtomLoginId(String atomLoginId) {
        this.atomLoginId = atomLoginId;
    }

    public Long getCarmodelid() {
        return carmodelid;
    }

    public void setCarmodelid(Long carmodelid) {
        this.carmodelid = carmodelid;
    }

    /*public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }*/


    public Boolean getIsUnregistered() {
        return isUnregistered;
    }

    public void setIsUnregistered(Boolean isUnregistered) {
        this.isUnregistered = isUnregistered;
    }

    public Integer getTotalCars() {
        return totalCars;
    }

    public void setTotalCars(Integer totalCars) {
        this.totalCars = totalCars;
    }

}