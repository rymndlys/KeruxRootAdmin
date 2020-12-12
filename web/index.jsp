
<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
     <%@ taglib uri="/struts-tags" prefix="s" %>
     <%@ taglib uri="/struts-dojo-tags" prefix="sx" %>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Kerux Root Admin Web App</title>
    <s:head/>
<sx:head />
    <!-- Styles -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:500,700&display=swap&subset=latin-ext" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600&display=swap&subset=latin-ext" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/fontawesome-all.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	<link href="dist/attention.css" rel="stylesheet">

	<link rel="stylesheet" href="css/chosen.css">
    <link rel="icon" href="images/KeruxLogo-notext.png">
<style>

table {
    width: 100%;
    display:block;
}
thead {
    display: inline-block;
    width: 100%;
    height: 20px;
}
tbody {
    height: 500px;
    display: inline-block;
    width: 100%;
    overflow: auto;
}
	tr{
	font-weight: 400;
    border-bottom: 2px ;
    border-right: 30px ;
    border-left: 30px ;

    padding: 8px 2px;
    width:100%;
	}
	th, td{
	padding: 8px 30px;
	}
	.optionalfield{
	display:none;
	}
	
	
	
/* The Modal (background) */
.modal {
display: block;
  position: fixed; /* Stay in place */
  z-index: 1031; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}


/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

.centeredactions{
	text-align:center;
}

.btnbar{
background-color:#d0262a;
border: 1px solid #d0262a;
box-shadow: 0 0 0 0 transparent !important;
}

.btnbar:hover{
  background-color: #272727;
  border: 1px solid #272727;
}

.btnbar:focus, .btnbar.focus{
box-shadow: 0 0 0 0 transparent;
}

.marginY{
	margin-top: 12px !important;
	margin-bottom: 5px !important;
}

.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/ On mouse-over, add a grey background color /
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/ When the checkbox is checked, add a blue background /
.container input:checked ~ .checkmark {
  background-color: #2196F3;
}

/ Create the checkmark/indicator (hidden when not checked) /
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/ Show the checkmark when checked /
.container input:checked ~ .checkmark:after {
  display: block;
}

/ Style the checkmark/indicator */
.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}

.btnSubmit-solid-lg {
    width:400px;
    display: inline-block;
    padding: 1.375rem 2.125rem 1.375rem 2.125rem;
    border: 0.125rem solid #d0262a;
    border-radius: 0.25rem;
    background-color: #d0262a;
    color: #fff;
    font: 700 1rem/0 "Montserrat", sans-serif;
    text-decoration: none;
    transition: all 0.2s;
}

.btnSubmit-solid-lg:hover {
    background-color: #272727;
    color: #ffffff;
    text-decoration: none;
}

.textbox {
    width: 450px;
    padding: 5px 10px;
    color: #272727;
    text-shadow: 1px 1px 0 #FFF;
    background: #FFF;
    border: 1px solid #FFF;
    border-radius: 5px;
    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.50);
    -moz-box-shadow: inset 0 1px 3px rgba(0,0,0,0.50);
    -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.50);
    margin-bottom: 10px;
}
  .textbox:focus {
    background: white;

    box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25);
    -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.25);
    -webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.25);
    outline: 0;
}
  .textbox:hover {
    background: #fee3e4;

}

</style>
</head>

<body data-spy="scroll" data-target=".fixed-top">

<script src="dist/attention.js"></script>
<!-- icon para sa loading -->
<!-- 	<div class="spinner-wrapper">
        <div class="spinner">
            <div class="bounce1"></div>
            <div class="bounce2"></div>
            <div class="bounce3"></div>
        </div>
    </div> -->
    
      <!-- Navbar -->
    <nav class="navbar navbar-expand-md navbar-dark navbar-custom fixed-top">
  
        <!-- Image Logo -->
        <a class="navbar-brand logo-image" href="#header"><img src="images/KeruxLogoW.png"></a>
        
        
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav ml-auto">
            	<li class="nav-item">
                	<div class="dropdown show">
					  <a class="nav-link page-scroll dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Functions
					  </a>
					
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" style="background-color:black">
					    <a class="nav-link page-scroll dropdown-item" href="#" id="EnClinic">Enroll Clinic</a>
					    <a class="nav-link page-scroll dropdown-item" href="#" id="MaClinic">Manage Clinic</a>
					    <a class="nav-link page-scroll dropdown-item" href="#" id="EnAdmin">Enroll Admin</a>
					    <a class="nav-link page-scroll dropdown-item" href="#" id="MaAdmin">Manage Admin</a>
					    <a class="nav-link page-scroll dropdown-item" href="#" id="Statistics">Reports</a>
					  </div>
					</div>
                </li>

                <li class="nav-item">
                <div class="dropdown show">
					  <a class="nav-link page-scroll dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<s:property value = "rootadminname"/>
					  </a>
					
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" style="background-color:black">
					    <a class="nav-link page-scroll dropdown-item" href="logout.jsp">Logout</a>
					  </div>
					</div>
                    
                </li>

            </ul>
        </div>
      
    </nav> <!-- end of navbar -->
    <!-- end of navbar -->
    
      
    
