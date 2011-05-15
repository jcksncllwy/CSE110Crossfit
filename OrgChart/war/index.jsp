<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.OrgChartEntry" %>
<%@ page import="edu.ucsd.cse110.orgchart.entry.EntryManager" %>
<%@ page import="edu.ucsd.cse110.orgchart.visualizer.OrgChartVisualizer" %>

<html>
  <head>
    <title>CSE 110 Org Chart</title>
    <link type="text/css" rel="stylesheet" href="/style.css" />
  </head>
  <body>
    <!-- Page -->
    <div id="page">

      <!-- Header -->
      <div id="header">
        <div id="title" class="menu left">
          <a href="/">CSE 110 Org Chart</a>
        </div>
        <div id="links" class="menu left">
          <%
          UserService userService = UserServiceFactory.getUserService();
          User user = userService.getCurrentUser();
          if (user != null) {
          %>
            <ul>
              <li><a href="addentry">add entry</a></li>
              <li class="last"><a href="rmentry">remove entry</a></li>
            </ul>
          <%
          }
          %>
        </div>
        <div id="userlinks" class="menu right">
          <%
          if (user != null) {
          %>
            <ul>
              <li class="first"><%= user.getNickname() %></li>
              <li class="last"><a href="<%= userService.createLogoutURL("/") %>">sign out</a></li>
            </ul>
          <%
          } else {
          %>
            <ul>
              <li class="first last"><a href="<%= userService.createLoginURL("/") %>">sign in</a></li>
            </ul>
          <%
          }
          %>
        </div>
      </div>

    <!-- Org Chart -->
    <div id="main">
      <%
      
      Map<OrgChartEntry, List<OrgChartEntry>> entries = EntryManager.getEntryTree();
      
      if (entries.isEmpty()) {
      %>
        <p>The org chart has no entries.</p>
      <%
      } else {
        StringBuilder chart = OrgChartVisualizer.printEntryTree(entries, null);
      %>
        <pre><%= chart.toString() %></pre>
      <%
      }
      %>
    </div>
    </div>
  </body>
</html>