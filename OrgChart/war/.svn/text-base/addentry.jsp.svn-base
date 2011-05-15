<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.OrgChartEntry" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.EntryAction" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.EntryManager" %>
<%@ page import="edu.ucsd.cse110.orgchart.form.EntryForm" %>
<%@ page import="edu.ucsd.cse110.orgchart.form.FormValidator" %>

<html>
  <head>
    <title>Add Entry - CSE 110 Org Chart</title>
  </head>
  <body>

<%
	String name = request.getParameter("name");
    String title = request.getParameter("title");
    String parent = request.getParameter("parent");
    
    EntryForm form = new EntryForm(name, title, parent);
    
    if (FormValidator.validateForm(form) == true) {
    	
    	User user = UserServiceFactory.getUserService().getCurrentUser();
        EntryAction.addEntry(form, user);
        
        response.sendRedirect("/");
    } else {
%>
    <form action=<%= request.getRequestURI() %> method="post">
      <div>Name: <input type="text" name="name" /></div>
      <div>Title: <input type="text" name="title" /></div>
      <div>
        Reports To:
        <select name="parent">
          <option value="-1">None</option>
          <%
        
         OrgChartEntry[] entries = EntryManager.getAllEntries();
          
          for (OrgChartEntry entry : entries) {
          %>
            <option value="<%= entry.getKey().getId() %>"><%= entry.getName() %>, <%= entry.getTitle() %></option>
          <%
          }
          %>
        </select>
      </div>
      <div><input type="submit" value="Add Entry" /></div>
    </form>
<%
    }
%>

  </body>
</html>