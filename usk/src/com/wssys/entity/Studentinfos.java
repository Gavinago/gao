package com.wssys.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Studentinfos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="studentinfos")
public class Studentinfos implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="createdate",updatable=false,insertable=false)private Timestamp createdate;
	private String schoolnumber;
	private String name;
	private Integer sex;
	private String birthday;
	private Integer age;
	private String borncode;
	private String origin;
	private String nation;
	private String nationality;
	private String idno;
	private Integer hkproperties;
	private String accountcode;
	private String classnumber;
	private String admissiondate;
	private String entrance;
	private String readmode;
	private String address;
	private String mailingaddress;
	private String homeaddress;
	private String telnumber;
	private String postalcode;
	@Column(name = "is_dsz")
	private Integer isDsz;
	@Column(name = "is_pre_education")
	private Integer isPreEducation;
	@Column(name = "is_lschildren")
	private Integer isLschildren;
	@Column(name = "is_apply")
	private Integer isApply;
	@Column(name = "is_enjoya")
	private Integer isEnjoya;
	@Column(name = "is_orphan")
	private Integer isOrphan;
	@Column(name = "is_special")
	private Integer isSpecial;
	private String sxjl;
	private String sxxfs;
	private Integer sxczxc;
	private String oldname;
	private String idnotime;
	private String bloodtype;
	private String specialty;
	private String stunumber;
	private String stuid;
	private String source;
	private String email;
	private String mainurl;
	
	@Column(name = "disability_type")
	private String disabilityType;
	
	@Column(name = "is_governmentbuuy")
	private Integer isGovernmentBuuy;
	private String sdjd;
	private String membersname1;
	private String membersrelations1;
	private String membersaddress1;
	private String membersresidencecode1;
	private String memberstel1;
	@Column(name="members_isguardian1")
	private Integer membersIsguardian1;
	private String membersidnotype1;
	private String membersidno1;
	private String membersnation1;
	private String membersworkunit1;
	private String memberspost1;
	private String membersname2;
	private String membersrelations2;
	private String membersaddress2;
	private String membersresidencecode2;
	private String memberstel2;
	@Column(name="members_isguardian2")
	private Integer membersIsguardian2;
	private String membersidnotype2;
	private String membersidno2;
	private String membersnation2;
	private String membersworkunit2;
	private String memberspost2;
	@Column(name = "is_workersbaby")
	private Integer isWorkersbaby;

	// Constructors

	/** default constructor */
	public Studentinfos() {
	}

	/** minimal constructor */
	public Studentinfos(Timestamp createdate, Integer sex, Integer age,
			String origin, String nation) {
		this.createdate = createdate;
		this.sex = sex;
		this.age = age;
		this.origin = origin;
		this.nation = nation;
	}

	/** full constructor */
	public Studentinfos(Timestamp createdate, String schoolnumber, String name,
			Integer sex, String birthday, Integer age, String borncode,
			String origin, String nation, String nationality, String idno,
			Integer hkproperties, String accountcode, String classnumber,
			String admissiondate, String entrance, String readmode,
			String address, String mailingaddress, String homeaddress,
			String telnumber, String postalcode, Integer isDsz,
			Integer isPreEducation, Integer isLschildren, Integer isApply,
			Integer isEnjoya, Integer isOrphan, Integer isSpecial, String sxjl,
			String sxxfs, Integer sxczxc, String oldname, String idnotime,
			String bloodtype, String specialty, String stunumber, String stuid,
			String source, String email, String mainurl, String disabilityType,
			Integer isGovernmentBuuy, String sdjd, String membersname1,
			String membersrelations1, String membersaddress1,
			String membersresidencecode1, String memberstel1,
			Integer membersIsguardian1, String membersidnotype1,
			String membersidno1, String membersnation1,
			String membersworkunit1, String memberspost1, String membersname2,
			String membersrelations2, String membersaddress2,
			String membersresidencecode2, String memberstel2,
			Integer membersIsguardian2, String membersidnotype2,
			String membersidno2, String membersnation2,
			String membersworkunit2, String memberspost2, Integer isWorkersbaby) {
		this.createdate = createdate;
		this.schoolnumber = schoolnumber;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.borncode = borncode;
		this.origin = origin;
		this.nation = nation;
		this.nationality = nationality;
		this.idno = idno;
		this.hkproperties = hkproperties;
		this.accountcode = accountcode;
		this.classnumber = classnumber;
		this.admissiondate = admissiondate;
		this.entrance = entrance;
		this.readmode = readmode;
		this.address = address;
		this.mailingaddress = mailingaddress;
		this.homeaddress = homeaddress;
		this.telnumber = telnumber;
		this.postalcode = postalcode;
		this.isDsz = isDsz;
		this.isPreEducation = isPreEducation;
		this.isLschildren = isLschildren;
		this.isApply = isApply;
		this.isEnjoya = isEnjoya;
		this.isOrphan = isOrphan;
		this.isSpecial = isSpecial;
		this.sxjl = sxjl;
		this.sxxfs = sxxfs;
		this.sxczxc = sxczxc;
		this.oldname = oldname;
		this.idnotime = idnotime;
		this.bloodtype = bloodtype;
		this.specialty = specialty;
		this.stunumber = stunumber;
		this.stuid = stuid;
		this.source = source;
		this.email = email;
		this.mainurl = mainurl;
		this.disabilityType = disabilityType;
		this.isGovernmentBuuy = isGovernmentBuuy;
		this.sdjd = sdjd;
		this.membersname1 = membersname1;
		this.membersrelations1 = membersrelations1;
		this.membersaddress1 = membersaddress1;
		this.membersresidencecode1 = membersresidencecode1;
		this.memberstel1 = memberstel1;
		this.membersIsguardian1 = membersIsguardian1;
		this.membersidnotype1 = membersidnotype1;
		this.membersidno1 = membersidno1;
		this.membersnation1 = membersnation1;
		this.membersworkunit1 = membersworkunit1;
		this.memberspost1 = memberspost1;
		this.membersname2 = membersname2;
		this.membersrelations2 = membersrelations2;
		this.membersaddress2 = membersaddress2;
		this.membersresidencecode2 = membersresidencecode2;
		this.memberstel2 = memberstel2;
		this.membersIsguardian2 = membersIsguardian2;
		this.membersidnotype2 = membersidnotype2;
		this.membersidno2 = membersidno2;
		this.membersnation2 = membersnation2;
		this.membersworkunit2 = membersworkunit2;
		this.memberspost2 = memberspost2;
		this.isWorkersbaby = isWorkersbaby;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getSchoolnumber() {
		return this.schoolnumber;
	}

	public void setSchoolnumber(String schoolnumber) {
		this.schoolnumber = schoolnumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBorncode() {
		return this.borncode;
	}

	public void setBorncode(String borncode) {
		this.borncode = borncode;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public Integer getHkproperties() {
		return this.hkproperties;
	}

	public void setHkproperties(Integer hkproperties) {
		this.hkproperties = hkproperties;
	}

	public String getAccountcode() {
		return this.accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getClassnumber() {
		return this.classnumber;
	}

	public void setClassnumber(String classnumber) {
		this.classnumber = classnumber;
	}

	public String getAdmissiondate() {
		return this.admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	public String getEntrance() {
		return this.entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public String getReadmode() {
		return this.readmode;
	}

	public void setReadmode(String readmode) {
		this.readmode = readmode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMailingaddress() {
		return this.mailingaddress;
	}

	public void setMailingaddress(String mailingaddress) {
		this.mailingaddress = mailingaddress;
	}

	public String getHomeaddress() {
		return this.homeaddress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	public String getTelnumber() {
		return this.telnumber;
	}

	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public Integer getIsDsz() {
		return this.isDsz;
	}

	public void setIsDsz(Integer isDsz) {
		this.isDsz = isDsz;
	}

	public Integer getIsPreEducation() {
		return this.isPreEducation;
	}

	public void setIsPreEducation(Integer isPreEducation) {
		this.isPreEducation = isPreEducation;
	}

	public Integer getIsLschildren() {
		return this.isLschildren;
	}

	public void setIsLschildren(Integer isLschildren) {
		this.isLschildren = isLschildren;
	}

	public Integer getIsApply() {
		return this.isApply;
	}

	public void setIsApply(Integer isApply) {
		this.isApply = isApply;
	}

	public Integer getIsEnjoya() {
		return this.isEnjoya;
	}

	public void setIsEnjoya(Integer isEnjoya) {
		this.isEnjoya = isEnjoya;
	}

	public Integer getIsOrphan() {
		return this.isOrphan;
	}

	public void setIsOrphan(Integer isOrphan) {
		this.isOrphan = isOrphan;
	}

	public Integer getIsSpecial() {
		return this.isSpecial;
	}

	public void setIsSpecial(Integer isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String getSxjl() {
		return this.sxjl;
	}

	public void setSxjl(String sxjl) {
		this.sxjl = sxjl;
	}

	public String getSxxfs() {
		return this.sxxfs;
	}

	public void setSxxfs(String sxxfs) {
		this.sxxfs = sxxfs;
	}

	public Integer getSxczxc() {
		return this.sxczxc;
	}

	public void setSxczxc(Integer sxczxc) {
		this.sxczxc = sxczxc;
	}

	public String getOldname() {
		return this.oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	public String getIdnotime() {
		return this.idnotime;
	}

	public void setIdnotime(String idnotime) {
		this.idnotime = idnotime;
	}

	public String getBloodtype() {
		return this.bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getStunumber() {
		return this.stunumber;
	}

	public void setStunumber(String stunumber) {
		this.stunumber = stunumber;
	}

	public String getStuid() {
		return this.stuid;
	}

	public void setStuid(String stuid) {
		this.stuid = stuid;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMainurl() {
		return this.mainurl;
	}

	public void setMainurl(String mainurl) {
		this.mainurl = mainurl;
	}

	public String getDisabilityType() {
		return this.disabilityType;
	}

	public void setDisabilityType(String disabilityType) {
		this.disabilityType = disabilityType;
	}

	public Integer getIsGovernmentBuuy() {
		return this.isGovernmentBuuy;
	}

	public void setIsGovernmentBuuy(Integer isGovernmentBuuy) {
		this.isGovernmentBuuy = isGovernmentBuuy;
	}

	public String getSdjd() {
		return this.sdjd;
	}

	public void setSdjd(String sdjd) {
		this.sdjd = sdjd;
	}

	public String getMembersname1() {
		return this.membersname1;
	}

	public void setMembersname1(String membersname1) {
		this.membersname1 = membersname1;
	}

	public String getMembersrelations1() {
		return this.membersrelations1;
	}

	public void setMembersrelations1(String membersrelations1) {
		this.membersrelations1 = membersrelations1;
	}

	public String getMembersaddress1() {
		return this.membersaddress1;
	}

	public void setMembersaddress1(String membersaddress1) {
		this.membersaddress1 = membersaddress1;
	}

	public String getMembersresidencecode1() {
		return this.membersresidencecode1;
	}

	public void setMembersresidencecode1(String membersresidencecode1) {
		this.membersresidencecode1 = membersresidencecode1;
	}

	public String getMemberstel1() {
		return this.memberstel1;
	}

	public void setMemberstel1(String memberstel1) {
		this.memberstel1 = memberstel1;
	}

	public Integer getMembersIsguardian1() {
		return this.membersIsguardian1;
	}

	public void setMembersIsguardian1(Integer membersIsguardian1) {
		this.membersIsguardian1 = membersIsguardian1;
	}

	public String getMembersidnotype1() {
		return this.membersidnotype1;
	}

	public void setMembersidnotype1(String membersidnotype1) {
		this.membersidnotype1 = membersidnotype1;
	}

	public String getMembersidno1() {
		return this.membersidno1;
	}

	public void setMembersidno1(String membersidno1) {
		this.membersidno1 = membersidno1;
	}

	public String getMembersnation1() {
		return this.membersnation1;
	}

	public void setMembersnation1(String membersnation1) {
		this.membersnation1 = membersnation1;
	}

	public String getMembersworkunit1() {
		return this.membersworkunit1;
	}

	public void setMembersworkunit1(String membersworkunit1) {
		this.membersworkunit1 = membersworkunit1;
	}

	public String getMemberspost1() {
		return this.memberspost1;
	}

	public void setMemberspost1(String memberspost1) {
		this.memberspost1 = memberspost1;
	}

	public String getMembersname2() {
		return this.membersname2;
	}

	public void setMembersname2(String membersname2) {
		this.membersname2 = membersname2;
	}

	public String getMembersrelations2() {
		return this.membersrelations2;
	}

	public void setMembersrelations2(String membersrelations2) {
		this.membersrelations2 = membersrelations2;
	}

	public String getMembersaddress2() {
		return this.membersaddress2;
	}

	public void setMembersaddress2(String membersaddress2) {
		this.membersaddress2 = membersaddress2;
	}

	public String getMembersresidencecode2() {
		return this.membersresidencecode2;
	}

	public void setMembersresidencecode2(String membersresidencecode2) {
		this.membersresidencecode2 = membersresidencecode2;
	}

	public String getMemberstel2() {
		return this.memberstel2;
	}

	public void setMemberstel2(String memberstel2) {
		this.memberstel2 = memberstel2;
	}

	public Integer getMembersIsguardian2() {
		return this.membersIsguardian2;
	}

	public void setMembersIsguardian2(Integer membersIsguardian2) {
		this.membersIsguardian2 = membersIsguardian2;
	}

	public String getMembersidnotype2() {
		return this.membersidnotype2;
	}

	public void setMembersidnotype2(String membersidnotype2) {
		this.membersidnotype2 = membersidnotype2;
	}

	public String getMembersidno2() {
		return this.membersidno2;
	}

	public void setMembersidno2(String membersidno2) {
		this.membersidno2 = membersidno2;
	}

	public String getMembersnation2() {
		return this.membersnation2;
	}

	public void setMembersnation2(String membersnation2) {
		this.membersnation2 = membersnation2;
	}

	public String getMembersworkunit2() {
		return this.membersworkunit2;
	}

	public void setMembersworkunit2(String membersworkunit2) {
		this.membersworkunit2 = membersworkunit2;
	}

	public String getMemberspost2() {
		return this.memberspost2;
	}

	public void setMemberspost2(String memberspost2) {
		this.memberspost2 = memberspost2;
	}

	public Integer getIsWorkersbaby() {
		return this.isWorkersbaby;
	}

	public void setIsWorkersbaby(Integer isWorkersbaby) {
		this.isWorkersbaby = isWorkersbaby;
	}

}