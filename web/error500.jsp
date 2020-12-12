<%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
     <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Error 404</title>
    <s:head/>
  <link rel="stylesheet" href="css/error500css.css">

<style>
.buttonCss
 {
  display: inline-block;
  margin-top: 2rem;
  padding: 0.5rem 1rem;
  border: 3px solid #595959;
  background: transparent;
  font-size: 1rem;
  color: #595959;
  text-decoration: none;
  cursor: pointer;
  font-weight: bold;
}

</style>
</head>
<body>
<!-- partial:index.partial.html -->
<main class='container'>

  <article class='content'>
  <img src="images/kerux.png" width="30%" height="30%">
    <p><font color="#d0262a">Hello User,</font></p>
    <p>You got lost in the error <strong><font color="#d0262a">500</font></strong> page.</p>
    <p>
    	
		<s:form action="index" theme="simple">
						<s:submit type="submit" cssClass="buttonCss" value="Go back to home."/>
				</s:form>
    </p>
  </article>
</main>


</body>
</html>