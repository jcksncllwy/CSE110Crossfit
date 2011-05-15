<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.OrgChartEntry" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.EntryManager" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.EntryAction" %>

<html>
  <head>
    <title>Add Entry - CSE 110 Org Chart</title>
  </head>
  <body>

  <%
    String nodeString = request.getParameter("node");
    
    if (nodeString != null && !nodeString.trim().isEmpty()) {
        long node = Long.parseLong(nodeString);
//ADD
               EntryAction.removeEntry(node);
response.sendRedirect("/");
    } else {
    %>
    <form action=<%= request.getRequestURI() %> method="post">
      <div>
        Delete entry:
        <select name="node">
          <%
          OrgChartEntry[] entries = EntryManager.getAllEntries();
          
          if (entries.length == 0) {
          %>
            <option value="-1">None</option>
          <%
          }
          
          for (OrgChartEntry entry : entries) {
          %>
            <option value="<%= entry.getKey().getId() %>"><%= entry.getName() %>, <%= entry.getTitle() %></option>
          <%
          }
          %>
        </select>
      </div>
      <div><input type="submit" value="Remove Entry" /></div>
    </form>
    <%
    }
  %>

  </body>
</html>