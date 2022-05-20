function validateDate()
    {
    var SDate = document.forms[0].sdate.value;    	
    var EDate = document.forms[0].edate.value;    	
    var alertReason1 =  'End Date must be greater than or equal to  Start Date.' 
    var alertReason2 =  'End Date can not be less than Current Date.';
    var validformat=/^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity

	var arrsDt = SDate.split('/');
	var arreDt = EDate.split('/');
	
	s=arrsDt[1] +  arrsDt[0] ;
	e=arreDt[1] +  arreDt[0] ;

		
 
    if(SDate == '')	
    {
        alert("Please enter Start Date");
        return false;
    }
    else if(EDate == '')	
    {
        alert("Please enter End Date");
        return false;
    }	    
	
    if ((!validformat.test(SDate)) || (!validformat.test(EDate)))
	{
    alert("Invalid Date Format. Please correct and submit again.")
	return false;
    }
	

	  if (arreDt[2]>=arrsDt[2])
	  {
		if (e>=s)
		 return true;
		else
			{
			alert(alertReason1);
			document.forms[0].edate.value='';
			document.forms[0].edate.focus();
			return false;
			}
	   }
	   else
		{
		alert(alertReason1);
		document.forms[0].edate.value='';
		document.forms[0].edate.focus();
		return false;
		}
	        	   

	
}
