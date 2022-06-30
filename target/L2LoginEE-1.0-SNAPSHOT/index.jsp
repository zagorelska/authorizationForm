<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Prog Academy</title>
      <style>
          div {
              height: 10px;
          }
          h1 {
              font-family: 'Times New Roman', Times, serif;
              font-size: 14pt;
              color: #0a3fdc;
          }
          p {
              font-family: 'Times New Roman', Times, sans-serif;
              font-size: 12pt;
              color: #c70808;
          }
          label {
              width: 70px;
              display: inline-block;
          }
          span {
              font-size: 8pt;
          }
      </style>
  </head>
  <body>
    <% String login = (String)session.getAttribute("user_login"); %>
    <% String incorrect = (String)session.getAttribute("incorrect"); %>

    <% if (login == null || "".equals(login)) { %>
        <form action="/login" method="POST">
            <div><label>Login: </label><input type="text" name="login"></div><br>
            <span class="required">*the password must be at least 10 characters long, contain at least one number, one capital letter, one special character!</span><br>
            <div><label>Password: </label><input type="password" name="password"></div><br>
            <div><label>Age: </label><input type="number" name="age"></div><br>
            <div><input type="submit" /></div>
        </form>
        <% if (incorrect != null && !"".equals(incorrect)) { %> <p><%= incorrect %></p> <% } %>
    <% } else { %>
        <h1>You are logged in as: <%= login %></h1>
        <br>Click this link to <a href="/login?a=exit">logout</a>
    <% } %>
  </body>
</html>
