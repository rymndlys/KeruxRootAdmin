<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, com.kerux.security.Security, com.kerux.controllers.MainController"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ taglib uri="/struts-dojo-tags" prefix="sx" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actions</title>

<s:head/>
<sx:head />
     

<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">



<style>
	.optionalfield{
	display:none;
	}
</style>
     
</head>
<body>

<a href="index.jsp">HOME</a>
	

<s:form action='actions'>
<s:submit value="Functions"></s:submit>
</s:form>

<hr>

<div class="btn-group" role="group" aria-label="functions">
<button type="button" class="btn btn-secondary" id="EnClinic">Enroll Clinic</button>
<button type="button" class="btn btn-secondary" id="MaClinic">Manage Clinic</button>
<button type="button" class="btn btn-secondary" id="EnAdmin">Enroll Admin</button>
<button type="button" class="btn btn-secondary" id="MaAdmin">Manage Admin</button>
</div>

<s:if test="hasActionMessages()">
   <div class="welcome">
      <s:actionmessage/>
   </div>
</s:if>

<s:if test="hasActionErrors()">
	<div class="errorDiv">
		<s:actionerror/>
	</div>
</s:if>

<div id="hideable_EnClinic" style="display:block">
ENROLL CLINIC

<s:form action = "clinicenroll"  theme="simple">
<s:textfield type="text"  name="clinicname" required="required" size="50" placeholder="Enter Clinic Name"/>
<s:textfield type="text"  name="address" required="required" size="50" placeholder="Enter Address" />
<s:textfield  type="number" placeholder="Enter Contact Number" name="contactno" required="required"/>

	<s:select label="Set Day and Time"
		headerKey="-1" headerValue="Select Day"
		list="#{'Monday':'Monday', 'Tuesday':'Tuesday', 'Wednesday':'Wednesday', 'Thursday':'Thursday', 'Friday':'Friday', 'Saturday':'Saturday', 'Sunday':'Sunday'}"
		name="clinicdays" id="clinicdays" />

<div id="panelM" style="display:none">
Monday
<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1M" /> to
<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2M" />
</div>

<div id="panelT" style="display:none">
Tuesday
<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1T" /> to
<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2T" />
</div>

<div id="panelW" style="display:none">
Wednesday
<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1W" /> to
<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2W" />
</div>

<div id="panelTH" style="display:none">
Thursday
<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1TH" /> to
<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2TH" />
</div>

<div id="panelF" style="display:none">
Friday
<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1F" /> to
<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2F" />
</div>

<div id="panelSat" style="display:none">
Saturday
<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1Sat" /> to
<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2Sat" />
</div>

<div id="panelSun" style="display:none">
Sunday
<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1Sun" /> to
<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2Sun" />
</div>

<s:checkboxlist label="Services" name="services" list="servicelist" ></s:checkboxlist>
<s:submit value="Submit"></s:submit>
</s:form>

</div>
      
      
      
<div id="hideable_MaClinic" style="display:none">
MANAGE CLINIC
		 
		
<s:form action='clinicunenroll' method='post'   theme="simple">
<sx:autocompleter list="clinics" name="clinicid" listValue="clinicname" listKey="clinicid" id="clinicun"/>
<s:hidden  value="" name="clinic" id="clinicunhidden"/>
<s:select label="Unenroll Clinic" headerValue="Change status"
list="#{'Unenrolled':'Unenrolled', 'Closed':'Closed', 'Unable to pay':'Unable to pay', 'Others..':'Others..'}" 
name="status" value="Unenrolled" id="selectReasonC"/>
<s:textfield  type="text" placeholder="Enter other reason" name="reason" id="reasonclinic" cssClass="optionalfield"/>
<s:submit value="REVOKE PRIVILEGES" cssClass="revokeButton" onclick="setValues()" ></s:submit>		 
</s:form>

</div>




<div id="hideable_EnAdmin" style="display:none" >
ENROLL ADMIN

<s:form action = "adminenroll"   theme="simple">
Clinic:
<s:select list="clinics" label="Select clinic for admin" listKey="clinicid" listValue="clinicname" name="clinicid" ></s:select>
<s:textfield type="text" name="firstname" required="required" size="50" placeholder="Enter Admin First Name"/><s:fielderror fieldName="FirstName"/>
<s:textfield type="text" name="lastname" required="required" size="50" placeholder="Enter Admin Last Name"/><s:fielderror fieldName="LastName"/>
<s:textfield type="text" name="email" required="required" size="50" placeholder="Enter Email"/><s:fielderror fieldName="Email"/>
<s:textfield  type="number" name="accesslevel" required="required" placeholder="Enter Access Level"/>
<s:submit value="SUBMIT" cssClass="submitButton"></s:submit>
</s:form>

</div>
      
      
      
<div id="hideable_MaAdmin" style="display:none">
SELECT ADMINISTRATOR
			 
		
<s:form action='adminunenroll' method='post'   theme="simple">
<sx:autocompleter label="Admin"
list="admins" name="adminid" listKey="adminid" listValue="firstname" id="adminun" />
<s:hidden  value="" name="admin" id="adminunhidden"/>
<s:select label="Unenroll Admin" headerValue="Change status"
list="#{'Removed from position':'Removed from position', 'Change job':'Change job', 'Committed Crime':'Commited Crime', 'Others..':'Others..'}"
name="status" value="Removed from position" id="selectReasonA"/>
<s:textfield  type="text" placeholder="Enter other reason" name="reason" id="reasonadmin" cssClass="optionalfield"/>
<s:submit value="REVOKE PRIVILEGES" cssClass="revokeButton" onclick="setValues2()" ></s:submit>
		 
</s:form>
</div>
      


<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script type="text/javascript">
	

