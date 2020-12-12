YOU HAVE LOGGED OUT
<%
			       if(session!=null)
			       {
			          session.invalidate();
			       }
			     %>
<jsp:include page="login.jsp"></jsp:include>