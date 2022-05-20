<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ page import="com.aristo.valueobject.LoginFormBean" %>
<%@ page import="com.aristo.form.LoginForm" %>



<html:html>

 

   <body>
      <html:form action="AddEntry.do?actionRequested=AddEntry" styleId="myForm" method="Post"  onsubmit="return checksale();">
         <table style="border-collapse: collapse; border-width: 0px;">
            <tr>
               <td>1. Target%/Target Value </td>
               <td>

                  <html:text property="bud_per" tabindex="1" size="5" readonly="true" />
                  <html:text property="sales_bud" tabindex="2" size="9" readonly="true" />

               </td>

               <td>1. Collection Today</td>
               <td>
                  <html:text property="collc" tabindex="8" readonly="true" />
               </td>
            </tr>
            <tr>
               <td>2. CN 100%</td>
               <td>
                  <html:text property="cn100" tabindex="3"  readonly="true"/>
               </td>
               <td>2. Collection Cumm.</td>
               <td>
                  <html:text property="collcumm" tabindex="9" readonly="true" />
               </td>
            </tr>


            <tr>
               <td>3. Net Sales Today </td>
               <td>
                  <html:text property="sale_today" tabindex="4" readonly="true" />
               </td>
               <td>3. Remittance Today</td>
               <td>
                  <html:text property="remit" tabindex="10"  readonly="true"/>
               </td>
            </tr>
            <tr>
               <td>4. Net Sales Upto Date</td>
               <td>
                  <html:text property="trd_sale" tabindex="5" readonly="true" />
               </td>
               <td>4. Remittance Cumm.</td>
               <td>
                  <html:text property="remitcumm" tabindex="11"  readonly="true"/>
               </td>
            </tr>
            <tr>
               <td>5 Last Month Sales </td>
               <td>
                  <html:text property="lmsale" tabindex="6" readonly="true" />
               </td>
               <td>5. Outstanding as on Date</td>
               <td>
                  <html:text property="outstnd" tabindex="12" readonly="true" />
               </td>
            </tr>
            <tr>
               <td>6. Last Year Sales </td>
               <td>
                  <html:text property="lysale" tabindex="7"  readonly="true"/>
               </td>
               <td>6. Monthly Billing Status</td>
               <td>
                  <html:select property="status" tabindex="13" disabled="disabled">
                     <html:option value="Open">Open</html:option>
                     <html:option value="Close">Close</html:option>
                  </html:select>
               </td>

            </tr>

            <tr>
               <td></td>
               <td></td>
               <td colspan="2" style="color:red; text-align: center;">*Enter all figures in Lakhs</td>

            </tr>
            <tr>
               <td></td>
               <td></td>
               <td></td>
               <td></td>
            </tr>
         </table>
         </td>
         </tr>






      </html:form>









   </body>

</html:html>