<s:if test="actionMessages!=null && actionMessages.size > 0">
    <div id="myModal" class="modal">
    
    

  <!-- Modal content -->
  <div class="modal-content">
        <s:iterator value="actionMessages" >
			<s:property />
        </s:iterator>      

  </div>

</div>
</s:if>

<s:if test="actionErrors!=null && actionErrors.size > 0">
    <div id="myModal1" class="modal">

  <!-- Modal content -->
  <div class="modal-content" >

        <s:iterator value="actionErrors" >
			<s:property />
        </s:iterator>           

  </div>

</div>
</s:if>




  <header id="header" class="header">
        <div class="header-content">
            <div class="container">
                <div class="row" >
                    <div class="col-lg-12">
                        <div class="text-container" >

<div id="hideable_EnClinic" style="display:block">
        <!-- Queue -->
    <div class="form-1">
        <div class="container " style="min-height: 40vh;">
            <div class="row mx-auto">
                

				
				
				<s:form action = "clinicenroll"  theme="simple" cssClass="mx-auto">
				<h3 style="color:#d0262a">ENROLL CLINIC</h3><br>
				<h4 style="color:#d0262a">Clinic Information</h4>
				<s:textfield type="text"  cssClass="textbox" name="clinicname" required="required" size="50" placeholder="Enter Clinic Name"/><br>
				<s:textfield type="text"  cssClass="textbox" name="cliniclicense" required="required" size="50" placeholder="Enter Clinic License" /><br>
				<s:textfield  type="number" cssClass="textbox" placeholder="Enter Contact Number" name="contactno" required="required"/><br><br>
				<h4 style="color:#d0262a">Address</h4>
				<s:textfield type="text"  cssClass="textbox" name="street" required="required" height="13" size="50" placeholder="Enter Street" /><br>
				<s:textfield type="text"  cssClass="textbox" name="barangay" required="required" size="50" placeholder="Enter Barangay" /><br>
				<s:textfield type="text"  cssClass="textbox" name="city" required="required" size="50" placeholder="Enter City" /><br>
				<s:textfield type="text"  cssClass="textbox" name="province" required="required" size="50" placeholder="Enter Province" /><br><br>

				<h4 style="color:#d0262a">Clinic Days & Hours:</h4>
					<s:select label="Set Day and Time"
						headerKey="-1" headerValue="Select Day"
						list="#{'Monday':'Monday', 'Tuesday':'Tuesday', 'Wednesday':'Wednesday', 'Thursday':'Thursday', 'Friday':'Friday', 'Saturday':'Saturday', 'Sunday':'Sunday'}"
						name="clinicdays" id="clinicdays" /><br>
				
				<div id="panelM" style="display:none">
				Monday
				<s:textfield name="clinichours1M" min="1"  max="12" maxlength="2" size="2" type="number" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins1M"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix1M"/> to
				<s:textfield type="number"  name="clinichours2M" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins2M"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix2M"/>
				</div>
				
				<div id="panelT" style="display:none">
				Tuesday
				<s:textfield type="number"  name="clinichours1T" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins1T"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix1T"/> to
				<s:textfield type="number"  name="clinichours2T" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins2T"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix2T"/>
				</div>
				
				<div id="panelW" style="display:none">
				Wednesday
				<s:textfield type="number"  name="clinichours1W" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins1W"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix1W"/> to
				<s:textfield type="number"  name="clinichours2W" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins2W"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix2W"/>
				</div>
				
				<div id="panelTH" style="display:none">
				Thursday
				<s:textfield type="number"  name="clinichours1TH" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins1TH"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix1TH"/> to
				<s:textfield type="number"  name="clinichours2TH" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins2TH"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix2TH"/>
				</div>
				
				<div id="panelF" style="display:none">
				Friday
				<s:textfield type="number"  name="clinichours1F" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins1F"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix1F"/> to
				<s:textfield type="number"  name="clinichours2F" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins2F"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix2F"/>
				</div>
				
				<div id="panelSat" style="display:none">
				Saturday
				<s:textfield type="number"  name="clinichours1Sat" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins1Sat"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix1Sat"/> to
				<s:textfield type="number"  name="clinichours2Sat" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins2Sat"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix2Sat"/>
				</div>
				
				<div id="panelSun" style="display:none">
				Sunday
				<s:textfield type="number"  name="clinichours1Sun" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins1Sun"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix1Sun"/> to
				<s:textfield type="number"  name="clinichours2Sun" min="1"  max="12" maxlength="2" size="2" onKeyUp=" if (isNaN(this.value) || this.value < 1 || this.value > 12) {this.value='' } "/>
				<s:select headerKey="00" headerValue="00" list="#{'30':'30'}" name="clinicmins2Sun"/>
				<s:select headerKey="am" headerValue="am" list="#{'pm':'pm'}" name="clinicsuffix2Sun"/>
				
				<!--<s:select label="Set Time" headerKey="-1" headerValue="Select Start Time" list="timeofday" name="clinichours1Sun" /> to
				<s:select label="Set Time" headerKey="-1" headerValue="Select End Time" list="timeofday" name="clinichours2Sun" />-->
				</div>
				
				<br><br><h4 style="color:#d0262a">Choose Clinic Services offered:</h4>
				<s:checkboxlist label="Services" name="services" list="servicelist" ></s:checkboxlist><br><br>
				<h4 style="color:#d0262a">You can add other services: </h4><s:textfield type="text"  cssClass="textbox" name="addservices" size="50" placeholder="Dermatology, Nephrology, Internal Medicine" /><br><br>
			
				<br><s:submit  cssClass="btnSubmit-solid-lg" value="Submit"></s:submit>
			
				</s:form>

            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of form-1 -->
    <!-- end of queue -->
