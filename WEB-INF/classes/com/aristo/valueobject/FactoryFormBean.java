package com.aristo.valueobject;

import java.io.Serializable;
import java.util.Date;

public class FactoryFormBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private	String documentno;
	private	int	invoiceno;
	private	Date preparationdate;
	private	int	preparationhours;
	private	int	preparationmin;
	private	Date despatchdate;
	private	int	despatchhours;
	private	int	despatchmin;
	private	String transporteid;
	private	String vehicleno;
	private	String freightstatus;
	private	Date dcdate;
	private	int	dctype;
	private	int	foodyn;
	private	int	medicineyn;
	private	String	branchid;
	private	String	lrno;
	private	Date	lrdate	;
	private	int	formno	;
	private	int	comminvoiceyn	;
	private	int	comminvoiceno	;
	private	String	remarks	;
	private	String	locationid	;
	private	String	companyid	;
	private	int	salestaxpercent	;
	private	int	othertaxpercent	;
	private	int	boxes	;
	private	double	weight	;
	private	double	value	;
	private	String	controlsampleyn	;
	private	String	roadpermno	;
	private	String	exported	;
	private	String	productid	;
	private	String	unitid	;
	private	int	unit	;
	private	String	batchno	;
	private	int	batchsize	;
	private	Date	mfgdate	;
	private	Date	expdate	;
	private	String	lotid	;
	private	int	percaseqty	;
	private	int	noofcase	;
	private	int	quantity	;
	private	String	detailremarks	;
	private	String	arno	;
	private	double	grossweight	;
	private	String	slipno	;
	private	int	percaselooseqty	;
	private	int	noofloosecase	;
	private	int	loosequantity	;
	private	int	totalqty	;
	private	double	mrp	;
	private	double	stockistinclusiveexcise	;
	private	double	stockistexclusiveexcise	;
	private	double	invvalue	;
	private	double	discountamt	;
	private	int	discountqty	;
	private	int	freeqty	;
	private	double	mrpinclusiveexcise	;
	private	double	mrpexclusiveexcise	;
	private	double	retailinclexcise	;
	private	double	retailexclexcise	;
	private	double	retaildiscount	;
	private	double	comminclexcise	;
	private	double	commenclexcise	;
	private	double	excisepercent	;
	private	double	abatementpercent	;
	private	double	assessablevalue	;
	private	double	totalassessablevalue	;
	private	double	totalexciseduty	;
	private	int	totalquantity	;
	private	double	abatementamount	;
	private	double	mrprateperunit	;
	private	int	dci_prl_free_qty	;
	private	double	invoicecessrate	;
	private	double	totalinvoicecesspay	;
	private	int	invpercent	;
	private	int	cesspercent	;
	private	String	location	;
	private	String	company	;
	private	String	productname	;
	
	public double getAbatementamount() {
		return abatementamount;
	}
	public void setAbatementamount(double abatementamount) {
		this.abatementamount = abatementamount;
	}
	public double getAbatementpercent() {
		return abatementpercent;
	}
	public void setAbatementpercent(double abatementpercent) {
		this.abatementpercent = abatementpercent;
	}
	public String getArno() {
		return arno;
	}
	public void setArno(String arno) {
		this.arno = arno;
	}
	public double getAssessablevalue() {
		return assessablevalue;
	}
	public void setAssessablevalue(double assessablevalue) {
		this.assessablevalue = assessablevalue;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public int getBatchsize() {
		return batchsize;
	}
	public void setBatchsize(int batchsize) {
		this.batchsize = batchsize;
	}
	public int getBoxes() {
		return boxes;
	}
	public void setBoxes(int boxes) {
		this.boxes = boxes;
	}
	public String getBranchid() {
		return branchid;
	}
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
	public int getCesspercent() {
		return cesspercent;
	}
	public void setCesspercent(int cesspercent) {
		this.cesspercent = cesspercent;
	}
	public double getCommenclexcise() {
		return commenclexcise;
	}
	public void setCommenclexcise(double commenclexcise) {
		this.commenclexcise = commenclexcise;
	}
	public double getComminclexcise() {
		return comminclexcise;
	}
	public void setComminclexcise(double comminclexcise) {
		this.comminclexcise = comminclexcise;
	}
	public int getComminvoiceno() {
		return comminvoiceno;
	}
	public void setComminvoiceno(int comminvoiceno) {
		this.comminvoiceno = comminvoiceno;
	}
	public int getComminvoiceyn() {
		return comminvoiceyn;
	}
	public void setComminvoiceyn(int comminvoiceyn) {
		this.comminvoiceyn = comminvoiceyn;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getControlsampleyn() {
		return controlsampleyn;
	}
	public void setControlsampleyn(String controlsampleyn) {
		this.controlsampleyn = controlsampleyn;
	}
	public Date getDcdate() {
		return dcdate;
	}
	public void setDcdate(Date dcdate) {
		this.dcdate = dcdate;
	}
	public int getDci_prl_free_qty() {
		return dci_prl_free_qty;
	}
	public void setDci_prl_free_qty(int dci_prl_free_qty) {
		this.dci_prl_free_qty = dci_prl_free_qty;
	}
	public int getDctype() {
		return dctype;
	}
	public void setDctype(int dctype) {
		this.dctype = dctype;
	}
	public Date getDespatchdate() {
		return despatchdate;
	}
	public void setDespatchdate(Date despatchdate) {
		this.despatchdate = despatchdate;
	}
	public int getDespatchhours() {
		return despatchhours;
	}
	public void setDespatchhours(int despatchhours) {
		this.despatchhours = despatchhours;
	}
	public int getDespatchmin() {
		return despatchmin;
	}
	public void setDespatchmin(int despatchmin) {
		this.despatchmin = despatchmin;
	}
	public String getDetailremarks() {
		return detailremarks;
	}
	public void setDetailremarks(String detailremarks) {
		this.detailremarks = detailremarks;
	}
	public double getDiscountamt() {
		return discountamt;
	}
	public void setDiscountamt(double discountamt) {
		this.discountamt = discountamt;
	}
	public int getDiscountqty() {
		return discountqty;
	}
	public void setDiscountqty(int discountqty) {
		this.discountqty = discountqty;
	}
	public String getDocumentno() {
		return documentno;
	}
	public void setDocumentno(String documentno) {
		this.documentno = documentno;
	}
	public double getExcisepercent() {
		return excisepercent;
	}
	public void setExcisepercent(double excisepercent) {
		this.excisepercent = excisepercent;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public String getExported() {
		return exported;
	}
	public void setExported(String exported) {
		this.exported = exported;
	}
	public int getFoodyn() {
		return foodyn;
	}
	public void setFoodyn(int foodyn) {
		this.foodyn = foodyn;
	}
	public int getFormno() {
		return formno;
	}
	public void setFormno(int formno) {
		this.formno = formno;
	}
	public int getFreeqty() {
		return freeqty;
	}
	public void setFreeqty(int freeqty) {
		this.freeqty = freeqty;
	}
	public String getFreightstatus() {
		return freightstatus;
	}
	public void setFreightstatus(String freightstatus) {
		this.freightstatus = freightstatus;
	}
	public double getGrossweight() {
		return grossweight;
	}
	public void setGrossweight(double grossweight) {
		this.grossweight = grossweight;
	}
	public double getInvoicecessrate() {
		return invoicecessrate;
	}
	public void setInvoicecessrate(double invoicecessrate) {
		this.invoicecessrate = invoicecessrate;
	}
	public int getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(int invoiceno) {
		this.invoiceno = invoiceno;
	}
	public int getInvpercent() {
		return invpercent;
	}
	public void setInvpercent(int invpercent) {
		this.invpercent = invpercent;
	}
	public double getInvvalue() {
		return invvalue;
	}
	public void setInvvalue(double invvalue) {
		this.invvalue = invvalue;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public int getLoosequantity() {
		return loosequantity;
	}
	public void setLoosequantity(int loosequantity) {
		this.loosequantity = loosequantity;
	}
	public String getLotid() {
		return lotid;
	}
	public void setLotid(String lotid) {
		this.lotid = lotid;
	}
	public Date getLrdate() {
		return lrdate;
	}
	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}

	public String getLrno() {
		return lrno;
	}
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}
	public int getMedicineyn() {
		return medicineyn;
	}
	public void setMedicineyn(int medicineyn) {
		this.medicineyn = medicineyn;
	}
	public Date getMfgdate() {
		return mfgdate;
	}
	public void setMfgdate(Date mfgdate) {
		this.mfgdate = mfgdate;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public double getMrpexclusiveexcise() {
		return mrpexclusiveexcise;
	}
	public void setMrpexclusiveexcise(double mrpexclusiveexcise) {
		this.mrpexclusiveexcise = mrpexclusiveexcise;
	}
	public double getMrpinclusiveexcise() {
		return mrpinclusiveexcise;
	}
	public void setMrpinclusiveexcise(double mrpinclusiveexcise) {
		this.mrpinclusiveexcise = mrpinclusiveexcise;
	}
	public double getMrprateperunit() {
		return mrprateperunit;
	}
	public void setMrprateperunit(double mrprateperunit) {
		this.mrprateperunit = mrprateperunit;
	}
	public int getNoofcase() {
		return noofcase;
	}
	public void setNoofcase(int noofcase) {
		this.noofcase = noofcase;
	}
	public int getNoofloosecase() {
		return noofloosecase;
	}
	public void setNoofloosecase(int noofloosecase) {
		this.noofloosecase = noofloosecase;
	}
	public int getOthertaxpercent() {
		return othertaxpercent;
	}
	public void setOthertaxpercent(int othertaxpercent) {
		this.othertaxpercent = othertaxpercent;
	}
	public int getPercaselooseqty() {
		return percaselooseqty;
	}
	public void setPercaselooseqty(int percaselooseqty) {
		this.percaselooseqty = percaselooseqty;
	}
	public int getPercaseqty() {
		return percaseqty;
	}
	public void setPercaseqty(int percaseqty) {
		this.percaseqty = percaseqty;
	}
	public Date getPreparationdate() {
		return preparationdate;
	}
	public void setPreparationdate(Date preparationdate) {
		this.preparationdate = preparationdate;
	}
	public int getPreparationhours() {
		return preparationhours;
	}
	public void setPreparationhours(int preparationhours) {
		this.preparationhours = preparationhours;
	}
	public int getPreparationmin() {
		return preparationmin;
	}
	public void setPreparationmin(int preparationmin) {
		this.preparationmin = preparationmin;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public double getRetaildiscount() {
		return retaildiscount;
	}
	public void setRetaildiscount(double retaildiscount) {
		this.retaildiscount = retaildiscount;
	}
	public double getRetailexclexcise() {
		return retailexclexcise;
	}
	public void setRetailexclexcise(double retailexclexcise) {
		this.retailexclexcise = retailexclexcise;
	}
	public double getRetailinclexcise() {
		return retailinclexcise;
	}
	public void setRetailinclexcise(double retailinclexcise) {
		this.retailinclexcise = retailinclexcise;
	}
	public int getSalestaxpercent() {
		return salestaxpercent;
	}
	public void setSalestaxpercent(int salestaxpercent) {
		this.salestaxpercent = salestaxpercent;
	}
	public String getSlipno() {
		return slipno;
	}
	public void setSlipno(String slipno) {
		this.slipno = slipno;
	}
	public double getStockistexclusiveexcise() {
		return stockistexclusiveexcise;
	}
	public void setStockistexclusiveexcise(double stockistexclusiveexcise) {
		this.stockistexclusiveexcise = stockistexclusiveexcise;
	}
	public double getStockistinclusiveexcise() {
		return stockistinclusiveexcise;
	}
	public void setStockistinclusiveexcise(double stockistinclusiveexcise) {
		this.stockistinclusiveexcise = stockistinclusiveexcise;
	}
	public double getTotalassessablevalue() {
		return totalassessablevalue;
	}
	public void setTotalassessablevalue(double totalassessablevalue) {
		this.totalassessablevalue = totalassessablevalue;
	}
	public double getTotalexciseduty() {
		return totalexciseduty;
	}
	public void setTotalexciseduty(double totalexciseduty) {
		this.totalexciseduty = totalexciseduty;
	}
	public double getTotalinvoicecesspay() {
		return totalinvoicecesspay;
	}
	public void setTotalinvoicecesspay(double totalinvoicecesspay) {
		this.totalinvoicecesspay = totalinvoicecesspay;
	}
	public int getTotalqty() {
		return totalqty;
	}
	public void setTotalqty(int totalqty) {
		this.totalqty = totalqty;
	}
	public int getTotalquantity() {
		return totalquantity;
	}
	public void setTotalquantity(int totalquantity) {
		this.totalquantity = totalquantity;
	}
	public String getTransporteid() {
		return transporteid;
	}
	public void setTransporteid(String transporteid) {
		this.transporteid = transporteid;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setRoadpermno(String roadpermno) {
		this.roadpermno = roadpermno;
	}
	public String getRoadpermno() {
		return roadpermno;
	}
	
	
	
}
