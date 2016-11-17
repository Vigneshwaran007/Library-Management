<%@page import="com.wipro.book.bean.BookBean,java.util.Enumeration"%>
<html>
<head>
</head>
<body>

<% BookBean bb=(BookBean)request.getAttribute("arun"); %>
<table border=1>
<th>Title</th>
<th>AuthorName</th>
<tr>
<td><%= bb.getBookName() %></td>
<td><%= bb.getAuthor().getAuthorName() %></td>
</tr>
</table>
</body>
</html>