</div>

<div id="hideable_MaClinic" style="display:none">
        <!-- Queue -->
    <div class="form-1">
        <div class="container" style="min-height: 40vh;">
            <div class="row">
                
						
						
								 
								
						<s:form action='clinicunenroll' method='post'   theme="simple">
						<h3>MANAGE CLINIC</h3>
						Clinic: <s:select list="clinics" name="clinicid" listValue="clinicname" listKey="clinicid" id="clinicun"/><br><br>
						<s:hidden  value="" name="clinic" id="clinicunhidden"/>
						Reason to revoke: <s:select label="Unenroll Clinic" headerValue="Change status"
						list="#{'Unenrolled':'Unenrolled', 'Closed':'Closed', 'Unable to pay':'Unable to pay', 'Others..':'Others..'}" 
						name="status" value="Unenrolled" id="selectReasonC"/><br><br>
						<s:textfield  type="text" placeholder="Enter other reason" name="reason" id="reasonclinic" cssClass="optionalfield"/><br><br>
						<br><s:submit value="REVOKE PRIVILEGES" cssClass="btnSubmit-solid-lg" onclick="setValues()" ></s:submit>		 
						</s:form>
						
						<!-- <form method="post" action="UploadFileClinic" enctype="multipart/form-data">
							Upload <span id="clinicuploadname"></span> Business Requirements:<br><br>
				            Select file to upload: <input type="file" name="uploadFile" /> (24 MB Limit)
				            <br/><br/>
				            <input type="submit" value="Upload" />
				        </form> -->
        
						<s:form action="doUpload" method="post" enctype="multipart/form-data">
						<h3>Upload <span id="clinicuploadname"></span> Business Requirements (2 MB Limit):</h3><br>
						<span id="fileerror" style="color:#d0262a"></span><br>
					       <s:file id="file" name="upload" label="File" accept=".jpg, .png, .pdf, .jpeg"/><br><br>
					       <s:submit  cssClass="btnSubmit-solid-lg" />
					   </s:form>

      
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of form-1 -->
    <!-- end of queue -->