function setValues() {
    var autoCompleter = dojo.widget.byId("clinicun");
   
	var key = autoCompleter.getSelectedKey();
	document.getElementById("clinicunhidden").value = key;
	
 }

function setValues2() {
    var autoCompleter = dojo.widget.byId("adminun");
   
	var key = autoCompleter.getSelectedKey();
	document.getElementById("adminunhidden").value = key;
	
 }
  $(document).ready(function () {
	  

	  

	 
	  
	$('#selectReasonC').on('change', function() {
		var x=document.getElementById('selectReasonC').value;
		var r=document.getElementById('reasonclinic');
		
		if (x=="Others.."){
			r.style.display='block';	
		}
		else{
			r.style.display='none';
		}
	});
	
	$('#selectReasonA').on('change', function() {
		var x=document.getElementById('selectReasonA').value;
		var r=document.getElementById('reasonadmin');
		
		if (x=="Others.."){
			r.style.display='block';	
		}
		else{
			r.style.display='none';
		}
	});
			
	$('#clinicdays').on('change', function() {
		var x=document.getElementById('clinicdays').value;
    	var m=document.getElementById('panelM');
    	var t=document.getElementById('panelT');
    	var w=document.getElementById('panelW');
    	var th=document.getElementById('panelTH');
    	var f=document.getElementById('panelF');
    	var sat=document.getElementById('panelSat');
    	var sun=document.getElementById('panelSun');
    	
    	if(x=='Monday'){
    		m.style.display='block';
    		t.style.display='none';
    		w.style.display='none';
    		th.style.display='none';
    		f.style.display='none';
    		sat.style.display='none';
    		sun.style.display='none';
    	}
    	else if(x=="Tuesday"){
    		m.style.display='none';
    		t.style.display='block';
    		w.style.display='none';
    		th.style.display='none';
    		f.style.display='none';
    		sat.style.display='none';
    		sun.style.display='none';
    	}
    	else if(x=="Wednesday"){
    		m.style.display='none';
    		t.style.display='none';
    		w.style.display='block';
    		th.style.display='none';
    		f.style.display='none';
    		sat.style.display='none';
    		sun.style.display='none';
    	}
    	else if(x=="Thursday"){
    		m.style.display='none';
    		t.style.display='none';
    		w.style.display='none';
    		th.style.display='block';
    		f.style.display='none';
    		sat.style.display='none';
    		sun.style.display='none';
    	}
    	else if(x=="Friday"){
    		m.style.display='none';
    		t.style.display='none';
    		w.style.display='none';
    		th.style.display='none';
    		f.style.display='block';
    		sat.style.display='none';
    		sun.style.display='none';
    	}
    	else if(x=="Saturday"){
    		m.style.display='none';
    		t.style.display='none';
    		w.style.display='none';
    		th.style.display='none';
    		f.style.display='none';
    		sat.style.display='block';
    		sun.style.display='none';
    	}
    	else if(x=="Sunday"){
    		m.style.display='none';
    		t.style.display='none';
    		w.style.display='none';
    		th.style.display='none';
    		f.style.display='none';
    		sat.style.display='none';
    		sun.style.display='block';
    	}
    	else{
    		m.style.display='none';
    		t.style.display='none';
    		w.style.display='none';
    		th.style.display='none';
    		f.style.display='none';
    		sat.style.display='none';
    		sun.style.display='none';
    	}
    	
	});
    //$('.clinicButton').on('click', function () {
    	
    //	clinic_id = $(this).attr('data-val');
    //    $('#hiddenFieldClinic').attr("value", clinic_id);
       
    //});
    //$('.adminButton').on('click', function () {
    	
    //	admin_id = $(this).attr('data-val');
    //    $('#hiddenFieldAdmin').attr("value", admin_id);

    //});
    $('#EnClinic').on('click', function () {
    	var x=document.getElementById('hideable_EnClinic');
    	var y=document.getElementById('hideable_MaClinic');
    	var z=document.getElementById('hideable_EnAdmin');
    	var a=document.getElementById('hideable_MaAdmin');
    	if (x.style.display=='none'){
    		x.style.display = 'block';
    		y.style.display = 'none';
    		z.style.display = 'none';
    		a.style.display = 'none';
    	}
    	else{
    		
    	}
    });
    $('#MaClinic').on('click', function () {
    	var x=document.getElementById('hideable_EnClinic');
    	var y=document.getElementById('hideable_MaClinic');
    	var z=document.getElementById('hideable_EnAdmin');
    	var a=document.getElementById('hideable_MaAdmin');
    	if (y.style.display=='none'){
    		x.style.display = 'none';
    		y.style.display = 'block';
    		z.style.display = 'none';
    		a.style.display = 'none';
    	}
    	else{
    		
    	}
    });
    $('#EnAdmin').on('click', function () {
    	var x=document.getElementById('hideable_EnClinic');
    	var y=document.getElementById('hideable_MaClinic');
    	var z=document.getElementById('hideable_EnAdmin');
    	var a=document.getElementById('hideable_MaAdmin');
    	if (z.style.display=='none'){
    		x.style.display = 'none';
    		y.style.display = 'none';
    		z.style.display = 'block';
    		a.style.display = 'none';
    	}
    	else{
    		
    	}
    });
    $('#MaAdmin').on('click', function () {
    	var x=document.getElementById('hideable_EnClinic');
    	var y=document.getElementById('hideable_MaClinic');
    	var z=document.getElementById('hideable_EnAdmin');
    	var a=document.getElementById('hideable_MaAdmin');
    	if (a.style.display=='none'){
    		x.style.display = 'none';
    		y.style.display = 'none';
    		z.style.display = 'none';
    		a.style.display = 'block';
    	}
    	else{
    		
    	}
    });
  });
  
</script>

</body>
</html>