</div>

<div id="hideable_EnAdmin"  style="display:none">
              <!-- Queue -->
    <div class="form-1">
        <div class="container" style="min-height: 40vh;">
            <div class="row mx-auto">
                

					
					
					<s:form action = "adminenroll"  cssClass="mx-auto" theme="simple">
					<h3 style="color:#d0262a">ENROLL ADMIN</h3><br>
					<h4 style="color:#d0262a">Clinic:</h4>
					<s:select list="clinics" label="Select clinic for admin" listKey="clinicid" listValue="clinicname" name="clinicid" ></s:select><br><br>
					<s:textfield type="text"  cssClass="textbox" name="firstname" required="required" size="50" placeholder="Enter Admin First Name"/><s:fielderror fieldName="FirstName"/><br>
					<s:textfield type="text"  cssClass="textbox" name="lastname" required="required" size="50" placeholder="Enter Admin Last Name"/><s:fielderror fieldName="LastName"/><br>
					<s:textfield type="text"  cssClass="textbox" name="email" required="required" size="50" placeholder="Enter Email"/><s:fielderror fieldName="Email"/><br>
					<s:select  label="Access Level" headerKey="-1" headerValue="Select Access Level" list="accessl" listKey="accesslevel_id" listValue="accesslevel" name="accesslevel" /><br>
					<br><s:submit value="SUBMIT" cssClass="btnSubmit-solid-lg" onclick="funtionAlert()"></s:submit>
					</s:form>

      

      
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of form-1 -->
    <!-- end of queue -->
   </div>   

<div id="hideable_MaAdmin" style="display:none">
              <!-- Queue -->
    <div class="form-1">
        <div class="container" style="min-height: 40vh;"> 
            <div class="row">
                
			
				
							 
						
				<s:form action='adminunenroll' method='post'   theme="simple">
				<h3>SELECT ADMINISTRATOR</h3>
				Admin: <s:select
				list="admins" name="adminid" listKey="adminid" listValue="firstname" id="adminun" /><br>
				<s:hidden  value="" name="admin" id="adminunhidden"/>
				Reason to revoke: <s:select label="Unenroll Admin" headerValue="Change status"
				list="#{'Removed from position':'Removed from position', 'Change job':'Change job', 'Committed Crime':'Commited Crime', 'Others..':'Others..'}"
				name="status" value="Removed from position" id="selectReasonA"/><br>
				<s:textfield  type="text" placeholder="Enter other reason" name="reason" id="reasonadmin" cssClass="optionalfield"/><br>
				<br><s:submit value="REVOKE PRIVILEGES" cssClass="btnSubmit-solid-lg" onclick="setValues2()" ></s:submit>
						 
				</s:form>


      
            </div> <!-- end of row -->
        </div> <!-- end of container -->
    </div> <!-- end of form-1 -->
    <!-- end of queue    cellspacing=12 style="color:black; border-collapse: seperate; text-align:center; border-color:white;  width: 100%; border-spacing: 10px;"-->
      </div>
   
        
        
        <div id="hideable_Statistics" style="display:none">
    <div class="form-1">
        <div class="container" style="min-height: 40vh;"> 

            <div class="row" style="background-color:white;">
            <div class="col-lg-12">
            
               		 	<h3 style="color:#d0262a">REPORTS</h3><br>
               		 	<select id="reportsSelect" onchange="val()">
               		 	<option>
               		 		General Overview
               		 	</option>
               		 	<option>
               		 		Audit Logs
               		 	</option>
               		 	<option>
               		 		Rating Reports
               		 	</option>
               		 	</select>
				<br><br>
	
				<div id="hideable_GeneralStat" style="display:block; text-align:left; color:#272727">
								<h3 style=" color:#d0262a">OVERVIEW OF HOW WELL KERUX IS DOING</h3>
								<s:iterator value = "stats">
								
								<span style="font-size:20px;" ><b><font color=#d0262a>TOTAL PATIENT(S) SERVED IN QUEUE(S): </font></b><s:property value = "queueserved"/></span><br>
								<span style="font-size:20px;" ><b><font color=#d0262a>TOTAL PATIENT(S) CANCELLED IN QUEUE(S): </font></b><s:property value = "queuescancelled"/></span><br><br>
								
								<span style="font-size:20px; "><b><font color=#d0262a>MOST ACTIVE DEPARTMENT (Overall): </font></b><s:property value = "highestdeptqueues"/></span><br>
								<span style="font-size:20px; "><b><font color=#d0262a>MOST ACTIVE DOCTOR (Overall): </font></b><s:property value = "highestdocqueues"/></span><br><br><br><br>
								
								Report Generated by: <s:property value = "rootadminname"/>  
								<script>
								var today = new Date();
								var dd = String(today.getDate()).padStart(2, '0');
								var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
								var yyyy = today.getFullYear();

								today = mm + '/' + dd + '/' + yyyy;
								document.write(today);
								</script>
								 <s:property value = "timeend"/>
								
								</s:iterator>
								<br><br>
								<span style="font-size:10px; color:#d0262a;">*This is a General Overview of how Kerux is doing throughout the whole System</span>
								
				</div>
				<div id="hideable_AuditLogs" style="display:none">
				<h5 style=" color:#d0262a">AUDIT LOGS</h5>
				
								<select onchange="list()" id="listSIZE">
						<option>
               		 		Last 5
               		 	</option>
               		 	<option>
               		 		Last 10
               		 	</option>
               		 	<option>
               		 		Last 25
               		 	</option>
               		 	</select>
								<table>
								
				
								<tr>
								<th>Log ID</th>
								<th>Table Name</th>
								<th>Event Type</th>
								<th>SqlCommand</th>
								<th>Old Data</th>
								<th>New Data</th>
								<th>Login Name</th>
								<th>Time Stamp</th>
								</tr>
								
								  
								<s:iterator value = "audits" begin="0" end="25" id="iterator" status="stat">
								
								<s:if test="#stat.count>10">
									<tr class="twentyfives" style="display:none">
								</s:if>
								<s:elseif test="#stat.count>5&&#stat.count<=10">
      								<tr class="tens" style="display:none">
    							</s:elseif>
    							<s:else>
    								<tr>
    							</s:else>
								<td><s:property value = "auditid"/></td> 
								<td><s:property value = "tablename"/></td>
								<td><s:property value = "eventtype"/></td> 
								<td><s:property value = "sqlcommand"/></td>
								<td><s:property value = "olddata"/></td> 
								<td><s:property value = "newdata"/></td>
								<td><s:property value = "loginname"/></td> 
								<td><s:property value = "timestamp"/></td>
								<s:if test="#stat.count>10">
									</tr>
								</s:if>
								<s:elseif test="#stat.count>5&&#stat.count<=10">
      								</tr>
    							</s:elseif>
    							<s:else>
    								</tr>
    							</s:else>
								</s:iterator>
								
								
								
								</table>
								<br>
								
								<a href="/Sympl/PDFaudit"><button class="btnSubmit-solid-lg" type="button">Download Pdf</button></a>
					</div>
					<div id="hideable_ReportLogs" style="display:none">
					<h5 style="color:#d0262a">RATING REPORTS</h5>
					<br>
					<span style="color:#d0262a"><b>TOTAL AVERAGE RATING : </b><s:property value="averating"/> / 5<br></span>
								<select onchange="list2()" id="listSIZE2">
						<option>
               		 		Last 5
               		 	</option>
               		 	<option>
               		 		Last 10
               		 	</option>
               		 	<option>
               		 		Last 25
               		 	</option>
               		 	</select>
					
								<table>
								
				
								<tr>
								<th>Time Stamp</th>
								<th>Rating (1 Lowest, 5 Highest)</th>
								<th>Queue Name</th>
								<th>Patient Name</th>
								</tr>
								
								<s:iterator value = "ratings"  status="status" >
								<s:if test="#status.count>10&&#status.count<=25">
									<tr class="twentyfives1" style="display:none">
								</s:if>
								<s:elseif test="#status.count>5&&#status.count<=10">
      								<tr class="tens1" style="display:none">
    							</s:elseif>
    							<s:else>
    								<tr>
    							</s:else>
								<td><s:property value = "timestamp"/></td>
								<td><s:property value = "rating"/></td> 
								<td><s:property value = "queuename"/></td>
								<td><s:property value = "patientname"/></td> 
								<s:if test="#status.count>10&&#status.count<=25">
									</tr>
								</s:if>
								<s:elseif test="#status.count>5&&#status.count<=10">
      								</tr>
    							</s:elseif>
    							<s:else>
    								</tr>
    							</s:else>
								</s:iterator>
								
								
								
								</table>
								<br>
								<a href="/Sympl/PDFrating"><button class="btnSubmit-solid-lg" type="button">Download Pdf</button></a>
					</div>
				
      					</div>
            		</div> <!-- end of row -->
            </div> <!-- end of container -->
    </div> <!-- end of form-1 -->

</div> 
        
        



                        </div>
                    </div> <!-- end of col -->
                </div> <!-- end of row -->
            </div> <!-- end of container -->
        </div> <!-- end of header-content -->
    </header> <!-- end of header -->
    <!-- end of header -->
    
       
      


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="jquery/chosen.jquery.js" type="text/javascript"></script>
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
var select = document.getElementById('clinicun'),
 clinicupload  = document.getElementById('clinicuploadname');

select.addEventListener('change', function() {
var selected = this.options[this.selectedIndex].innerHTML;
clinicupload.innerHTML = selected;
});

function changeClin() {
	var selects = select.options[select.selectedIndex].innerHTML;
	clinicupload.innerHTML = selects;
} 

function val() {
    d = document.getElementById("reportsSelect").value;
	
    if(d=="General Overview"){
    	var x=document.getElementById('hideable_GeneralStat');
    	var y=document.getElementById('hideable_AuditLogs');
    	var z=document.getElementById('hideable_ReportLogs');
    	if (x.style.display=='none'){
    		x.style.display = 'block';
    		y.style.display = 'none';
    		z.style.display = 'none';
    	}
    	else{
    		
    	}
    }
    else if(d=="Audit Logs"){
    	var x=document.getElementById('hideable_GeneralStat');
    	var y=document.getElementById('hideable_AuditLogs');
    	var z=document.getElementById('hideable_ReportLogs');
    	if (y.style.display=='none'){
    		y.style.display = 'block';
    		x.style.display = 'none';
    		z.style.display = 'none';
    	}
    	else{
    		
    	}
    }
    else if(d=="Rating Reports"){
    	var x=document.getElementById('hideable_GeneralStat');
    	var y=document.getElementById('hideable_AuditLogs');
    	var z=document.getElementById('hideable_ReportLogs');
    	if (z.style.display=='none'){
    		z.style.display = 'block';
    		y.style.display = 'none';
    		x.style.display = 'none';
    	}
    	else{
    		
    	}
    }
}

function list() {

	f= document.getElementById("listSIZE").value;
	
	 var sl = document.getElementsByClassName("tens");
	 var slide = document.getElementsByClassName("twentyfives");
	 
	    
	    
    if(f=="Last 5"){
    	for (var i = 0; i < sl.length; i++) {
    	    sl.item(i).style.display='none';
    	 }
    	for (var i = 0; i < slide.length; i++) {
    	    slide.item(i).style.display='none';
    	 }
   
    }
    else if(f=="Last 10"){
    	for (var i = 0; i < sl.length; i++) {
    	    sl.item(i).style.display='block';
    	 }
    	for (var i = 0; i < slide.length; i++) {
    	    slide.item(i).style.display='none';
    	 }
    }
    else if(f=="Last 25"){
    	for (var i = 0; i < sl.length; i++) {
    	    sl.item(i).style.display='block';
    	 }
    	for (var i = 0; i < slide.length; i++) {
    	    slide.item(i).style.display='block';
    	 }
    }
    	
    
}


function list2() {

	f= document.getElementById("listSIZE2").value;
	
	 var sl = document.getElementsByClassName("tens1");
	 var slide = document.getElementsByClassName("twentyfives1");
	 
	    
	    
    if(f=="Last 5"){
    	for (var i = 0; i < sl.length; i++) {
    	    sl.item(i).style.display='none';
    	 }
    	for (var i = 0; i < slide.length; i++) {
    	    slide.item(i).style.display='none';
    	 }
   
    }
    else if(f=="Last 10"){
    	for (var i = 0; i < sl.length; i++) {
    	    sl.item(i).style.display='block';
    	 }
    	for (var i = 0; i < slide.length; i++) {
    	    slide.item(i).style.display='none';
    	 }
    }
    else if(f=="Last 25"){
    	for (var i = 0; i < sl.length; i++) {
    	    sl.item(i).style.display='block';
    	 }
    	for (var i = 0; i < slide.length; i++) {
    	    slide.item(i).style.display='block';
    	 }
    }
    	
    
}
 
  $(document).ready(function () {
	  
	  window.onload = changeClin;
	//Get the modal
	  var modal = document.getElementById("myModal");
	  var modal1 = document.getElementById("myModal1");

		
		
	  
	  
window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
    
    if (event.target == modal1) {
        modal1.style.display = "none";
      }
  }

var uploadField = document.getElementById("file");
var fileError = document.getElementById("fileerror");
uploadField.onchange = function() {
    if(this.files[0].size > 2097152){
       fileError.innerHTML=" File size is too large!";
      // alert("File Size is too big!");
       this.value = "";
    }
    else{
    	fileError.innerHTML="";
    };
};
	  
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
	
	 $('#EnClinic').on('click', function () {
	    	var x=document.getElementById('hideable_EnClinic');
	    	var y=document.getElementById('hideable_MaClinic');
	    	var z=document.getElementById('hideable_EnAdmin');
	    	var a=document.getElementById('hideable_MaAdmin');
	    	var b=document.getElementById('hideable_Statistics');
	    	if (x.style.display=='none'){
	    		x.style.display = 'block';
	    		y.style.display = 'none';
	    		z.style.display = 'none';
	    		a.style.display = 'none';
	    		b.style.display = 'none';
	    	}
	    	else{
	    		
	    	}
	    });
	    $('#MaClinic').on('click', function () {
	    	var x=document.getElementById('hideable_EnClinic');
	    	var y=document.getElementById('hideable_MaClinic');
	    	var z=document.getElementById('hideable_EnAdmin');
	    	var a=document.getElementById('hideable_MaAdmin');
	    	var b=document.getElementById('hideable_Statistics');
	    	if (y.style.display=='none'){
	    		x.style.display = 'none';
	    		y.style.display = 'block';
	    		z.style.display = 'none';
	    		a.style.display = 'none';
	    		b.style.display = 'none';
	    	}
	    	else{
	    		
	    	}
	    });
	    $('#EnAdmin').on('click', function () {
	    	var x=document.getElementById('hideable_EnClinic');
	    	var y=document.getElementById('hideable_MaClinic');
	    	var z=document.getElementById('hideable_EnAdmin');
	    	var a=document.getElementById('hideable_MaAdmin');
	    	var b=document.getElementById('hideable_Statistics');
	    	if (z.style.display=='none'){
	    		x.style.display = 'none';
	    		y.style.display = 'none';
	    		z.style.display = 'block';
	    		a.style.display = 'none';
	    		b.style.display = 'none';
	    	}
	    	else{
	    		
	    	}
	    });
	    $('#MaAdmin').on('click', function () {
	    	var x=document.getElementById('hideable_EnClinic');
	    	var y=document.getElementById('hideable_MaClinic');
	    	var z=document.getElementById('hideable_EnAdmin');
	    	var a=document.getElementById('hideable_MaAdmin');
	    	var b=document.getElementById('hideable_Statistics');
	    	if (a.style.display=='none'){
	    		x.style.display = 'none';
	    		y.style.display = 'none';
	    		z.style.display = 'none';
	    		a.style.display = 'block';
	    		b.style.display = 'none';
	    	}
	    	else{
	    		
	    	}
	    });
	    $('#Statistics').on('click', function () {
	    	var x=document.getElementById('hideable_EnClinic');
	    	var y=document.getElementById('hideable_MaClinic');
	    	var z=document.getElementById('hideable_EnAdmin');
	    	var a=document.getElementById('hideable_MaAdmin');
	    	var b=document.getElementById('hideable_Statistics');
	    	if (b.style.display=='none'){
	    		x.style.display = 'none';
	    		y.style.display = 'none';
	    		z.style.display = 'none';
	    		a.style.display = 'none';
	    		b.style.display = 'block';
	    	}
	    	else{
	    		
	    	}
	    });
	
	   
	
  });

</script>

</body>
</html>